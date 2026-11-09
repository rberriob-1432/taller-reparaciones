package main.java.application.service;
import main.java.application.port.out.OrdenRepository;
import main.java.application.port.out.VehiculoRepository;
import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;
import main.java.domain.model.OrdenServicio;
import main.java.domain.model.Vehiculo;

import java.util.List;

public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final VehiculoRepository vehiculoRepository;

    public OrdenService(
            OrdenRepository ordenRepository,
            VehiculoRepository vehiculoRepository
    ) {
        this.ordenRepository = ordenRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public void registrarOrden(
            String codigo,
            String descripcion,
            TipoServicio tipoServicio,
            Prioridad prioridad,
            String placa
    ) {

        if (ordenRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException(
                    "Ya existe una orden con ese código"
            );
        }

        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El vehículo no está registrado"
                ));

        OrdenServicio orden = new OrdenServicio(
                codigo,
                descripcion,
                tipoServicio,
                prioridad,
                vehiculo
        );

        ordenRepository.save(orden);
    }

    public OrdenServicio consultarOrden(String codigo) {

        return ordenRepository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró la orden"
                ));
    }

    public List<OrdenServicio> listarOrdenes() {
        return ordenRepository.findAll();
    }

    public OrdenServicio procesarSiguienteOrden() {

        OrdenServicio orden = ordenRepository.findNextPending()
                .orElseThrow(() -> new IllegalStateException(
                        "No existen órdenes pendientes"
                ));

        orden.iniciarServicio();

        ordenRepository.save(orden);

        return orden;
    }

    public void cambiarPrioridad(String codigo, Prioridad prioridad) {

        OrdenServicio orden = consultarOrden(codigo);

        if (orden.getEstado() != main.java.domain.enums.EstadoOrden.PENDIENTE) {
            throw new IllegalStateException(
                    "Solo se puede cambiar la prioridad de órdenes pendientes"
            );
        }

        orden.cambiarPrioridad(prioridad);

        ordenRepository.save(orden);
    }

    public void completarOrden(String codigo) {

        OrdenServicio orden = consultarOrden(codigo);

        orden.completarServicio();

        ordenRepository.save(orden);
    }

    public void cancelarOrden(String codigo) {

        OrdenServicio orden = consultarOrden(codigo);

        orden.cancelarServicio();

        ordenRepository.save(orden);
    }
}