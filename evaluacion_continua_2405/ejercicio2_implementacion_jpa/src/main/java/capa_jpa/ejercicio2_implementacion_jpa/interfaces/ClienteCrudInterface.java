package capa_jpa.ejercicio2_implementacion_jpa.interfaces;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Cliente;
import java.util.List;

public interface ClienteCrudInterface {

    Cliente crearCliente(Cliente cliente);

    Cliente obtenerClientePorId(int id);

    List<Cliente> obtenerTodosClientes();

    Cliente actualizarCliente(int id, Cliente cliente);

    void eliminarCliente(int id);
}
