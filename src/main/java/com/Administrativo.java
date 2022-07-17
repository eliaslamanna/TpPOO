package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.EstadoVisita.EN_CURSO;
import static com.EstadoVisita.FINALIZADO;
import static java.util.stream.Collectors.toList;

public class Administrativo extends Rol {

    public Administrativo() {
        this.rol = "Administrativo";
    }

    public void revisarServicios(Integer idtecnico) {
        List<Visita> visitasTecnico = new ArrayList<>(Empresa.getInstancia().getVisitas().values()).stream()
                .filter(visita -> visita.getTecnicos().contains(Empresa.getInstancia().getTecnicos().get(idtecnico)) && EN_CURSO.equals(visita.getEstado()))
                .collect(toList());

        visitasTecnico.forEach(visita -> {
           visita.setEstado(FINALIZADO);
            visita.setFactura(generarFactura(visita, visita.getTecnicos()));
            System.out.println("Visita revisada: " + visita.getIdVisita());
        });
    }

    public Factura generarFactura(Visita visita, List<Usuario> tecnicos) {
        float costoHorasTrabajo[] = {0};

        tecnicos.forEach(tecnico -> {
            costoHorasTrabajo[0] += visita.getTiempoTrabajado()*((Tecnico) tecnico.getRol()).getSeniority().costoHoraTrabajo;
        });

        float costoOtrosGastos = (float) visita.getOtrosCostos().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoGastos = (float) visita.getGastosAdicionales().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoFactura = costoHorasTrabajo[0] + costoOtrosGastos + costoGastos;

        return new Factura(new Random().nextInt(1000), costoFactura, costoFactura+(costoFactura*(0.21F + 0.30F)));
    }

    public void imprimirFactura(Integer idVisita) {
        Factura factura = Empresa.getInstancia().getVisitas().get(idVisita).getFactura();

        System.out.println(factura != null && !factura.yaSeImprimio() ? factura.toString() : "El id ingresado no corresponde con ninguna visita que no se haya impreso ya.");
        Empresa.getInstancia().getVisitas().get(idVisita).getFactura().setYaSeImprimio(true);
    }

    @Override
    public Integer mostrarMenu() {
        Scanner read = new Scanner(System.in);

        System.out.println("\n---------------------------------------------");
        System.out.println("*****\t\tAdministrativo\t\t*****\n\n");
        System.out.println("1) Revisar Servicios");
        System.out.println("2) Imprimir Factura");
        System.out.println("3) Salir");
        int opcion = read.nextInt();
        read.nextLine();

        return opcion;
    }
}
