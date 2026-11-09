package main.java.application.service;

import main.java.application.port.out.ClienteRepository;
import main.java.domain.model.Cliente;

import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void registrarCliente(Cliente cliente) {

        if (clienteRepository.existsByIdentificacion(
                cliente.getIdentificacion())) {

            throw new IllegalArgumentException(
                    "Ya existe un cliente con esa identificación"
            );
        }

        clienteRepository.save(cliente);
    }

    public Cliente consultarCliente(String identificacion) {

        return clienteRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el cliente"
                ));
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}