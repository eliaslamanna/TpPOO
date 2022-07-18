package com;

import java.util.List;

public class Reparacion extends Visita {

    public Reparacion(Cliente cliente, List<Usuario> tecnicos, String dia, Integer horarioInicio, Integer horarioFin) {
        super(cliente, tecnicos, dia, horarioInicio, horarioFin);
        setTiempoTrabajado(30);
    }
}
