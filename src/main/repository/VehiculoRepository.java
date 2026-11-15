package main.repository;

import main.estructuras.HashMap;
import main.model.Vehiculo;

public class VehiculoRepository {
    private final HashMap<String, Vehiculo> vehiculos;

    public VehiculoRepository() {
        this.vehiculos = new HashMap<>();
    }

    public void registrar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser null");
        }

        String placa = vehiculo.getPlaca();

        if (vehiculos.containsKey(placa)) {
            throw new IllegalArgumentException(
                    "Ya existe un vehículo con la placa: " + placa
            );
        }

        vehiculos.put(placa, vehiculo);
    }

    public Vehiculo buscarPorPlaca(String placa) {
        if (placa == null) {
            return null;
        }

        return vehiculos.get(placa.trim().toUpperCase());
    }

    public boolean existe(String placa) {
        if (placa == null) {
            return false;
        }

        return vehiculos.containsKey(placa.trim().toUpperCase());
    }

    public boolean actualizar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser null");
        }

        String placa = vehiculo.getPlaca();

        if (!vehiculos.containsKey(placa)) {
            return false;
        }

        vehiculos.put(placa, vehiculo);
        return true;
    }

    public boolean eliminar(String placa) {
        if (placa == null) {
            return false;
        }

        String placaNormalizada = placa.trim().toUpperCase();

        if (!vehiculos.containsKey(placaNormalizada)) {
            return false;
        }

        vehiculos.remove(placaNormalizada);
        return true;
    }

    public int cantidad() {
        return vehiculos.size();
    }

    public boolean estaVacio() {
        return vehiculos.isEmpty();
    }
}
