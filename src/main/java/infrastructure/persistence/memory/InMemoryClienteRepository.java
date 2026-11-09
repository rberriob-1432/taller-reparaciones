package main.java.infrastructure.persistence.memory;

import main.java.application.port.out.ClienteRepository;
import main.java.domain.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

public class InMemoryClienteRepository implements ClienteRepository {

    private final TreeMap<String, Cliente> clientes = new TreeMap<>();

    @Override
    public void save(Cliente cliente) {

        clientes.put(
                cliente.getIdentificacion(),
                cliente
        );
    }

    @Override
    public Optional<Cliente> findByIdentificacion(String identificacion) {

        return Optional.ofNullable(
                clientes.get(identificacion)
        );
    }

    @Override
    public boolean existsByIdentificacion(String identificacion) {

        return clientes.containsKey(identificacion);
    }

    @Override
    public List<Cliente> findAll() {

        return List.copyOf(clientes.values());
    }
}