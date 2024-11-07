package capa_dao.ejercicio1_capa_dao;

import java.util.List;
import java.util.Scanner;

import capa_dao.ejercicio1_capa_dao.modelos.Producto;
import capa_dao.ejercicio1_capa_dao.modelos.Cliente;
import capa_dao.ejercicio1_capa_dao.daos.ProductoDAO;
import capa_dao.ejercicio1_capa_dao.daos.ClienteDAO;

public class Ejercicio1CapaDaoApplication {

    public static void main(String[] args) {

        // Crear instancias de los DAOs
        ProductoDAO productoDAO = new ProductoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // Bucle de operaciones
        while (continuar) {
            System.out.println("\n--- Menú de Operaciones ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Obtener producto por ID");
            System.out.println("3. Ver todos los productos");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Agregar cliente");
            System.out.println("7. Obtener cliente por ID");
            System.out.println("8. Ver todos los clientes");
            System.out.println("9. Actualizar cliente");
            System.out.println("10. Eliminar cliente");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Agregar producto
                    System.out.print("Ingrese nombre del producto: ");
                    String nombreProducto = scanner.next();
                    System.out.print("Ingrese precio del producto: ");
                    double precioProducto = scanner.nextDouble();
                    Producto nuevoProducto = new Producto(nombreProducto, precioProducto);
                    productoDAO.create(nuevoProducto);
                    System.out.println("Producto agregado.\n");
                    break;

                case 2:
                    // Obtener producto por ID
                    System.out.print("Ingrese ID del producto: ");
                    int idProducto = scanner.nextInt();
                    Producto producto = productoDAO.getById(idProducto);
                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    // Ver todos los productos
                    List<Producto> productos = productoDAO.getAll();
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        System.out.println("Lista de productos:");
                        productos.forEach(System.out::println);
                    }
                    break;

                case 4:
                    // Actualizar producto
                    System.out.print("Ingrese ID del producto a actualizar: ");
                    int idActualizarProducto = scanner.nextInt();
                    Producto productoActualizar = productoDAO.getById(idActualizarProducto);
                    if (productoActualizar != null) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombreProducto = scanner.next();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecioProducto = scanner.nextDouble();
                        productoActualizar.setNombre(nuevoNombreProducto);
                        productoActualizar.setPrecio(nuevoPrecioProducto);
                        productoDAO.update(productoActualizar);
                        System.out.println("Producto actualizado.");
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 5:
                    // Eliminar producto
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    int idEliminarProducto = scanner.nextInt();
                    try {
                        productoDAO.delete(idEliminarProducto);
                        System.out.println("Producto eliminado.");
                    } catch (Exception e) {
                        System.out.println("No se pudo eliminar el producto. Asegúrese de que el ID sea correcto.");
                    }
                    break;

                case 6:
                    // Agregar cliente
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombreCliente = scanner.next();
                    System.out.print("Ingrese correo del cliente: ");
                    String correoCliente = scanner.next();
                    Cliente nuevoCliente = new Cliente(nombreCliente, correoCliente);
                    clienteDAO.create(nuevoCliente);
                    System.out.println("Cliente agregado.\n");
                    break;

                case 7:
                    // Obtener cliente por ID
                    System.out.print("Ingrese ID del cliente: ");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = clienteDAO.getById(idCliente);
                    if (cliente != null) {
                        System.out.println("Cliente encontrado: " + cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 8:
                    // Ver todos los clientes
                    List<Cliente> clientes = clienteDAO.getAll();
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        System.out.println("Lista de clientes:");
                        clientes.forEach(System.out::println);
                    }
                    break;

                case 9:
                    // Actualizar cliente
                    System.out.print("Ingrese ID del cliente a actualizar: ");
                    int idActualizarCliente = scanner.nextInt();
                    Cliente clienteActualizar = clienteDAO.getById(idActualizarCliente);
                    if (clienteActualizar != null) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombreCliente = scanner.next();
                        System.out.print("Nuevo correo: ");
                        String nuevoCorreoCliente = scanner.next();
                        clienteActualizar.setNombre(nuevoNombreCliente);
                        clienteActualizar.setCorreo(nuevoCorreoCliente);
                        clienteDAO.update(clienteActualizar);
                        System.out.println("Cliente actualizado.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 10:
                    // Eliminar cliente
                    System.out.print("Ingrese ID del cliente a eliminar: ");
                    int idEliminarCliente = scanner.nextInt();
                    try {
                        clienteDAO.delete(idEliminarCliente);
                        System.out.println("Cliente eliminado.");
                    } catch (Exception e) {
                        System.out.println("No se pudo eliminar el cliente. Asegúrese de que el ID sea correcto.");
                    }
                    break;

                case 11:
                    // Salir del menú
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
