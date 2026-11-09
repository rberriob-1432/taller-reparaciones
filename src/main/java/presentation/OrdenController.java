package main.java.presentation;

import main.java.application.service.OrdenService;
import main.java.domain.enums.Prioridad;
import main.java.domain.enums.TipoServicio;
import main.java.domain.model.OrdenServicio;

public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    public void registrar(
            String codigo,
            String descripcion,
            TipoServicio tipo,
            Prioridad prioridad,
            String placa
    ) {

        ordenService.registrarOrden(
                codigo,
                descripcion,
                tipo,
                prioridad,
                placa
        );

        System.out.println("Orden registrada correctamente.");
    }

    public void consultar(String codigo) {

        System.out.println(
                ordenService.consultarOrden(codigo)
        );
    }

    public void listar() {

        ordenService.listarOrdenes().forEach(System.out::println);
    }

    public void procesarSiguiente() {

        OrdenServicio orden = ordenService.procesarSiguienteOrden();

        System.out.println("Orden enviada a reparación:");
        System.out.println(orden);
    }

    public void cambiarPrioridad(String codigo, Prioridad prioridad) {

        ordenService.cambiarPrioridad(codigo, prioridad);

        System.out.println("Prioridad actualizada correctamente.");
    }

    public void completar(String codigo) {

        ordenService.completarOrden(codigo);

        System.out.println("Servicio completado.");
    }

    public void cancelar(String codigo) {

        ordenService.cancelarOrden(codigo);

        System.out.println("Orden cancelada.");
    }
}