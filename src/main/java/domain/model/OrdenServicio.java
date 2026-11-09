package main.java.domain.model;

import main.java.domain.enums.EstadoOrden;
import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrdenServicio implements Comparable<OrdenServicio> {

    private final String codigo;
    private final String descripcionFalla;
    private final TipoServicio tipoServicio;
    private final Vehiculo vehiculo;
    private final LocalDateTime fechaCreacion;

    private Prioridad prioridad;
    private EstadoOrden estado;

    public OrdenServicio(
            String codigo,
            String descripcionFalla,
            TipoServicio tipoServicio,
            Prioridad prioridad,
            Vehiculo vehiculo
    ) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }

        if (descripcionFalla == null || descripcionFalla.isBlank()) {
            throw new IllegalArgumentException("La descripción de la falla es obligatoria");
        }

        this.codigo = codigo.trim().toUpperCase();
        this.descripcionFalla = descripcionFalla.trim();

        this.tipoServicio = Objects.requireNonNull(
                tipoServicio, "El tipo de servicio es obligatorio"
        );

        this.prioridad = Objects.requireNonNull(
                prioridad, "La prioridad es obligatoria"
        );

        this.vehiculo = Objects.requireNonNull(
                vehiculo, "La orden debe estar asociada a un vehículo"
        );

        this.estado = EstadoOrden.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void cambiarPrioridad(Prioridad nuevaPrioridad) {

        this.prioridad = Objects.requireNonNull(
                nuevaPrioridad, "La prioridad no puede ser nula"
        );
    }

    public void iniciarServicio() {

        if (estado != EstadoOrden.PENDIENTE) {
            throw new IllegalStateException(
                    "Solo se pueden iniciar órdenes pendientes"
            );
        }

        estado = EstadoOrden.EN_PROCESO;
    }

    public void completarServicio() {

        if (estado != EstadoOrden.EN_PROCESO) {
            throw new IllegalStateException(
                    "Solo se pueden completar órdenes en proceso"
            );
        }

        estado = EstadoOrden.COMPLETADA;
    }

    public void cancelarServicio() {

        if (estado == EstadoOrden.COMPLETADA ||
                estado == EstadoOrden.CANCELADA) {

            throw new IllegalStateException(
                    "No se puede cancelar esta orden"
            );
        }

        estado = EstadoOrden.CANCELADA;
    }

    @Override
    public int compareTo(OrdenServicio otra) {

        // Primero: prioridad más alta.
        int comparacion = Integer.compare(
                otra.prioridad.getNivel(),
                this.prioridad.getNivel()
        );

        if (comparacion != 0) {
            return comparacion;
        }

        // Segundo: fecha de creación más antigua.
        comparacion = this.fechaCreacion.compareTo(otra.fechaCreacion);

        if (comparacion != 0) {
            return comparacion;
        }

        // Tercero: código como desempate.
        return this.codigo.compareTo(otra.codigo);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof OrdenServicio orden)) return false;

        return codigo.equals(orden.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {

        return "OrdenServicio{" +
                "codigo='" + codigo + '\'' +
                ", falla='" + descripcionFalla + '\'' +
                ", tipoServicio=" + tipoServicio +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                ", vehiculo=" + vehiculo.getPlaca() +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}