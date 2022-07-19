package com;

import java.util.List;

public class Reparacion extends Visita {

    public Reparacion(Cliente cliente, List<Usuario> tecnicos, Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin) {
        super(cliente, tecnicos, dia, mes, horarioInicio, horarioFin);
        setTiempoTrabajado(30);
    }
}
