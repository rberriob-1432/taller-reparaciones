package main.java.application.port.out;
import main.java.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    void save(Cliente cliente);

    Optional<Cliente> findByIdentificacion(String identificacion);

    boolean existsByIdentificacion(String identificacion);

    List<Cliente> findAll();
}