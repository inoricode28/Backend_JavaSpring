let adminProducts = [];
let adminCategories = [];
let adminOrders = [];
let adminVentas = [];

function initAdmin() {
    if (!isAdmin()) {
        window.location.href = 'index.html';
        return;
    }
    loadAdminProducts();
    setupAdminTabs();
}

function setupAdminTabs() {
    document.querySelectorAll('.admin-tab').forEach(tab => {
        tab.addEventListener('click', function() {
            document.querySelectorAll('.admin-tab').forEach(t => t.classList.remove('active'));
            this.classList.add('active');
            document.querySelectorAll('.admin-panel').forEach(p => p.style.display = 'none');
            const target = document.getElementById(this.dataset.target);
            if (target) target.style.display = 'block';
            if (this.dataset.target === 'tabProductos') loadAdminProducts();
            if (this.dataset.target === 'tabCategorias') loadAdminCategories();
            if (this.dataset.target === 'tabPedidos') loadAdminOrders();
            if (this.dataset.target === 'tabVentas') loadAdminVentas();
        });
    });
}

async function loadAdminProducts() {
    const container = document.getElementById('adminProductos');
    if (!container) return;
    container.innerHTML = '<div class="loading"><div class="spinner"></div></div>';
    try {
        adminProducts = await apiFetch('/productos');
        renderAdminProducts();
    } catch(e) {
        container.innerHTML = '<div class="alert alert-error">Error: ' + e.message + '</div>';
    }
}

function renderAdminProducts() {
    const container = document.getElementById('adminProductos');
    if (!container) return;
    container.innerHTML =
        '<div style="margin-bottom:1rem;"><button class="btn btn-primary" onclick="showProductForm()">+ Nuevo Producto</button></div>' +
        '<div class="table-wrap"><table class="admin-table">' +
        '<thead><tr><th>ID</th><th>Nombre</th><th>Categoria</th><th>Precio</th><th>Stock</th><th>Estado</th><th>Acciones</th></tr></thead>' +
        '<tbody>' + adminProducts.map(p =>
            '<tr>' +
            '<td>' + p.id + '</td>' +
            '<td>' + p.nombre + '</td>' +
            '<td>' + (p.categoriaNombre || '-') + '</td>' +
            '<td>S/ ' + (p.precioMin || '0') + '</td>' +
            '<td>' + (p.stock || 0) + '</td>' +
            '<td>' + (p.estado === 1 ? '<span style="color:var(--accent-green)">Activo</span>' : '<span style="color:var(--accent-red)">Inactivo</span>') + '</td>' +
            '<td class="actions">' +
            '<button class="btn btn-primary btn-sm" onclick="editProduct(' + p.id + ')">Editar</button>' +
            '<button class="btn btn-danger btn-sm" onclick="deleteProduct(' + p.id + ')">Eliminar</button>' +
            '</td></tr>'
        ).join('') +
        '</tbody></table></div>';
}

async function loadAdminCategories() {
    const container = document.getElementById('adminCategorias');
    if (!container) return;
    container.innerHTML = '<div class="loading"><div class="spinner"></div></div>';
    try {
        adminCategories = await apiFetch('/categorias');
        container.innerHTML =
            '<div style="margin-bottom:1rem;"><button class="btn btn-primary" onclick="showCategoryForm()">+ Nueva Categoria</button></div>' +
            '<div class="table-wrap"><table class="admin-table">' +
            '<thead><tr><th>ID</th><th>Nombre</th><th>Estado</th><th>Acciones</th></tr></thead>' +
            '<tbody>' + adminCategories.map(c =>
                '<tr>' +
                '<td>' + c.codCategoria + '</td>' +
                '<td>' + c.nombre + '</td>' +
                '<td>' + (c.estado === 1 ? '<span style="color:var(--accent-green)">Activo</span>' : '<span style="color:var(--accent-red)">Inactivo</span>') + '</td>' +
                '<td class="actions">' +
                '<button class="btn btn-primary btn-sm" onclick="editCategory(' + c.codCategoria + ')">Editar</button>' +
                '<button class="btn btn-danger btn-sm" onclick="deleteCategory(' + c.codCategoria + ')">Eliminar</button>' +
                '</td></tr>'
            ).join('') +
            '</tbody></table></div>';
    } catch(e) {
        container.innerHTML = '<div class="alert alert-error">Error: ' + e.message + '</div>';
    }
}

