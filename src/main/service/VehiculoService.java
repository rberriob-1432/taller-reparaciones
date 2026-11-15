package main.service;

import main.model.Vehiculo;
import main.repository.VehiculoRepository;

public class VehiculoService {

    private final VehiculoRepository repository;

    public VehiculoService(VehiculoRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("El repositorio no puede ser null");
        }
        this.repository = repository;
    }

    public void registrar(Vehiculo vehiculo) {
        repository.registrar(vehiculo);
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return repository.buscarPorPlaca(placa);
    }

    public boolean actualizar(Vehiculo vehiculo) {
        return repository.actualizar(vehiculo);
    }

    public boolean eliminar(String placa) {
        return repository.eliminar(placa);
    }

    public int cantidad() {
        return repository.cantidad();
    }

    public boolean existe(String placa) {
        return repository.existe(placa);
    }
}
