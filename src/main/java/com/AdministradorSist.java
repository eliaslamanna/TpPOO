package com;

import com.exception.ArticuloNoExisteException;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdministradorSist extends Rol {

    private Scanner read = new Scanner(System.in);

    public AdministradorSist() {
        this.rol = "AdministradorSist";
    }

    public void agregarArticulo() {
        Articulo articulo = cargarArticulo();

        if(Empresa.getInstancia().getStock().containsKey(articulo.getNombre())) {
            Articulo articuloExistente = Empresa.getInstancia().getStock().get(articulo.getNombre());
            articuloExistente.setCantidad(articuloExistente.getCantidad() + articulo.getCantidad());
            Empresa.getInstancia().getStock().put(articulo.getNombre(),articuloExistente);
        }

        Empresa.getInstancia().getStock().put(articulo.getNombre(), articulo);
    }

    public Articulo cargarArticulo(){
        System.out.println("Ingrese el nombre del nuevo articulo: ");
        String nombre = read.next();
        read.nextLine();

        System.out.println("Ingrese el stock inicial: ");
        float stock = read.nextFloat();
        read.nextLine();

        System.out.println("Ingrese la cantidad: ");
        float cantidad = read.nextFloat();
        read.nextLine();

        System.out.println("Ingrese el precio por unidad: ");
        float precio = read.nextFloat();
        read.nextLine();

        return new Articulo(nombre, stock, cantidad, precio);
    }

    public void eliminarArticulo() {

        System.out.println("Ingrese el nombre del articulo a eliminar: ");
        String nombreArticulo = read.next();
        read.nextLine();

        Empresa.getInstancia().getStock().remove(nombreArticulo);
    }

    public void actualizarStock(Articulo articulo, float cantidad) {
        articulo.setStock(articulo.getStock() - cantidad);
    }

    public void agregarStock(String nombreArticulo, float cantidad) {
        Articulo articulo = Empresa.getInstancia().getStock().get(nombreArticulo);

        if(articulo == null) {
            System.out.println("Ingrese el precio del nuevo articulo: ");
            float precio = read.nextFloat();
            read.nextLine();

            Empresa.getInstancia().getStock().put(nombreArticulo, new Articulo(nombreArticulo, cantidad, precio));
        } else {
            articulo.setStock(articulo.getStock() + cantidad);
        }
    }

    public void restarStock(int idVisita) {
        HashMap<String, Articulo> stock = Empresa.getInstancia().getStock();
        Visita visita = null;

        for(Tecnico tecnico : Empresa.getInstancia().getTecnicos()) {
            for (Visita visitaT : tecnico.getVisitas()) {
                if (visitaT.getIdVisita() == idVisita){
                    visita = visitaT;
                }
            }
        }

        if(visita == null) {
            System.out.println("El id no corresponde con ninguna visita");
        } else {
            if (Instalacion.class.isInstance(visita)) {
                actualizarStock(stock.get("Cable Coaxil"), 4.5F);
                actualizarStock(stock.get("Decodificador de TV"), 1);
                actualizarStock(stock.get("Modem de Internet"), 1);
                actualizarStock(stock.get("Divisor Coaxial"), 1);
                actualizarStock(stock.get("Conectores RG6"), 6);
            }

            List<Articulo> materialesAdicionales = visita.getOtrosCostos().stream().filter(otroCosto -> otroCosto.getNombre().equals("Materiales Adicionales")).collect(Collectors.toList());
            for (Articulo materialAdicional : materialesAdicionales) {
                actualizarStock(stock.get(materialAdicional.getNombre()), materialAdicional.getCantidad());
            }
        }
    }

    public void configurarCostoHoras() {
        System.out.println("Ingrese el nuevo costo para las horas de tecnicos JR: ");
        float costoJr = read.nextFloat();
        read.nextLine();
        Seniority.JR(costoJr);

        System.out.println("Ingrese el nuevo costo para las horas de tecnicos SSR: ");
        float costoSsr = read.nextFloat();
        read.nextLine();
        Seniority.SSR(costoSsr);

        System.out.println("Ingrese el nuevo costo para las horas de tecnicos SR: ");
        float costoSr = read.nextFloat();
        read.nextLine();
        Seniority.SR(costoSr);
    }


    @Override
    public Integer mostrarMenu() {

        System.out.println("\n---------------------------------------------");
        System.out.println("*****\t\tAdministrador de Sistema\t\t*****\n\n");
        System.out.println("1) Agregar articulo a stock");
        System.out.println("2) Agregar stock de articulo");
        System.out.println("3) Eliminar stock de articulo");
        System.out.println("4) Salir");

        int opcion = read.nextInt();
        read.nextLine();

        return opcion;
    }
}
