package main.java.application.port.out;
import main.java.domain.model.OrdenServicio;
import java.util.List;
import java.util.Optional;

public interface OrdenRepository {

    void save(OrdenServicio orden);

    Optional<OrdenServicio> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    List<OrdenServicio> findAll();

    Optional<OrdenServicio> findNextPending();

    boolean existsActiveByVehiclePlate(String placa);
}