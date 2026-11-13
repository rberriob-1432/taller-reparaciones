package test.java.presentation;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private final InputStream entradaOriginal = System.in;
    private final PrintStream salidaOriginal = System.out;

    private final ByteArrayOutputStream salida =
            new ByteArrayOutputStream();

    private Menu crearMenu(String entrada) {
        System.setIn(new ByteArrayInputStream(
                entrada.getBytes(StandardCharsets.UTF_8)
        ));

        System.setOut(new PrintStream(salida));

        InMemoryClienteRepository clienteRepository =
                new InMemoryClienteRepository();

        InMemoryVehiculoRepository vehiculoRepository =
                new InMemoryVehiculoRepository();

        InMemoryOrdenRepository ordenRepository =
                new InMemoryOrdenRepository();

        ClienteService clienteService =
                new ClienteService(clienteRepository);

        VehiculoService vehiculoService =
                new VehiculoService(
                        vehiculoRepository,
                        clienteRepository,
                        ordenRepository
                );

        OrdenService ordenService =
                new OrdenService(
                        ordenRepository,
                        vehiculoRepository
                );

        return new Menu(
                new ClienteController(clienteService),
                new VehiculoController(vehiculoService),
                new OrdenController(ordenService)
        );
    }

    @Test
    void debeMostrarElMenuYFinalizarElSistema() {
        Menu menu = crearMenu("0\n");

        menu.iniciar();

        assertTrue(salida.toString().contains(
                "TALLER DE REPARACIÓN"
        ));

        assertTrue(salida.toString().contains(
                "Sistema finalizado."
        ));
    }

    @Test
    void debeRegistrarUnClienteDesdeElMenu() {
        Menu menu = crearMenu(
                "1\n123\nRaul\n3001234567\n0\n"
        );

        menu.iniciar();

        assertTrue(salida.toString().contains(
                "Cliente registrado correctamente."
        ));
    }

    @Test
    void debeMostrarErrorCuandoLaOpcionNoEsValida() {
        Menu menu = crearMenu("99\n0\n");

        menu.iniciar();

        assertTrue(salida.toString().contains(
                "Opción no válida."
        ));
    }

    @Test
    void debeSolicitarNuevamenteLaOpcionCuandoNoEsUnNumero() {
        Menu menu = crearMenu("abc\n0\n");

        menu.iniciar();

        assertTrue(salida.toString().contains(
                "Debe ingresar un número válido."
        ));

        assertTrue(salida.toString().contains(
                "Sistema finalizado."
        ));
    }

    @AfterEach
    void restaurarConsola() {
        System.setIn(entradaOriginal);
        System.setOut(salidaOriginal);
    }
}