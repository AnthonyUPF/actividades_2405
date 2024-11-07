package capa_jpa.implementacion_jpa.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import capa_jpa.implementacion_jpa.modelos.Usuario;
import capa_jpa.implementacion_jpa.servicios.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> agregarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.agregarUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Retornar el mensaje de error detallado en la respuesta
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>("No se han encontrado usuarios.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
            if (usuarioExistente.isPresent()) {
                usuario.setId(id);
                Usuario usuarioActualizado = usuarioService.actualizarUsuario(usuario);
                return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado con el ID proporcionado para actualización.", HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable int id) {
        try {
            if (usuarioService.obtenerUsuarioPorId(id).isPresent()) {
                usuarioService.eliminarUsuario(id);
                return new ResponseEntity<>("Usuario eliminado con éxito.", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("No se encontró un usuario con el ID proporcionado para eliminar.", HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
