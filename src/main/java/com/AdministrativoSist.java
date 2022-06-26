package com;

import com.exception.ArticuloNoExisteException;

public class AdministrativoSist extends Rol {

    public void agregarArticulo(Articulo articulo) {

        //chequeo si el articulo existe

        //si el articulo ya existe entonces le sumo la cantidad a la cantidad que ya habia de ese tipo
        if(Empresa.stock.containsKey(articulo.getNombre())) {
            //obtengo el que tengo guardado y le sumo la cantidad del nuevo
            Articulo articuloExistente = Empresa.stock.get(articulo.getNombre());
            articuloExistente.setCantida(articuloExistente.getCantida() + articulo.getCantida());
            Empresa.stock.put(articulo.getNombre(),articuloExistente);
        }

        //si el articulo no existe lo guardo normal
        Empresa.stock.put(articulo.getNombre(), articulo);
    }

    public void eliminarArticulo(String nombreArticulo) {
        Empresa.stock.remove(nombreArticulo);
    }

    public void modificarArticulo(Articulo articulo) throws ArticuloNoExisteException {
        //si el articulo que quiero modificar no existe entonces tiro exception
        if(!Empresa.stock.containsKey((articulo.getNombre()))) {
            throw new ArticuloNoExisteException();
        }

        //busco el que ya tengo, le pongo los valores del nuevo y lo vuelvo a guardar
        Articulo articuloActual = Empresa.stock.get(articulo.getNombre());

        this.actualizarArticulo(articuloActual,articulo);
    }

    //le paso 2 atributos, uno el articulo viejo guardado y otro el nuevo que tiene los valores que quiero actualizar
    //del nuevo tomo solo los valores que no estan vacios ya que son los que quiero cambiar en el viejo
    private void actualizarArticulo(Articulo articuloActual, Articulo articuloNuevo) {
        if(articuloNuevo.getCantida() != null) {
            articuloActual.setCantida(articuloNuevo.getCantida());
        }
        else if(articuloNuevo.getPrecio() != null) {
            articuloActual.setPrecio(articuloNuevo.getPrecio());
        }
    }

    @Override
    public void mostrarMenu() {

    }
}
