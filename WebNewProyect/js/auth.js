async function handleLogin(e) {
    e.preventDefault();
    const usuario = document.getElementById('loginUsuario').value;
    const password = document.getElementById('loginPassword').value;
    const msg = document.getElementById('loginMsg');

    try {
        const res = await fetch(API + '/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ usuario, password })
        });
        if (!res.ok) {
            const err = await res.text();
            msg.innerHTML = '<div class="alert alert-error">' + err + '</div>';
            return;
        }
        const data = await res.json();
        localStorage.setItem('token', data.accessToken);
        localStorage.setItem('user', JSON.stringify(data.usuario));
        window.location.href = isAdmin() ? 'admin.html' : 'index.html';
    } catch (e) {
        msg.innerHTML = '<div class="alert alert-error">Error de conexion: ' + e.message + '</div>';
    }
}

async function handleRegister(e) {
    e.preventDefault();
    const usuario = document.getElementById('regUsuario').value;
    const password = document.getElementById('regPassword').value;
    const msg = document.getElementById('regMsg');

    try {
        const res = await fetch(API + '/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ usuario, password, rolId: 2 })
        });
        if (!res.ok) {
            const err = await res.text();
            msg.innerHTML = '<div class="alert alert-error">' + err + '</div>';
            return;
        }
        msg.innerHTML = '<div class="alert alert-success">Registro exitoso. Ya puedes iniciar sesion.</div>';
        document.getElementById('registerForm').reset();
        setTimeout(() => { showLoginTab(); }, 1500);
    } catch (e) {
        msg.innerHTML = '<div class="alert alert-error">Error de conexion: ' + e.message + '</div>';
    }
}

function showLoginTab() {
    document.querySelectorAll('.auth-tab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.auth-form').forEach(f => f.classList.remove('active'));
    document.querySelector('.auth-tab[data-tab="login"]').classList.add('active');
    document.getElementById('loginForm').classList.add('active');
}

function showRegisterTab() {
    document.querySelectorAll('.auth-tab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.auth-form').forEach(f => f.classList.remove('active'));
    document.querySelector('.auth-tab[data-tab="register"]').classList.add('active');
    document.getElementById('registerForm').classList.add('active');
}
