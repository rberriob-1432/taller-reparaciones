package main.java.presentation;

import main.java.application.service.ClienteService;
import main.java.domain.model.Cliente;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void registrar(String identificacion, String nombre, String telefono) {

        Cliente cliente = new Cliente(
                identificacion,
                nombre,
                telefono
        );

        clienteService.registrarCliente(cliente);

        System.out.println("Cliente registrado correctamente.");
    }

    public void consultar(String identificacion) {

        Cliente cliente = clienteService.consultarCliente(identificacion);

        System.out.println(cliente);
    }

    public void listar() {

        clienteService.listarClientes().forEach(System.out::println);
    }
    public Cliente obtener(String identificacion) {
        return clienteService.consultarCliente(identificacion);
    }
}