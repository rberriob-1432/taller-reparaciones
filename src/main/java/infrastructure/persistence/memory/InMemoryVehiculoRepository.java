package main.java.infrastructure.persistence.memory;
import main.java.application.port.out.VehiculoRepository;
import main.java.domain.model.Vehiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Set;

public class InMemoryVehiculoRepository implements VehiculoRepository {

    // Inventario general de vehículos.
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    // Índice de búsqueda rápida por placa.
    private final Map<String, Vehiculo> vehiculosPorPlaca = new HashMap<>();

    // Control de placas registradas.
    private final Set<String> placasRegistradas = new HashSet<>();

    @Override
    public void save(Vehiculo vehiculo) {

        String placa = normalizarPlaca(vehiculo.getPlaca());

        // Actualizar inventario.
        vehiculos.removeIf(
                existente -> existente.getPlaca().equals(placa)
        );

        vehiculos.add(vehiculo);

        // Actualizar índice.
        vehiculosPorPlaca.put(placa, vehiculo);

        // Actualizar conjunto de placas.
        placasRegistradas.add(placa);
    }

    @Override
    public Optional<Vehiculo> findByPlaca(String placa) {

        return Optional.ofNullable(
                vehiculosPorPlaca.get(normalizarPlaca(placa))
        );
    }

    @Override
    public boolean existsByPlaca(String placa) {

        return placasRegistradas.contains(
                normalizarPlaca(placa)
        );
    }

    @Override
    public List<Vehiculo> findAll() {

        return List.copyOf(vehiculos);
    }

    @Override
    public boolean deleteByPlaca(String placa) {

        String placaNormalizada = normalizarPlaca(placa);

        Vehiculo eliminado = vehiculosPorPlaca.remove(placaNormalizada);

        if (eliminado == null) {
            return false;
        }

        vehiculos.removeIf(
                vehiculo -> vehiculo.getPlaca().equals(placaNormalizada)
        );

        placasRegistradas.remove(placaNormalizada);

        return true;
    }

    private String normalizarPlaca(String placa) {

        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException(
                    "La placa no puede estar vacía"
            );
        }

        return placa.trim().toUpperCase();
    }
}
