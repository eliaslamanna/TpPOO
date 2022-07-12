package com;

import java.util.Random;
import java.util.Scanner;

public class Administrativo extends Rol {

    public Administrativo() {
        this.rol = "Administrativo";
    }

    public void revisarServicios() {
        Empresa.getInstancia().getTecnicos().forEach(tecnico -> {
            tecnico.getVisitas().stream()
                    .filter(visita -> visita.getEstado().equals(EstadoVisita.FINALIZADO))
                    .forEach(visita -> {
                        visita.setFactura(generarFactura(visita, tecnico));
                        System.out.println("Visita revisada: " + visita.getIdVisita());
                    });
        });
    }

    public Factura generarFactura(Visita visita, Tecnico tecnico) {
        float costoHorasTrabajo = visita.getTiempoTrabajado()*tecnico.getSeniority().costoHoraTrabajo;
        float costoOtrosGastos = (float) visita.getOtrosCostos().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoGastos = (float) visita.getGastosAdicionales().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoFactura = costoHorasTrabajo + costoOtrosGastos + costoGastos;

        return new Factura(new Random().nextInt(1000), costoFactura, costoFactura+(costoFactura*(0.21F + 0.30F)));
    }

    public void imprimirFactura(int idVisita) {
        Factura factura = null;
        for (Tecnico tecnico : Empresa.getInstancia().getTecnicos()) {
            for (Visita visita : tecnico.getVisitas()) {
                if (idVisita == visita.getIdVisita()) {
                    factura = visita.getFactura();
                }
            }
        }

        System.out.println(factura.toString());
    }

    @Override
    public Integer mostrarMenu() {
        int opcion = 0;
        Scanner read = new Scanner(System.in);

        System.out.println("\n\n\n\n");
        System.out.println("---------------------------------------------");
        System.out.println("*****\t\tAdministrativo\t\t*****\n\n");
        System.out.println("1) Revisar Servicios");
        System.out.println("2) Imprimir Factura");
        System.out.println("3) Salir");
        opcion = read.nextInt();
        read.nextLine();

        return opcion;
    }
}
