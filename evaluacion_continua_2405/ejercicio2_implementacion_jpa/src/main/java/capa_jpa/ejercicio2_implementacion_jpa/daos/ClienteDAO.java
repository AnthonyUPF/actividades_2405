package capa_jpa.ejercicio2_implementacion_jpa.daos;

import capa_jpa.ejercicio2_implementacion_jpa.modelos.Cliente;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Cliente cliente) {
        entityManager.persist(cliente);
    }

    public Cliente getById(int id) {
        return entityManager.find(Cliente.class, id);
    }

    public List<Cliente> getAll() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public void update(Cliente cliente) {
        entityManager.merge(cliente);
    }

    public void delete(int id) {
        Cliente cliente = getById(id);
        if (cliente != null) {
            entityManager.remove(cliente);
        }
    }
}
