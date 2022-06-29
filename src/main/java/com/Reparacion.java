package com;

import java.util.List;

public class Reparacion extends Visita {

    public Reparacion(Cliente cliente, List<Tecnico> tecnicos, List<Articulo> otrosCostos, List<Articulo> gastosAdicionales) {
        super(cliente, tecnicos, otrosCostos, gastosAdicionales);
        setTiempoTrabajado(30);
    }
}
