package main.java.application.service;

import main.java.application.port.out.ClienteRepository;
import main.java.application.port.out.OrdenRepository;
import main.java.application.port.out.VehiculoRepository;
import main.java.domain.model.Cliente;
import main.java.domain.model.Vehiculo;

import java.util.List;

public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;
    private final OrdenRepository ordenRepository;

    public VehiculoService(
            VehiculoRepository vehiculoRepository,
            ClienteRepository clienteRepository,
            OrdenRepository ordenRepository
    ) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
        this.ordenRepository = ordenRepository;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {

        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new IllegalArgumentException(
                    "Ya existe un vehículo con esa placa"
            );
        }

        Cliente propietario = clienteRepository
                .findByIdentificacion(
                        vehiculo.getPropietario().getIdentificacion()
                )
                .orElseThrow(() -> new IllegalArgumentException(
                        "El propietario no está registrado"
                ));

        Vehiculo nuevoVehiculo = new Vehiculo(
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                propietario
        );

        vehiculoRepository.save(nuevoVehiculo);
    }

    public Vehiculo consultarVehiculo(String placa) {

        return vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el vehículo"
                ));
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    public void eliminarVehiculo(String placa) {

        consultarVehiculo(placa);

        if (ordenRepository.existsActiveByVehiclePlate(placa)) {
            throw new IllegalStateException(
                    "No se puede eliminar un vehículo con órdenes activas"
            );
        }

        vehiculoRepository.deleteByPlaca(placa);
    }
}