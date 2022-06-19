package com;

import java.util.List;

public class Visita {
    private int idVisita;
    private Cliente cliente;
    private List<Tecnico> tecnicos;
    //el almuerzo se suma como articulo (se le da el costo que queramos)
    private List<Articulo> materiales;
    private EstadoVisita estado;
    private int tiempoTrabajado;
    private float otrosCostos;
    private float gastosAdicionales;
    private Factura factura;
}
