let allProducts = [];
let allCategories = [];
let allVariants = {};

async function loadCategories() {
    try {
        allCategories = await apiFetch('/categorias');
        renderCategories();
        return allCategories;
    } catch (e) {
        console.error('Error cargando categorias:', e);
        return [];
    }
}

function renderCategories() {
    const container = document.getElementById('categoriesList');
    if (!container) return;
    container.innerHTML = '<li><a href="#" class="active" data-id="">Todo</a></li>' +
        allCategories.map(c =>
            '<li><a href="#" data-id="' + c.codCategoria + '">' + c.nombre + '</a></li>'
        ).join('');

    container.querySelectorAll('a').forEach(a => {
        a.addEventListener('click', (e) => {
            e.preventDefault();
            container.querySelectorAll('a').forEach(x => x.classList.remove('active'));
            a.classList.add('active');
            const id = a.getAttribute('data-id');
            if (id) {
                renderProducts(allProducts.filter(p => p.categoriaId == id));
            } else {
                renderProducts(allProducts);
            }
        });
    });
}

async function loadProducts() {
    const container = document.getElementById('productGrid');
    if (!container) return;
    container.innerHTML = '<div class="loading"><div class="spinner"></div><p>Cargando productos...</p></div>';
    try {
        allProducts = await apiFetch('/productos');
        renderProducts(allProducts);
    } catch (e) {
        container.innerHTML = '<div class="empty-state"><p>Error al cargar productos</p></div>';
    }
}

function renderProducts(products) {
    const container = document.getElementById('productGrid');
    if (!container) return;
    if (!products || products.length === 0) {
        container.innerHTML = '<div class="empty-state"><p>No hay productos disponibles</p></div>';
        return;
    }
    container.innerHTML = products.map(p => {
        const imgHtml = p.imagen
            ? '<img src="' + API + p.imagen.replace('/newproject', '') + '" alt="' + p.nombre + '">'
            : '👕';
        return '<div class="product-card">' +
            '<div class="product-img">' + imgHtml + '</div>' +
            '<div class="product-info">' +
            '<div class="product-category">' + (p.categoriaNombre || '') + '</div>' +
            '<div class="product-name">' + p.nombre + '</div>' +
            '<div class="product-marca">' + (p.marca || '') + '</div>' +
            '<div class="product-price">S/ ' + (p.precioMin || '0.00') + '</div>' +
            '<div class="product-actions">' +
            '<button class="btn btn-primary btn-sm" onclick="showProductDetail(' + p.id + ')">Ver</button>' +
            '<button class="btn btn-outline btn-sm" onclick="addToCartFromProduct(' + p.id + ')">🛒</button>' +
            '</div></div></div>';
    }).join('');
}

