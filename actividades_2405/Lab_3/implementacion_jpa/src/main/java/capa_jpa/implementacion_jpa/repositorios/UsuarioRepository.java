package capa_jpa.implementacion_jpa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import capa_jpa.implementacion_jpa.modelos.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Método para encontrar un usuario por su correo
    Optional<Usuario> findByCorreo(String correo);
}
