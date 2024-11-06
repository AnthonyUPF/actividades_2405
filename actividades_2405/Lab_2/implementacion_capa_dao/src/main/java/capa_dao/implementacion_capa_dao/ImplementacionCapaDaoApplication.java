package capa_dao.implementacion_capa_dao;

import java.util.List;
import java.util.Scanner;

import capa_dao.implementacion_capa_dao.modelos.Usuario;
import capa_dao.implementacion_capa_dao.servicios.UsuarioService;

public class ImplementacionCapaDaoApplication {

    public static void main(String[] args) {

        // Crear instancia del servicio
        UsuarioService usuarioService = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        // Bucle de operaciones
        while (continuar) {
            System.out.println("\n--- Menú de Operaciones ---");
            System.out.println("1. Agregar usuarios");
            System.out.println("2. Obtener usuario por ID");
            System.out.println("3. Ver todos los usuarios");
            System.out.println("4. Actualizar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Agregar múltiples usuarios
                    System.out.print("¿Cuántos usuarios desea agregar? ");
                    int cantidadUsuarios = scanner.nextInt();
                    for (int i = 0; i < cantidadUsuarios; i++) {
                        System.out.println("Usuario " + (i + 1) + ":");
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.next();
                        System.out.print("Ingrese correo: ");
                        String correo = scanner.next();
                        Usuario nuevoUsuario = new Usuario(nombre, correo);
                        usuarioService.agregarUsuario(nuevoUsuario);
                        System.out.println("Usuario agregado.\n");
                    }
                    break;

                case 2:
                    // Obtener usuario por ID
                    System.out.print("Ingrese ID del usuario: ");
                    int id = scanner.nextInt();
                    Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
                    if (usuario != null) {
                        System.out.println("Usuario encontrado: " + usuario);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 3:
                    // Ver todos los usuarios
                    List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        System.out.println("Lista de usuarios:");
                        usuarios.forEach(System.out::println);
                    }
                    break;

                case 4:
                    // Actualizar usuario
                    System.out.print("Ingrese ID del usuario a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    Usuario usuarioActualizar = usuarioService.obtenerUsuarioPorId(idActualizar);
                    if (usuarioActualizar != null) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.next();
                        System.out.print("Nuevo correo: ");
                        String nuevoCorreo = scanner.next();
                        usuarioActualizar.setNombre(nuevoNombre);
                        usuarioActualizar.setCorreo(nuevoCorreo);
                        usuarioService.actualizarUsuario(usuarioActualizar);
                        System.out.println("Usuario actualizado.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 5:
                    // Eliminar usuario
                    System.out.print("Ingrese ID del usuario a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    try {
                        usuarioService.eliminarUsuario(idEliminar);
                        System.out.println("Usuario eliminado.");
                    } catch (Exception e) {
                        System.out.println("No se pudo eliminar el usuario. Asegúrese de que el ID sea correcto.");
                    }
                    break;

                case 6:
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
