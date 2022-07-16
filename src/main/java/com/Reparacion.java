package com;

import java.util.List;

public class Reparacion extends Visita {

    public Reparacion(Cliente cliente, List<Usuario> tecnicos) {
        super(cliente, tecnicos);
        setTiempoTrabajado(30);
    }
}
