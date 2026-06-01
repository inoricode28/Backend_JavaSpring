let cartData = null;

async function loadCart() {
    const user = getUser();
    if (!user) { window.location.href = 'login.html'; return; }

    const container = document.getElementById('cartItems');
    const summary = document.getElementById('cartSummary');
    if (!container) return;

    container.innerHTML = '<div class="loading"><div class="spinner"></div><p>Cargando carrito...</p></div>';

    try {
        cartData = await apiFetch('/carrito/cliente/' + user.id);
        renderCart();
    } catch(e) {
        container.innerHTML = '<div class="empty-state"><p>Error al cargar el carrito</p></div>';
    }
}

function renderCart() {
    const container = document.getElementById('cartItems');
    const summary = document.getElementById('cartSummary');
    if (!container) return;

    const detalles = cartData.detalles || [];

    if (detalles.length === 0) {
        container.innerHTML = '<div class="empty-state"><p>Tu carrito esta vacio</p></div>';
        if (summary) summary.innerHTML = '';
        return;
    }

    container.innerHTML = detalles.map(d =>
        '<div class="cart-item">' +
        '<div class="cart-item-img">👕</div>' +
        '<div class="cart-item-info">' +
        '<div class="cart-item-name">' + (d.productoNombre || 'Producto') + '</div>' +
        '<div class="cart-item-detail">' +
        (d.talla ? 'Talla: ' + d.talla : '') +
        (d.color ? ' | Color: ' + d.color : '') +
        '</div>' +
        '</div>' +
        '<div class="cart-item-qty">' +
        '<button onclick="updateQty(' + d.idCarritoDetalle + ', -1)">-</button>' +
        '<span>' + d.cantidad + '</span>' +
        '<button onclick="updateQty(' + d.idCarritoDetalle + ', 1)">+</button>' +
        '</div>' +
        '<div class="cart-item-price">S/ ' + (d.subtotal || '0.00') + '</div>' +
        '<button class="cart-item-remove" onclick="removeItem(' + d.idCarritoDetalle + ')">&times;</button>' +
        '</div>'
    ).join('');

    const total = detalles.reduce((s, d) => s + parseFloat(d.subtotal || 0), 0);
    if (summary) {
        summary.innerHTML =
            '<h3>Resumen</h3>' +
            '<div class="summary-row"><span>Productos</span><span>' + detalles.length + '</span></div>' +
            '<div class="summary-row total"><span>Total</span><span>S/ ' + total.toFixed(2) + '</span></div>' +
            '<button class="btn btn-primary checkout-btn" onclick="showCheckout()">Proceder al Pago</button>';
    }

    updateCartBadge();
    localStorage.setItem('cartTotal', total.toFixed(2));
}

async function updateQty(detalleId, delta) {
    try {
        if (delta > 0) {
            await apiFetch('/carrito/cliente/' + getUser().id + '/agregar', {
                method: 'POST',
                body: { productoVarianteId: detalleId, cantidad: 1 }
            });
        } else {
            await apiFetch('/carrito/detalle/' + detalleId, { method: 'DELETE' });
        }
        await loadCart();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

async function removeItem(detalleId) {
    try {
        await apiFetch('/carrito/detalle/' + detalleId, { method: 'DELETE' });
        await loadCart();
    } catch(e) {
        alert('Error: ' + e.message);
    }
}

function showCheckout() {
    if (!isLoggedIn()) { window.location.href = 'login.html'; return; }
    const modal = document.getElementById('paymentModal');
    document.getElementById('paymentModalContent').innerHTML =
        '<button class="modal-close" onclick="document.getElementById(\'paymentModal\').classList.remove(\'active\')">&times;</button>' +
        '<h3>Metodo de Pago</h3>' +
        '<div class="payment-methods" id="paymentMethods"></div>' +
        '<div id="paymentMsg"></div>' +
        '<div class="form-actions">' +
        '<button class="btn btn-outline" onclick="document.getElementById(\'paymentModal\').classList.remove(\'active\')">Cancelar</button>' +
        '<button class="btn btn-success" onclick="confirmPurchase()">Confirmar Compra</button>' +
        '</div>';
    modal.classList.add('active');
    loadPaymentMethods();
}

async function loadPaymentMethods() {
    const container = document.getElementById('paymentMethods');
    try {
        const methods = await apiFetch('/metodos-pago');
        container.innerHTML = methods.map(m =>
            '<div class="payment-method" data-id="' + m.idMetodoPago + '" onclick="selectPayment(this)">' +
            '<div class="icon">💳</div>' +
            '<div>' + m.nombre + '</div></div>'
        ).join('');
    } catch(e) {
        container.innerHTML = '<p>Error cargando metodos de pago</p>';
    }
}

let selectedPaymentMethod = null;

function selectPayment(el) {
    document.querySelectorAll('.payment-method').forEach(m => m.classList.remove('selected'));
    el.classList.add('selected');
    selectedPaymentMethod = el.dataset.id;
}

async function confirmPurchase() {
    if (!selectedPaymentMethod) {
        document.getElementById('paymentMsg').innerHTML = '<div class="alert alert-error">Selecciona un metodo de pago</div>';
        return;
    }

    const user = getUser();
    const detalles = cartData.detalles || [];
    if (detalles.length === 0) return;

    try {
        const pedido = await apiFetch('/pedidos/cliente/' + user.id, {
            method: 'POST',
            body: {
                direccion: 'Direccion por defecto',
                detalles: detalles.map(d => ({
                    productoVarianteId: d.productoVarianteId,
                    cantidad: d.cantidad,
                    precioUnitario: d.precio || 0
                }))
            }
        });

        const total = localStorage.getItem('cartTotal') || '0';
        const venta = await apiFetch('/ventas/cliente/' + user.id + '/usuario/' + user.id + '/pedido/' + pedido.idPedido, {
            method: 'POST'
        });

        await apiFetch('/pagos/venta/' + venta.idVenta, {
            method: 'POST',
            body: {
                metodoPagoId: parseInt(selectedPaymentMethod),
                monto: parseFloat(total),
                fecha: new Date().toISOString(),
                transaccionExterna: 'PAGO-' + Date.now()
            }
        });

        alert('Compra realizada con exito!');
        document.getElementById('paymentModal').classList.remove('active');
        await loadCart();
    } catch(e) {
        document.getElementById('paymentMsg').innerHTML = '<div class="alert alert-error">Error: ' + e.message + '</div>';
    }
}
