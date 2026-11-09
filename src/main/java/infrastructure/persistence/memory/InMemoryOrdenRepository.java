package main.java.infrastructure.persistence.memory;
import main.java.application.port.out.OrdenRepository;
import main.java.domain.enums.EstadoOrden;
import main.java.domain.model.OrdenServicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class InMemoryOrdenRepository implements OrdenRepository {

    private final Map<String, OrdenServicio> ordenes = new HashMap<>();

    private final PriorityQueue<OrdenServicio> colaPrioridad =
            new PriorityQueue<>();

    @Override
    public void save(OrdenServicio orden) {

        String codigo = orden.getCodigo();

        // Actualizar el almacenamiento principal.
        ordenes.put(codigo, orden);

        // Retirar cualquier versión anterior de la cola.
        colaPrioridad.remove(orden);

        // Solo las órdenes pendientes pueden entrar en la cola.
        if (orden.getEstado() == EstadoOrden.PENDIENTE) {
            colaPrioridad.offer(orden);
        }
    }

    @Override
    public Optional<OrdenServicio> findByCodigo(String codigo) {

        return Optional.ofNullable(
                ordenes.get(codigo.trim().toUpperCase())
        );
    }

    @Override
    public boolean existsByCodigo(String codigo) {

        return ordenes.containsKey(
                codigo.trim().toUpperCase()
        );
    }

    @Override
    public List<OrdenServicio> findAll() {

        return new ArrayList<>(ordenes.values());
    }

    @Override
    public Optional<OrdenServicio> findNextPending() {

        return Optional.ofNullable(
                colaPrioridad.peek()
        );
    }

    @Override
    public boolean existsActiveByVehiclePlate(String placa) {

        String placaNormalizada = placa.trim().toUpperCase();

        return ordenes.values().stream().anyMatch(orden ->

                orden.getVehiculo().getPlaca().equals(placaNormalizada)
                        &&
                        (orden.getEstado() == EstadoOrden.PENDIENTE
                                || orden.getEstado() == EstadoOrden.EN_PROCESO)
        );
    }
}