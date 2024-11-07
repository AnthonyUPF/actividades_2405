package capa_jpa.ejercicio2_implementacion_jpa.controladores;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Cliente;
import capa_jpa.ejercicio2_implementacion_jpa.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        // Verificar si el correo ya está registrado antes de enviar al servicio
        Optional<Cliente> clienteExistente = clienteService.buscarPorCorreo(cliente.getCorreo());
        if (clienteExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: El correo " + cliente.getCorreo() + " ya está registrado en el sistema.");
        }

        try {
            Cliente nuevoCliente = clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: No se pudo crear el cliente. Intente más tarde.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable int id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se encontró un cliente con el ID " + id);
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("No hay clientes registrados actualmente.");
        }
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: Los datos proporcionados no son válidos para la actualización.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: No se pudo actualizar el cliente.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable int id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: No se encontró un cliente con el ID " + id + " para eliminar.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: No se pudo eliminar el cliente.");
        }
    }
}
