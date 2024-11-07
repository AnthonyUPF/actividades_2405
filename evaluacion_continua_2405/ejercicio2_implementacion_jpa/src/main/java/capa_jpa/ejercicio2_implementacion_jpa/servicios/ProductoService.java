package capa_jpa.ejercicio2_implementacion_jpa.servicios;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Producto;
import capa_jpa.ejercicio2_implementacion_jpa.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        if (producto == null || producto.getNombre() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("Error: Datos del producto no válidos.");
        }
        return productoRepository.save(producto);
    }

    // Obtener un producto por ID
    public Producto obtenerProductoPorId(int id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(int id, Producto producto) {
        if (producto == null || producto.getNombre() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("Error: Datos del producto no válidos.");
        }

        Producto productoExistente = obtenerProductoPorId(id);
        if (productoExistente == null) {
            throw new IllegalArgumentException("Error: Producto no encontrado para actualización.");
        }

        producto.setId(id);  // Aseguramos que se actualice el ID
        return productoRepository.save(producto);
    }

    // Eliminar un producto
    public void eliminarProducto(int id) {
        Producto productoExistente = obtenerProductoPorId(id);
        if (productoExistente == null) {
            throw new IllegalArgumentException("Error: Producto no encontrado para eliminación.");
        }
        productoRepository.deleteById(id);
    }
}
