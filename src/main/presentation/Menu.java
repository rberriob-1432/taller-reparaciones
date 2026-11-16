package main.presentation;
import main.estructuras.HashMap;
import main.estructuras.ArrayList;
import main.model.*;
import main.service.*;

import java.util.Scanner;

public class Menu {

    private final VehiculoService vehiculoService;
    private final ClienteService clienteService;
    private final OrdenServicioService ordenService;

    private final Scanner scanner;
    private final HashMap<Integer, Runnable> opciones;

    public Menu(
            VehiculoService vehiculoService,
            ClienteService clienteService,
            OrdenServicioService ordenService) {

        this.vehiculoService = vehiculoService;
        this.clienteService = clienteService;
        this.ordenService = ordenService;

        this.scanner = new Scanner(System.in);
        this.opciones = new HashMap<>();

        configurarOpciones();
    }

    private void configurarOpciones() {
        opciones.put(1, this::registrarVehiculo);
        opciones.put(2, this::buscarVehiculo);
        opciones.put(3, this::registrarCliente);
        opciones.put(4, this::buscarCliente);
        opciones.put(5, this::registrarOrden);
        opciones.put(6, this::buscarOrden);
        opciones.put(7, this::cambiarEstado);
        opciones.put(8, this::cambiarPrioridad);
        opciones.put(9, this::listarOrdenes);
        opciones.put(10, this::eliminarVehiculo);
        opciones.put(11, this::eliminarCliente);
        opciones.put(12, this::eliminarOrden);
        opciones.put(13, this::mostrarCantidades);
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            if (opcion == 0) {
                System.out.println("Sistema finalizado.");
                continue;
            }

            Runnable accion = opciones.get(opcion);

            if (accion == null) {
                System.out.println("Opción no válida.");
                continue;
            }

            try {
                accion.run();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n================================");
        System.out.println("   TALLER DE REPARACION");
        System.out.println("================================");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Buscar vehículo");
        System.out.println("3. Registrar cliente");
        System.out.println("4. Buscar cliente");
        System.out.println("5. Registrar orden");
        System.out.println("6. Buscar orden");
        System.out.println("7. Cambiar estado");
        System.out.println("8. Cambiar prioridad");
        System.out.println("9. Listar órdenes por prioridad");
        System.out.println("10. Eliminar vehículo");
        System.out.println("11. Eliminar cliente");
        System.out.println("12. Eliminar orden");
        System.out.println("13. Mostrar cantidades");
        System.out.println("0. Salir");
    }

    private void registrarVehiculo() {
        String placa = leerTexto("Placa: ");
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerEntero("Año: ");
        String color = leerTexto("Color: ");

        vehiculoService.registrar(
                new Vehiculo(placa, marca, modelo, anio, color)
        );

        System.out.println("Vehículo registrado.");
    }

    private void buscarVehiculo() {
        String placa = leerTexto("Placa: ");
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);

        System.out.println(
                vehiculo == null ? "Vehículo no encontrado." : vehiculo
        );
    }

    private void registrarCliente() {
        String identificacion = leerTexto("Identificación: ");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        String correo = leerTexto("Correo: ");

        clienteService.registrar(
                new Cliente(identificacion, nombre, telefono, correo)
        );

        System.out.println("Cliente registrado.");
    }

    private void buscarCliente() {
        String identificacion = leerTexto("Identificación: ");
        Cliente cliente =
                clienteService.buscarPorIdentificacion(identificacion);

        System.out.println(
                cliente == null ? "Cliente no encontrado." : cliente
        );
    }

    private void registrarOrden() {
        String codigo = leerTexto("Código: ");
        String descripcion = leerTexto("Descripción de la falla: ");

        TipoServicio tipo = seleccionarEnum(
                "Tipo de servicio", TipoServicio.values()
        );

        Prioridad prioridad = seleccionarEnum(
                "Prioridad", Prioridad.values()
        );

        String placa = leerTexto("Placa del vehículo: ");
        String identificacion = leerTexto("Identificación del cliente: ");

        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        Cliente cliente =
                clienteService.buscarPorIdentificacion(identificacion);

        if (vehiculo == null || cliente == null) {
            throw new IllegalArgumentException(
                    "El vehículo o el cliente no están registrados."
            );
        }

        OrdenServicio orden = new OrdenServicio(
                codigo, descripcion, tipo, prioridad, vehiculo, cliente
        );

        ordenService.registrar(orden);

        System.out.println("Orden registrada.");
    }

    private void buscarOrden() {
        String codigo = leerTexto("Código de la orden: ");
        OrdenServicio orden = ordenService.buscarPorCodigo(codigo);

        System.out.println(
                orden == null ? "Orden no encontrada." : orden
        );
    }

    private void cambiarEstado() {
        String codigo = leerTexto("Código de la orden: ");

        EstadoOrden estado = seleccionarEnum(
                "Estado", EstadoOrden.values()
        );

        System.out.println(
                ordenService.cambiarEstado(codigo, estado)
                        ? "Estado actualizado."
                        : "Orden no encontrada."
        );
    }

    private void cambiarPrioridad() {
        String codigo = leerTexto("Código de la orden: ");

        Prioridad prioridad = seleccionarEnum(
                "Prioridad", Prioridad.values()
        );

        System.out.println(
                ordenService.cambiarPrioridad(codigo, prioridad)
                        ? "Prioridad actualizada."
                        : "Orden no encontrada."
        );
    }

    private void listarOrdenes() {
        ArrayList<OrdenServicio> ordenes =
                ordenService.listarPorPrioridad();

        if (ordenes.isEmpty()) {
            System.out.println("No hay órdenes pendientes.");
            return;
        }

        for (int i = 0; i < ordenes.size(); i++) {
            System.out.println((i + 1) + ". " + ordenes.get(i));
        }
    }

    private void eliminarVehiculo() {
        String placa = leerTexto("Placa: ");

        System.out.println(
                vehiculoService.eliminar(placa)
                        ? "Vehículo eliminado."
                        : "Vehículo no encontrado."
        );
    }

    private void eliminarCliente() {
        String identificacion = leerTexto("Identificación: ");

        System.out.println(
                clienteService.eliminar(identificacion)
                        ? "Cliente eliminado."
                        : "Cliente no encontrado."
        );
    }

    private void eliminarOrden() {
        String codigo = leerTexto("Código de la orden: ");

        System.out.println(
                ordenService.eliminar(codigo)
                        ? "Orden eliminada."
                        : "Orden no encontrada."
        );
    }

    private void mostrarCantidades() {
        System.out.println("\n--- RESUMEN ---");
        System.out.println("Vehículos: " + vehiculoService.cantidad());
        System.out.println("Clientes: " + clienteService.cantidad());
        System.out.println("Órdenes: " + ordenService.cantidad());
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Debe ingresar un número entero."
            );
        }
    }

    private <T extends Enum<T>> T seleccionarEnum(
            String titulo, T[] valores) {

        System.out.println("\n" + titulo + ":");

        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ". " + valores[i]);
        }

        int opcion = leerEntero("Seleccione: ");

        if (opcion < 1 || opcion > valores.length) {
            throw new IllegalArgumentException("Selección inválida.");
        }

        return valores[opcion - 1];
    }
}
