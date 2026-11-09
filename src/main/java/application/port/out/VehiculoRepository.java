package main.java.application.port.out;

import main.java.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository {

    void save(Vehiculo vehiculo);

    Optional<Vehiculo> findByPlaca(String placa);

    boolean existsByPlaca(String placa);

    List<Vehiculo> findAll();

    boolean deleteByPlaca(String placa);
}