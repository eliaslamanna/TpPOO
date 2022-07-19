package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.text.Document;

import static com.EstadoVisita.EN_CURSO;
import static com.EstadoVisita.FINALIZADO;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Administrativo extends Rol {

    public Administrativo() {
        this.rol = "Administrativo";
    }

    public void revisarServicios(String idVisita) {
        Integer idParse = Integer.valueOf(idVisita);
    	Visita visita = Empresa.getInstancia().getVisitas().get(idParse);
        if(visita == null) {
        	JOptionPane.showMessageDialog(null, "La visita ingresada no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(visita.getEstado() == visita.getEstado().EN_CURSO) {
        visita.setEstado(FINALIZADO);
        visita.setFactura(generarFactura(visita, visita.getTecnicos()));
        }else {
        JOptionPane.showMessageDialog(null, "La visita ingresada no se puede finalizar", "Error", JOptionPane.ERROR_MESSAGE);	
        }
    }

    public Factura generarFactura(Visita visita, List<Usuario> tecnicos) {
        float costoHorasTrabajo[] = {0};

        tecnicos.forEach(tecnico -> {
            costoHorasTrabajo[0] += visita.getTiempoTrabajado()* Empresa.getInstancia().getCostoHora(((Tecnico) tecnico.getRol()).getSeniority());
        });

        float costoOtrosGastos = (float) visita.getOtrosCostos().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoGastos = (float) visita.getGastosAdicionales().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecioUnidad()).sum();
        float costoFactura = costoHorasTrabajo[0] + costoOtrosGastos + costoGastos;

        return new Factura(new Random().nextInt(1000), costoFactura, costoFactura+(costoFactura*(0.21F + 0.30F)));
    }

    public void imprimirFactura(String idVisita) {
    	
    	Integer idParse = Integer.valueOf(idVisita);
        Factura factura = Empresa.getInstancia().getVisitas().get(idVisita).getFactura();

        System.out.println(factura != null && !factura.yaSeImprimio() ? factura.toString() : "El id ingresado no corresponde con ninguna visita que no se haya impreso ya.");
        Empresa.getInstancia().getVisitas().get(idVisita).getFactura().setYaSeImprimio(true);
        
        /*final String dest = "bills/Factura_" + factura.getNumeroFactura() + ".pdf";
        File file = new File(dest);
        file.getParentFile().mkdirs();
        
        try {
        	CreatePdf(dest);
        }catch (IOException nf) {
        }
        */
    }

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
    
    /*
    private void CreatePdf(String dest) throws IOException{
    	FileOutputStream fos = new FileOutputStream(dest);
    	PdfWriter writer = new PdfWriter(fos);
    	PdfDocument pdf = new PdfDocument(writer);
    	Document document = new Document(pdf);
    	document.add(new Paragraph("HelloWorld"));
    	document.close();
    }
    */
}