async function loadAdminOrders() {
    const container = document.getElementById('adminPedidos');
    if (!container) return;
    container.innerHTML = '<div class="loading"><div class="spinner"></div></div>';
    try {
        adminOrders = await apiFetch('/ventas');
        container.innerHTML =
            '<div class="table-wrap"><table class="admin-table">' +
            '<thead><tr><th>ID Venta</th><th>Cliente</th><th>Codigo</th><th>Total</th><th>Estado</th><th>Fecha</th><th>Acciones</th></tr></thead>' +
            '<tbody>' + (adminOrders.length === 0 ? '<tr><td colspan="7" style="text-align:center;color:var(--text-muted)">No hay ventas registradas</td></tr>' :
                adminOrders.map(v =>
                    '<tr>' +
                    '<td>' + v.idVenta + '</td>' +
                    '<td>' + (v.clienteNombre || '-') + '</td>' +
                    '<td>' + (v.codigo || '-') + '</td>' +
                    '<td>S/ ' + (v.precioTotal || '0') + '</td>' +
                    '<td>' + (v.estado === 1 ? '<span style="color:var(--accent-green)">Completado</span>' : '<span style="color:var(--accent-yellow)">Pendiente</span>') + '</td>' +
                    '<td>' + (v.createdAt ? new Date(v.createdAt).toLocaleDateString() : '-') + '</td>' +
                    '<td class="actions">' +
                    '<button class="btn btn-success btn-sm" onclick="updateOrderStatus(' + v.idVenta + ', 1)">Completar</button>' +
                    '</td></tr>'
                ).join('')
            ) + '</tbody></table></div>';
    } catch(e) {
        container.innerHTML = '<div class="alert alert-error">Error: ' + e.message + '</div>';
    }
}

async function loadAdminVentas() {
    const container = document.getElementById('adminVentas');
    if (!container) return loadAdminOrders();
    loadAdminOrders();
}

async function showProductForm(product) {
    const modal = document.getElementById('adminModal');
    const content = document.getElementById('adminModalContent');
    const cats = await apiFetch('/categorias');

    content.innerHTML =
        '<button class="modal-close" onclick="closeAdminModal()">&times;</button>' +
        '<h3>' + (product ? 'Editar Producto' : 'Nuevo Producto') + '</h3>' +
        '<form id="productForm">' +
        '<div class="form-group"><label>Nombre</label><input type="text" id="pfNombre" value="' + (product ? product.nombre : '') + '" required></div>' +
        '<div class="form-group"><label>Marca</label><input type="text" id="pfMarca" value="' + (product ? product.marca || '' : '') + '"></div>' +
        '<div class="form-group"><label>Categoria</label><select id="pfCategoria">' +
        cats.map(c => '<option value="' + c.codCategoria + '" ' + (product && product.categoriaId == c.codCategoria ? 'selected' : '') + '>' + c.nombre + '</option>').join('') +
        '</select></div>' +
        '<div class="form-group"><label>Descripcion</label><textarea id="pfDescripcion">' + (product ? product.descripcion || '' : '') + '</textarea></div>' +
        '<div style="display:grid;grid-template-columns:1fr 1fr;gap:1rem;">' +
        '<div class="form-group"><label>Precio Min.</label><input type="number" step="0.01" id="pfPrecioMin" value="' + (product ? product.precioMin || '' : '') + '" required></div>' +
        '<div class="form-group"><label>Precio Max.</label><input type="number" step="0.01" id="pfPrecioMax" value="' + (product ? product.precioMax || '' : '') + '"></div>' +
        '</div>' +
        '<div style="display:grid;grid-template-columns:1fr 1fr;gap:1rem;">' +
        '<div class="form-group"><label>Costo</label><input type="number" step="0.01" id="pfCosto" value="' + (product ? product.costo || '' : '') + '"></div>' +
        '<div class="form-group"><label>Stock</label><input type="number" id="pfStock" value="' + (product ? product.stock || '' : '') + '" required></div>' +
        '</div>' +
        '<div class="form-group"><label>Imagen</label><input type="file" id="pfImagen" accept="image/*"></div>' +
        (product && product.imagen ? '<div class="form-group"><small>Imagen actual: <a href="' + product.imagen + '" target="_blank">' + product.imagen.split('/').pop() + '</a></small></div>' : '') +
        '<div class="form-actions">' +
        '<button type="button" class="btn btn-outline" onclick="closeAdminModal()">Cancelar</button>' +
        '<button type="submit" class="btn btn-primary">' + (product ? 'Actualizar' : 'Crear') + '</button>' +
        '</div></form>';
    modal.classList.add('active');

    document.getElementById('productForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('categoriaId', document.getElementById('pfCategoria').value);
        formData.append('nombre', document.getElementById('pfNombre').value);
        formData.append('marca', document.getElementById('pfMarca').value);
        formData.append('descripcion', document.getElementById('pfDescripcion').value);
        formData.append('precioMin', document.getElementById('pfPrecioMin').value);
        formData.append('precioMax', document.getElementById('pfPrecioMax').value || '');
        formData.append('costo', document.getElementById('pfCosto').value || '');
        formData.append('stock', document.getElementById('pfStock').value);
        const fileInput = document.getElementById('pfImagen');
        if (fileInput.files.length > 0) {
            formData.append('file', fileInput.files[0]);
        }
        if (product && product.imagen) {
            formData.append('imagenActual', product.imagen);
        }
        try {
            if (product) {
                await apiFetch('/productos/' + product.id, { method: 'PUT', body: formData });
            } else {
                await apiFetch('/productos', { method: 'POST', body: formData });
            }
            closeAdminModal();
            loadAdminProducts();
        } catch(e) {
            alert('Error: ' + e.message);
        }
    });
}

