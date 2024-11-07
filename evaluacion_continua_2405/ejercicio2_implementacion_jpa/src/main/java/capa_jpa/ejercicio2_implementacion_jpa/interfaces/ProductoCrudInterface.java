package capa_jpa.ejercicio2_implementacion_jpa.interfaces;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Producto;
import java.util.List;

public interface ProductoCrudInterface {

    Producto crearProducto(Producto producto);

    Producto obtenerProductoPorId(int id);

    List<Producto> obtenerTodosProductos();

    Producto actualizarProducto(int id, Producto producto);

    void eliminarProducto(int id);
}
