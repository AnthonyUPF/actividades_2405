const contadorElement = document.getElementById('contador');

async function obtenerContador() {
    const response = await fetch('/api/contador');
    const data = await response.json();
    contadorElement.textContent = data;
}

async function incrementar() {
    const response = await fetch('/api/contador/incrementar', { method: 'POST' });
    const data = await response.json();
    contadorElement.textContent = data;
}

async function decrementar() {
    const response = await fetch('/api/contador/decrementar', { method: 'POST' });
    const data = await response.json();
    contadorElement.textContent = data;
}

// Llama a obtenerContador al cargar la página
obtenerContador();
