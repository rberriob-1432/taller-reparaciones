package main.java.presentation;

import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;
import main.java.domain.model.Cliente;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteController clienteController;
    private final VehiculoController vehiculoController;
    private final OrdenController ordenController;

    public Menu(
            ClienteController clienteController,
            VehiculoController vehiculoController,
            OrdenController ordenController
    ) {
        this.clienteController = clienteController;
        this.vehiculoController = vehiculoController;
        this.ordenController = ordenController;
    }

    public void iniciar() {

        int opcion;

        do {
            mostrarMenu();

            opcion = leerEntero("Seleccione una opción: ");

            try {
                ejecutarOpcion(opcion);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);

        scanner.close();

        System.out.println("Sistema finalizado.");
    }

    private void mostrarMenu() {

        System.out.println("\n====================================");
        System.out.println("     TALLER DE REPARACIÓN");
        System.out.println("====================================");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Consultar cliente");
        System.out.println("3. Listar clientes");
        System.out.println("------------------------------------");
        System.out.println("4. Registrar vehículo");
        System.out.println("5. Consultar vehículo");
        System.out.println("6. Listar vehículos");
        System.out.println("7. Eliminar vehículo");
        System.out.println("------------------------------------");
        System.out.println("8. Registrar orden de servicio");
        System.out.println("9. Consultar orden");
        System.out.println("10. Listar órdenes");
        System.out.println("11. Procesar siguiente reparación");
        System.out.println("12. Cambiar prioridad");
        System.out.println("13. Completar orden");
        System.out.println("14. Cancelar orden");
        System.out.println("------------------------------------");
        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    private void ejecutarOpcion(int opcion) {

        switch (opcion) {

            case 1 -> registrarCliente();

            case 2 -> consultarCliente();

            case 3 -> clienteController.listar();

            case 4 -> registrarVehiculo();

            case 5 -> vehiculoController.consultar(
                    leerTexto("Ingrese la placa: ")
            );

            case 6 -> vehiculoController.listar();

            case 7 -> vehiculoController.eliminar(
                    leerTexto("Ingrese la placa: ")
            );

            case 8 -> registrarOrden();

            case 9 -> ordenController.consultar(
                    leerTexto("Ingrese el código: ")
            );

            case 10 -> ordenController.listar();

            case 11 -> ordenController.procesarSiguiente();

            case 12 -> cambiarPrioridad();

            case 13 -> ordenController.completar(
                    leerTexto("Ingrese el código: ")
            );

            case 14 -> ordenController.cancelar(
                    leerTexto("Ingrese el código: ")
            );

            case 0 -> System.out.println("Cerrando sistema...");

            default -> System.out.println("Opción no válida.");
        }
    }

    private void registrarCliente() {

        String identificacion = leerTexto("Identificación: ");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");

        clienteController.registrar(
                identificacion,
                nombre,
                telefono
        );
    }

    private void consultarCliente() {

        String identificacion = leerTexto("Identificación: ");

        clienteController.consultar(identificacion);
    }

    private void registrarVehiculo() {

        String placa = leerTexto("Placa: ");
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");

        String identificacion = leerTexto(
                "Identificación del propietario: "
        );

        Cliente propietario = clienteController.obtener(identificacion);

        vehiculoController.registrar(
                placa,
                marca,
                modelo,
                propietario
        );
    }

    private void registrarOrden() {

        String codigo = leerTexto("Código de la orden: ");

        String descripcion = leerTexto("Descripción de la falla: ");

        TipoServicio tipo = seleccionarEnum(
                TipoServicio.class,
                "Seleccione el tipo de servicio:"
        );

        Prioridad prioridad = seleccionarEnum(
                Prioridad.class,
                "Seleccione la prioridad:"
        );

        String placa = leerTexto("Placa del vehículo: ");

        ordenController.registrar(
                codigo,
                descripcion,
                tipo,
                prioridad,
                placa
        );
    }

    private void cambiarPrioridad() {

        String codigo = leerTexto("Código de la orden: ");

        Prioridad prioridad = seleccionarEnum(
                Prioridad.class,
                "Nueva prioridad:"
        );

        ordenController.cambiarPrioridad(codigo, prioridad);
    }

    private int leerEntero(String mensaje) {

        while (true) {

            try {
                System.out.print(mensaje);

                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private String leerTexto(String mensaje) {

        System.out.print(mensaje);

        return scanner.nextLine().trim();
    }

    private <T extends Enum<T>> T seleccionarEnum(
            Class<T> tipoEnum,
            String mensaje
    ) {

        T[] valores = tipoEnum.getEnumConstants();

        System.out.println("\n" + mensaje);

        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ". " + valores[i]);
        }

        while (true) {

            int opcion = leerEntero("Seleccione: ");

            if (opcion >= 1 && opcion <= valores.length) {
                return valores[opcion - 1];
            }

            System.out.println("Selección no válida.");
        }
    }
}