package com;

public class Administrativo extends Rol {

    public void revisarServicios() {
        Empresa.getInstancia().getTecnicos().forEach(tecnico -> {
            tecnico.getVisitas().stream()
                    .filter(visita -> visita.getEstado().equals(EstadoVisita.FINALIZADO))
                    .forEach(visita -> visita.setFactura(generarFactura(visita, tecnico)));
        });
    }

    public Factura generarFactura(Visita visita, Tecnico tecnico) {
        float costoHorasTrabajo = visita.getTiempoTrabajado()*tecnico.getSeniority().costoHoraTrabajo;
        float costoOtrosGastos = (float) visita.getOtrosCostos().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoGastos = (float) visita.getGastosAdicionales().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoFactura = costoHorasTrabajo + costoOtrosGastos + costoGastos;

        return new Factura((int) Math.random(), costoFactura, costoFactura+(costoFactura*(0.21F + 0.30F)));
    }

    public void imprimirFactura(int idVisita) {
        //implementacion
    }

    @Override
    public void mostrarMenu() {
    	System.out.println("\n\n\n\n");
    	System.out.println("---------------------------------------------");
    	System.out.println("*****\t\tAdministrativo\t\t*****\n\n");
    	
    	
    }
}
