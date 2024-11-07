package capa_jpa.ejercicio2_implementacion_jpa.controladores;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Producto;
import capa_jpa.ejercicio2_implementacion_jpa.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            if (producto == null || producto.getNombre() == null || producto.getPrecio() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Datos del producto no válidos o incompletos.");
            }
            Producto nuevoProducto = productoService.crearProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: No se pudo crear el producto.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se encontró un producto con el ID " + id);
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosProductos() {
        List<Producto> productos = productoService.obtenerTodosProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("No hay productos registrados actualmente.");
        }
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        try {
            if (producto == null || producto.getNombre() == null || producto.getPrecio() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Datos del producto no válidos o incompletos.");
            }
            Producto productoExistente = productoService.obtenerProductoPorId(id);
            if (productoExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró un producto con el ID " + id);
            }
            Producto productoActualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: No se pudo actualizar el producto.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: No se pudo eliminar el producto.");
        }
    }
}
