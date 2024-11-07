package capa_jpa.ejercicio2_implementacion_jpa.repositorios;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByCodigo(String codigo);
}
