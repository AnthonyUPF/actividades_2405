package capa_jpa.implementacion_jpa.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capa_jpa.implementacion_jpa.modelos.Usuario;
import capa_jpa.implementacion_jpa.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para agregar un usuario
    public Usuario agregarUsuario(Usuario usuario) {
        // Verificar si ya existe un usuario con el mismo correo
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado. Por favor, use otro correo.");
        }

        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario. Intente nuevamente más tarde. Detalles: " + e.getMessage());
        }
    }

    // Obtener usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(int id) {
        try {
            return usuarioRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario por ID. Detalles: " + e.getMessage());
        }
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de usuarios. Detalles: " + e.getMessage());
        }
    }

    // Actualizar un usuario
    public Usuario actualizarUsuario(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario. Detalles: " + e.getMessage());
        }
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(int id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario. Detalles: " + e.getMessage());
        }
    }
}
