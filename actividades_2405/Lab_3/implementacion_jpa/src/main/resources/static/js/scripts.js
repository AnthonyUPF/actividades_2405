document.addEventListener('DOMContentLoaded', () => {
    const usuarioForm = document.getElementById('usuarioForm');
    const usuariosDiv = document.getElementById('usuarios');
    const mensajeDiv = document.getElementById('mensaje');  // Div para mostrar mensajes
    let usuarioEdicion = null;

    // Función para obtener usuarios
    function obtenerUsuarios() {
        fetch('/api/usuarios')
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        mostrarMensaje(data || 'Error al cargar los usuarios. Intente nuevamente más tarde.', 'error');
                    });
                }
                return response.json();
            })
            .then(usuarios => {
                usuariosDiv.innerHTML = '';
                usuarios.forEach(usuario => {
                    const div = document.createElement('div');
                    div.innerHTML = `
                        <p>${usuario.nombre} - ${usuario.correo}</p>
                        <button onclick="eliminarUsuario(${usuario.id})">Eliminar</button>
                        <button onclick="editarUsuario(${usuario.id})">Editar</button>
                    `;
                    usuariosDiv.appendChild(div);
                });
            })
            .catch(error => {
                console.error('Error al obtener usuarios:', error);
                mostrarMensaje('Error al obtener usuarios. Intente nuevamente.', 'error');
            });
    }

    // Mostrar mensaje de éxito o error
    function mostrarMensaje(mensaje, tipo) {
        mensajeDiv.textContent = mensaje;
        mensajeDiv.className = `message ${tipo}`;  // Aplica clase de éxito o error
        mensajeDiv.style.display = 'block';  // Muestra el mensaje
        setTimeout(() => {
            mensajeDiv.style.display = 'none';  // Oculta el mensaje después de 3 segundos
        }, 3000);
    }

    // Agregar o actualizar usuario
    usuarioForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const nombre = document.getElementById('nombre').value;
        const correo = document.getElementById('correo').value;

        // Limpiar cualquier mensaje de error anterior
        mensajeDiv.style.display = 'none';

        if (!nombre || !correo) {
            mostrarMensaje('Por favor, complete todos los campos.', 'error');
            return;
        }

        if (usuarioEdicion) {
            // Actualizar usuario
            fetch(`/api/usuarios/${usuarioEdicion.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nombre, correo })
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        mostrarMensaje(data || 'Error al actualizar el usuario. Intente nuevamente más tarde.', 'error');
                    });
                }
                return response.json();
            })
            .then(() => {
                obtenerUsuarios();
                usuarioForm.reset();
                usuarioEdicion = null;  // Resetear la variable de edición
                mostrarMensaje('Usuario actualizado con éxito.', 'success');
            })
            .catch(error => {
                console.error('Error al actualizar usuario:', error);
                mostrarMensaje('Error al actualizar el usuario. Inténtalo nuevamente.', 'error');
            });
        } else {
            // Agregar nuevo usuario
            fetch('/api/usuarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nombre, correo })
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        mostrarMensaje(data || 'Error al agregar usuario. Intenta nuevamente más tarde.', 'error');
                    });
                }
                return response.json();
            })
            .then(() => {
                obtenerUsuarios();
                usuarioForm.reset();
                mostrarMensaje('Usuario agregado con éxito.', 'success');
            })
            .catch(error => {
                console.error('Error al agregar usuario:', error);
                mostrarMensaje('Error al agregar usuario. Inténtalo nuevamente.', 'error');
            });
        }
    });

    // Eliminar usuario
    window.eliminarUsuario = function (id) {
        fetch(`/api/usuarios/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    mostrarMensaje(data.message || 'Error al eliminar el usuario.', 'error');
                });
            }
            obtenerUsuarios();
            mostrarMensaje('Usuario eliminado con éxito.', 'success');
        })
        .catch(error => {
            console.error('Error al eliminar usuario:', error);
            mostrarMensaje('Error al eliminar el usuario. Intenta nuevamente.', 'error');
        });
    };

    // Función para editar un usuario
    window.editarUsuario = function (id) {
        fetch(`/api/usuarios/${id}`)
            .then(response => response.json())
            .then(usuario => {
                document.getElementById('nombre').value = usuario.nombre;
                document.getElementById('correo').value = usuario.correo;
                usuarioEdicion = usuario; // Guardar usuario en modo edición
            })
            .catch(error => {
                console.error('Error al obtener usuario para editar:', error);
                mostrarMensaje('Error al obtener datos del usuario. Intenta nuevamente.', 'error');
            });
    };

    // Cargar lista inicial de usuarios
    obtenerUsuarios();
});
