package capa_jpa.ejercicio2_implementacion_jpa.repositorios;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCorreo(String correo);  // Método para buscar cliente por correo
}
