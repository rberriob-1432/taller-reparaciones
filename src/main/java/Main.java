package main.java;
import main.java.application.service.ClienteService;
import main.java.application.service.OrdenService;
import main.java.application.service.VehiculoService;
import main.java.infrastructure.persistence.memory.InMemoryClienteRepository;
import main.java.infrastructure.persistence.memory.InMemoryOrdenRepository;
import main.java.infrastructure.persistence.memory.InMemoryVehiculoRepository;
import main.java.presentation.ClienteController;
import main.java.presentation.Menu;
import main.java.presentation.OrdenController;
import main.java.presentation.VehiculoController;

public class Main {

    public static void main(String[] args) {

        // =====================================
        // 1. INFRASTRUCTURE
        // =====================================

        var clienteRepository = new InMemoryClienteRepository();

        var vehiculoRepository = new InMemoryVehiculoRepository();

        var ordenRepository = new InMemoryOrdenRepository();


        // =====================================
        // 2. APPLICATION
        // =====================================

        var clienteService = new ClienteService(
                clienteRepository
        );

        var vehiculoService = new VehiculoService(
                vehiculoRepository,
                clienteRepository,
                ordenRepository
        );

        var ordenService = new OrdenService(
                ordenRepository,
                vehiculoRepository
        );


        // =====================================
        // 3. PRESENTATION
        // =====================================

        var clienteController = new ClienteController(
                clienteService
        );

        var vehiculoController = new VehiculoController(
                vehiculoService
        );

        var ordenController = new OrdenController(
                ordenService
        );


        // =====================================
        // 4. EJECUCIÓN
        // =====================================

        Menu menu = new Menu(
                clienteController,
                vehiculoController,
                ordenController
        );

        menu.iniciar();
    }
}
