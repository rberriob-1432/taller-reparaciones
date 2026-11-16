package main;
import main.presentation.Menu;
import main.repository.*;
import main.service.*;

public class Main {

    public static void main(String[] args) {

        VehiculoRepository vehiculoRepository =
                new VehiculoRepository();

        ClienteRepository clienteRepository =
                new ClienteRepository();

        OrdenServicioRepository ordenRepository =
                new OrdenServicioRepository();

        VehiculoService vehiculoService =
                new VehiculoService(vehiculoRepository);

        ClienteService clienteService =
                new ClienteService(clienteRepository);

        OrdenServicioService ordenService =
                new OrdenServicioService(
                        ordenRepository,
                        vehiculoRepository,
                        clienteRepository
                );

        Menu menu = new Menu(
                vehiculoService,
                clienteService,
                ordenService
        );

        menu.iniciar();
    }
}
