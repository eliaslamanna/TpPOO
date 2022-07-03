package com;

import com.exception.ArticuloNoExisteException;
import com.exception.StockInsuficienteException;

import javax.management.ObjectInstance;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AdministradorSist extends Rol {

    public AdministradorSist() {
        this.rol = "AdministradorSist";
    }
    public void agregarArticulo(Articulo articulo) {

        //chequeo si el articulo existe

        //si el articulo ya existe entonces le sumo la cantidad a la cantidad que ya habia de ese tipo
        if(Empresa.getInstancia().getStock().containsKey(articulo.getNombre())) {
            //obtengo el que tengo guardado y le sumo la cantidad del nuevo
            Articulo articuloExistente = Empresa.getInstancia().getStock().get(articulo.getNombre());
            articuloExistente.setCantidad(articuloExistente.getCantidad() + articulo.getCantidad());
            Empresa.getInstancia().getStock().put(articulo.getNombre(),articuloExistente);
        }

        //si el articulo no existe lo guardo normal
        Empresa.getInstancia().getStock().put(articulo.getNombre(), articulo);
    }

    public void eliminarArticulo(String nombreArticulo) {
        Empresa.getInstancia().getStock().remove(nombreArticulo);
    }

    public void modificarArticulo(Articulo articulo) throws ArticuloNoExisteException {
        //si el articulo que quiero modificar no existe entonces tiro exception
        if(!Empresa.getInstancia().getStock().containsKey((articulo.getNombre()))) {
            throw new ArticuloNoExisteException();
        }

        //busco el que ya tengo, le pongo los valores del nuevo y lo vuelvo a guardar
        Articulo articuloActual = Empresa.getInstancia().getStock().get(articulo.getNombre());

        this.actualizarArticulo(articuloActual,articulo);
    }

    //le paso 2 atributos, uno el articulo viejo guardado y otro el nuevo que tiene los valores que quiero actualizar
    //del nuevo tomo solo los valores que no estan vacios ya que son los que quiero cambiar en el viejo
    private void actualizarArticulo(Articulo articuloActual, Articulo articuloNuevo) {
        if(articuloNuevo.getCantidad() != null) {
            articuloActual.setCantidad(articuloNuevo.getCantidad());
        }
        else if(articuloNuevo.getPrecioUnidad() != null) {
            articuloActual.setPrecioUnidad(articuloNuevo.getPrecioUnidad());
        }
    }

    public void actualizarStock(Articulo articulo, float cantidad) {
        articulo.setStock(articulo.getStock() - cantidad);
    }

    public void restarStock(Visita visita) {
        HashMap<String, Articulo> stock = Empresa.getInstancia().getStock();

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


    @Override
    public void mostrarMenu() {
    }
}