async function showProductDetail(id) {
    const p = allProducts.find(x => x.id === id);
    if (!p) return;

    try {
        allVariants[id] = await apiFetch('/productos/' + id + '/variantes');
    } catch(e) {
        allVariants[id] = [];
    }

    const modal = document.getElementById('productModal');
    const content = document.getElementById('productModalContent');
    const variants = allVariants[id] || [];
    const tallas = [...new Set(variants.map(v => v.talla).filter(Boolean))];
    const colores = [...new Set(variants.map(v => v.color).filter(Boolean))];

    const imgHtml = p.imagen
        ? '<img src="' + API + p.imagen.replace('/newproject', '') + '" style="max-width:100%;max-height:300px;border-radius:12px;">'
        : '<div style="font-size:4rem;text-align:center;padding:2rem;">👕</div>';

    content.innerHTML =
        '<button class="modal-close" onclick="closeProductModal()">&times;</button>' +
        '<div style="display:grid;grid-template-columns:1fr 1fr;gap:2rem;align-items:start;">' +
        '<div>' + imgHtml + '</div>' +
        '<div>' +
        '<div class="product-category">' + (p.categoriaNombre || '') + '</div>' +
        '<h3 style="font-size:1.5rem;margin-bottom:0.5rem;">' + p.nombre + '</h3>' +
        '<div class="product-marca">' + (p.marca || '') + '</div>' +
        '<p style="margin:1rem 0;color:var(--text-secondary);">' + (p.descripcion || '') + '</p>' +
        '<div class="product-price" style="font-size:1.5rem;">S/ ' + (p.precioMin || '0.00') + '</div>' +
        (tallas.length > 0 ? '<div><div class="variant-label">Talla:</div><div class="variant-selector" id="tallasSelector">' +
            tallas.map(t => '<span class="variant-option" data-value="' + t + '">' + t + '</span>').join('') +
            '</div></div>' : '') +
        (colores.length > 0 ? '<div style="margin-top:0.5rem;"><div class="variant-label">Color:</div><div class="variant-selector" id="coloresSelector">' +
            colores.map(c => '<span class="variant-option" data-value="' + c + '">' + c + '</span>').join('') +
            '</div></div>' : '') +
        '<div style="margin-top:1.5rem;display:flex;gap:0.75rem;">' +
        '<button class="btn btn-primary" onclick="addToCartFromDetail(' + id + ')">Agregar al Carrito</button>' +
        '</div></div></div>';

    modal.classList.add('active');

    document.querySelectorAll('.variant-option').forEach(el => {
        el.addEventListener('click', function() {
            const parent = this.parentElement;
            parent.querySelectorAll('.variant-option').forEach(x => x.classList.remove('selected'));
            this.classList.add('selected');
        });
    });
}

function closeProductModal() {
    document.getElementById('productModal').classList.remove('active');
}

async function addToCartFromProduct(productId) {
    if (!isLoggedIn()) {
        window.location.href = 'login.html';
        return;
    }
    const user = getUser();
    const clienteId = user.id;
    const variants = allVariants[productId] || [];
    if (variants.length === 0) {
        alert('Este producto no tiene variantes disponibles');
        return;
    }
    const v = variants[0];
    try {
        await apiFetch('/carrito/cliente/' + clienteId + '/agregar', {
            method: 'POST',
            body: { productoVarianteId: v.idVariante, cantidad: 1 }
        });
        alert('Producto agregado al carrito');
        updateCartBadge();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

async function addToCartFromDetail(productId) {
    if (!isLoggedIn()) {
        window.location.href = 'login.html';
        return;
    }

    const selectedTalla = document.querySelector('#tallasSelector .selected');
    const selectedColor = document.querySelector('#coloresSelector .selected');
    const variants = allVariants[productId] || [];

    let match = null;
    if (selectedTalla && selectedColor) {
        match = variants.find(v => v.talla === selectedTalla.dataset.value && v.color === selectedColor.dataset.value);
    } else if (selectedTalla) {
        match = variants.find(v => v.talla === selectedTalla.dataset.value);
    } else if (selectedColor) {
        match = variants.find(v => v.color === selectedColor.dataset.value);
    } else {
        match = variants[0];
    }

    if (!match) {
        alert('Selecciona una combinacion de talla y color disponible');
        return;
    }

    const user = getUser();
    try {
        await apiFetch('/carrito/cliente/' + user.id + '/agregar', {
            method: 'POST',
            body: { productoVarianteId: match.idVariante, cantidad: 1 }
        });
        alert('Producto agregado al carrito');
        closeProductModal();
        updateCartBadge();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

async function updateCartBadge() {
    const badge = document.getElementById('cartBadge');
    if (!badge) return;
    const user = getUser();
    if (!user) { badge.textContent = '0'; return; }
    try {
        const carrito = await apiFetch('/carrito/cliente/' + user.id);
        const detalles = carrito.detalles || [];
        const total = detalles.reduce((s, d) => s + (d.cantidad || 0), 0);
        badge.textContent = total;
    } catch(e) {
        badge.textContent = '0';
    }
}
