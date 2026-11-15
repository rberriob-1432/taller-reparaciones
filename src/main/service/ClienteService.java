package main.service;

import main.model.Cliente;
import main.repository.ClienteRepository;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("El repositorio no puede ser null");
        }
        this.repository = repository;
    }

    public void registrar(Cliente cliente) {
        repository.registrar(cliente);
    }

    public Cliente buscarPorIdentificacion(String identificacion) {
        return repository.buscarPorIdentificacion(identificacion);
    }

    public boolean actualizar(Cliente cliente) {
        return repository.actualizar(cliente);
    }

    public boolean eliminar(String identificacion) {
        return repository.eliminar(identificacion);
    }

    public int cantidad() {
        return repository.cantidad();
    }

    public boolean existe(String identificacion) {
        return repository.existe(identificacion);
    }
}
