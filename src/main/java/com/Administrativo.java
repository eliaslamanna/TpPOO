package com;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import static com.EstadoVisita.FINALIZADO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

        float costoOtrosGastos = (float) visita.getOtrosCostos().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecio()).sum();
        float costoGastos = (float) visita.getGastosAdicionales().stream().mapToDouble(articulo -> articulo.getCantidad()*articulo.getPrecio()).sum();
        float costoFactura = costoHorasTrabajo[0] + costoOtrosGastos + costoGastos;

        return new Factura(new Random().nextInt(1000), costoFactura, costoFactura+(costoFactura*(0.21F + 0.30F)));
    }

    public void imprimirFactura(String idVisita) throws DocumentException, FileNotFoundException {

        Integer id = Integer.valueOf(idVisita);
        Factura factura = Empresa.getInstancia().getVisitas().get(Integer.valueOf(id)).getFactura();

        System.out.println(factura != null && !factura.yaSeImprimio() ? factura.toString() : "El id ingresado no corresponde con ninguna visita que no se haya impreso ya.");
        
        CreatePdf(factura.getNumeroFactura(), factura.getPrecioFinal(), id);
        
        factura.setYaSeImprimio(true);
        
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
    
    
    private void CreatePdf(int nroFactura, float precioFactura, Integer id) throws DocumentException, FileNotFoundException{
    	
    	/*try {
    	Document document = new Document();
    	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("bills/Factura_" + nroFactura + ".pdf") );
    	document.open();
    	
    	PdfContentByte cb = writer.getDirectContent();
    	Graphics g = cb.createGraphicsShapes(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());

        cb.saveState();
    	
    	ImageIcon logoFact = new ImageIcon("images/logoFact.png");
    	g.drawImage(logoFact.getImage(), 200, 250, 200, 100, null);
    
    	
    	Font font = new Font("Roboto", Font.BOLD, 20);
    	g.setFont(font);
    	g.setColor(Color.BLACK);
    	g.drawString("Factura Numero " + nroFactura, 0, 100);
    	
    	Font font2 = new Font("Helvetica", Font.PLAIN, 15);
    	g.setFont(font2);
    	g.drawString("Id de visita: " + id, 0, 150);
    	g.drawString("Precio final: " + precioFactura, 0, 200);
    	
    	document.close();
    	
    	JOptionPane.showMessageDialog(null, "La factura de la visita "+ id + " se guardo en el siguiente archivo images/Factura_" + nroFactura + ".png", "Imprimir factura", JOptionPane.INFORMATION_MESSAGE);
    	
    	}catch (DocumentException de) {
    		
    	}catch (FileNotFoundException nf) {
    		
    	}*/

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("bills/Factura_" + nroFactura + ".pdf"));
            document.open();
            document.add(new Paragraph("Factura nro: " + nroFactura));
            document.close();
        }
        catch(Exception e)  {
            System.out.println("No se imprimio.");
        }

        JOptionPane.showMessageDialog(null, "La factura de la visita "+ id + " se guardo en el siguiente archivo images/Factura_" + nroFactura + ".pdf", "Imprimir factura", JOptionPane.INFORMATION_MESSAGE);
    	
   
    }
    
}
