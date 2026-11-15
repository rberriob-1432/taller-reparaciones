package main.model;
public class OrdenServicio implements Comparable<OrdenServicio> {

    private final String codigo;
    private final String descripcionFalla;
    private final TipoServicio tipoServicio;
    private Prioridad prioridad;
    private EstadoOrden estado;
    private final Vehiculo vehiculo;
    private final Cliente cliente;

    public OrdenServicio(String codigo, String descripcionFalla,
                         TipoServicio tipoServicio, Prioridad prioridad,
                         Vehiculo vehiculo, Cliente cliente) {

        if (codigo == null || codigo.trim().isEmpty()
                || descripcionFalla == null || descripcionFalla.trim().isEmpty()
                || tipoServicio == null || prioridad == null
                || vehiculo == null || cliente == null) {
            throw new IllegalArgumentException("Datos de la orden inválidos");
        }

        this.codigo = codigo.trim();
        this.descripcionFalla = descripcionFalla.trim();
        this.tipoServicio = tipoServicio;
        this.prioridad = prioridad;
        this.estado = EstadoOrden.PENDIENTE;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setPrioridad(Prioridad prioridad) {
        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad no puede ser null");
        }
        this.prioridad = prioridad;
    }

    public void setEstado(EstadoOrden estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser null");
        }
        this.estado = estado;
    }

    @Override
    public int compareTo(OrdenServicio otra) {
        int resultado = Integer.compare(
                this.prioridad.getNivel(),
                otra.prioridad.getNivel()
        );

        if (resultado != 0) {
            return resultado;
        }

        return this.codigo.compareTo(otra.codigo);
    }

    @Override
    public String toString() {
        return "OrdenServicio{" +
                "codigo='" + codigo + '\'' +
                ", descripcionFalla='" + descripcionFalla + '\'' +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                '}';
    }
}
