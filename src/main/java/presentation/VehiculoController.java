package main.java.presentation;

import main.java.application.service.VehiculoService;
import main.java.domain.model.Cliente;
import main.java.domain.model.Vehiculo;

public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    public void registrar(
            String placa,
            String marca,
            String modelo,
            Cliente propietario
    ) {

        Vehiculo vehiculo = new Vehiculo(
                placa,
                marca,
                modelo,
                propietario
        );

        vehiculoService.registrarVehiculo(vehiculo);

        System.out.println("Vehículo registrado correctamente.");
    }

    public void consultar(String placa) {

        System.out.println(
                vehiculoService.consultarVehiculo(placa)
        );
    }

    public void listar() {

        vehiculoService.listarVehiculos().forEach(System.out::println);
    }

    public void eliminar(String placa) {

        vehiculoService.eliminarVehiculo(placa);

        System.out.println("Vehículo eliminado correctamente.");
    }
}