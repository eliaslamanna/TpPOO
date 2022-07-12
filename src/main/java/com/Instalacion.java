package com;

import java.util.List;

public class Instalacion extends Visita {

    public Instalacion(Cliente cliente, List<Tecnico> tecnicos, List<Articulo> otrosCostos, List<Articulo> gastosAdicionales) {
        super(cliente, tecnicos, otrosCostos, gastosAdicionales);

        setTiempoTrabajado(60);
    }

}
