package com;

import java.util.List;

public class Instalacion extends Visita {

    public Instalacion(Cliente cliente, List<Tecnico> tecnicos) {
        super(cliente, tecnicos);
        setTiempoTrabajado(60);
        agregarArticulo(new Articulo("Cable coaxial", 4.5F));
        agregarArticulo(new Articulo("Decodificador de TV", 1F));
        agregarArticulo(new Articulo("Modem de internet", 1F));
        agregarArticulo(new Articulo("Divisor coaxial", 1F));
        agregarArticulo(new Articulo("Conector RG6", 6F));
    }

}
