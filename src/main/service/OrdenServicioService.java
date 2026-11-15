package main.service;

import main.estructuras.ArrayList;
import main.model.EstadoOrden;
import main.model.OrdenServicio;
import main.model.Prioridad;
import main.repository.ClienteRepository;
import main.repository.OrdenServicioRepository;
import main.repository.VehiculoRepository;

public class OrdenServicioService {

    private final OrdenServicioRepository ordenRepository;
    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public OrdenServicioService(
            OrdenServicioRepository ordenRepository,
            VehiculoRepository vehiculoRepository,
            ClienteRepository clienteRepository) {

        if (ordenRepository == null || vehiculoRepository == null
                || clienteRepository == null) {
            throw new IllegalArgumentException(
                    "Los repositorios no pueden ser null"
            );
        }

        this.ordenRepository = ordenRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public void registrar(OrdenServicio orden) {
        validarReferencias(orden);
        ordenRepository.registrar(orden);
    }

    public OrdenServicio buscarPorCodigo(String codigo) {
        return ordenRepository.buscarPorCodigo(codigo);
    }

    public boolean actualizar(OrdenServicio orden) {
        validarReferencias(orden);
        return ordenRepository.actualizar(orden);
    }

    public boolean eliminar(String codigo) {
        return ordenRepository.eliminar(codigo);
    }

    public boolean cambiarEstado(String codigo, EstadoOrden estado) {
        OrdenServicio orden = buscarPorCodigo(codigo);

        if (orden == null) {
            return false;
        }

        orden.setEstado(estado);
        return ordenRepository.actualizar(orden);
    }

    public boolean cambiarPrioridad(String codigo, Prioridad prioridad) {
        OrdenServicio orden = buscarPorCodigo(codigo);

        if (orden == null) {
            return false;
        }

        orden.setPrioridad(prioridad);
        return ordenRepository.actualizar(orden);
    }

    public ArrayList<OrdenServicio> listarPorPrioridad() {
        return ordenRepository.listarPorPrioridad();
    }

    public int cantidad() {
        return ordenRepository.cantidad();
    }

    private void validarReferencias(OrdenServicio orden) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser null");
        }

        String placa = orden.getVehiculo().getPlaca();
        String identificacion = orden.getCliente().getIdentificacion();

        if (!vehiculoRepository.existe(placa)) {
            throw new IllegalArgumentException(
                    "El vehículo no está registrado: " + placa
            );
        }

        if (!clienteRepository.existe(identificacion)) {
            throw new IllegalArgumentException(
                    "El cliente no está registrado: " + identificacion
            );
        }
    }
}
