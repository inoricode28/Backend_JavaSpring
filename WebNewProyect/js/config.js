const API = 'http://localhost:9090/newproject';

function getToken() {
    return localStorage.getItem('token');
}

function getUser() {
    const u = localStorage.getItem('user');
    return u ? JSON.parse(u) : null;
}

function isAdmin() {
    const user = getUser();
    return user && user.rol && user.rol.toUpperCase() === 'ADMIN';
}

function isLoggedIn() {
    return !!getToken();
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = 'login.html';
}

function apiHeaders(body) {
    const h = {};
    if (!(body instanceof FormData)) {
        h['Content-Type'] = 'application/json';
    }
    const t = getToken();
    if (t) h['Authorization'] = 'Bearer ' + t;
    return h;
}

async function apiFetch(url, options = {}) {
    const config = {
        ...options,
        headers: { ...apiHeaders(options.body), ...(options.headers || {}) },
    };
    if (options.body && typeof options.body === 'object' && !(options.body instanceof FormData)) {
        config.body = JSON.stringify(options.body);
    }
    const res = await fetch(API + url, config);
    if (!res.ok) {
        const err = await res.text();
        throw new Error(err || 'Error en la peticion');
    }
    if (res.status === 204) return null;
    return res.json();
}
