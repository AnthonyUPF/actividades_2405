package capa_jpa.ejercicio2_implementacion_jpa.servicios;

import capa_jpa.ejercicio2_implementacion_jpa.interfaces.ClienteCrudInterface;
import capa_jpa.ejercicio2_implementacion_jpa.modelos.Cliente;
import capa_jpa.ejercicio2_implementacion_jpa.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteCrudInterface {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (cliente == null || cliente.getCorreo() == null || cliente.getNombre() == null) {
            throw new IllegalArgumentException("Error: Datos del cliente incompletos.");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente actualizarCliente(int id, Cliente cliente) {
        if (cliente == null || cliente.getCorreo() == null || cliente.getNombre() == null) {
            throw new IllegalArgumentException("Error: Datos del cliente incompletos o inválidos.");
        }

        Cliente clienteExistente = obtenerClientePorId(id);
        if (clienteExistente == null) {
            throw new IllegalArgumentException("Error: Cliente no encontrado para actualización.");
        }

        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(int id) {
        Cliente clienteExistente = obtenerClientePorId(id);
        if (clienteExistente == null) {
            throw new IllegalArgumentException("Error: Cliente no encontrado para eliminación.");
        }
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> buscarPorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }
}