function editProduct(id) {
    const p = adminProducts.find(x => x.id === id);
    if (p) showProductForm(p);
}

async function deleteProduct(id) {
    if (!confirm('Eliminar este producto?')) return;
    try {
        await apiFetch('/productos/' + id, { method: 'DELETE' });
        loadAdminProducts();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

function showCategoryForm(cat) {
    const modal = document.getElementById('adminModal');
    const content = document.getElementById('adminModalContent');
    content.innerHTML =
        '<button class="modal-close" onclick="closeAdminModal()">&times;</button>' +
        '<h3>' + (cat ? 'Editar Categoria' : 'Nueva Categoria') + '</h3>' +
        '<form id="categoryForm">' +
        '<div class="form-group"><label>Nombre</label><input type="text" id="cfNombre" value="' + (cat ? cat.nombre : '') + '" required></div>' +
        '<div class="form-actions">' +
        '<button type="button" class="btn btn-outline" onclick="closeAdminModal()">Cancelar</button>' +
        '<button type="submit" class="btn btn-primary">' + (cat ? 'Actualizar' : 'Crear') + '</button>' +
        '</div></form>';
    modal.classList.add('active');

    document.getElementById('categoryForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        try {
            if (cat) {
                await apiFetch('/categorias/' + cat.codCategoria, { method: 'PUT', body: { nombre: document.getElementById('cfNombre').value } });
            } else {
                await apiFetch('/categorias', { method: 'POST', body: { nombre: document.getElementById('cfNombre').value } });
            }
            closeAdminModal();
            loadAdminCategories();
        } catch(e) {
            alert('Error: ' + e.message);
        }
    });
}

function editCategory(id) {
    const c = adminCategories.find(x => x.codCategoria === id);
    if (c) showCategoryForm(c);
}

async function deleteCategory(id) {
    if (!confirm('Eliminar esta categoria?')) return;
    try {
        await apiFetch('/categorias/' + id, { method: 'DELETE' });
        loadAdminCategories();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

async function updateOrderStatus(id, estado) {
    try {
        await apiFetch('/ventas/' + id + '/estado?estado=' + estado, { method: 'PUT' });
        loadAdminOrders();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

function closeAdminModal() {
    document.getElementById('adminModal').classList.remove('active');
}
