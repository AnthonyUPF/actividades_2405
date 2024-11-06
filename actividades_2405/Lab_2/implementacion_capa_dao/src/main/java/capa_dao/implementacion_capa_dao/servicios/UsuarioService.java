package capa_dao.implementacion_capa_dao.servicios;

import java.util.List;

import capa_dao.implementacion_capa_dao.modelos.Usuario;
import capa_dao.implementacion_capa_dao.repositorios.UsuarioDAO;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Agregar un nuevo usuario
    public void agregarUsuario(Usuario usuario) {
        usuarioDAO.insert(usuario);
    }

    // Obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.findById(id);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.findAll();
    }

    // Actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(int id) {
        usuarioDAO.delete(id);
    }
}
