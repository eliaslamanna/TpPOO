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
    private List<Articulo> otrosCostos;
    private List<Articulo> gastosAdicionales;
    private Factura factura;

    public List<Articulo> getMateriales() {
        return this.materiales;
    }

    public void agregarArticulo(Articulo articulo) {
        this.materiales.add(articulo);
    }

    public Visita(Cliente cliente, List<Tecnico> tecnicos, List<Articulo> otrosCostos, List<Articulo> gastosAdicionales) {
        this.idVisita = (int) Math.random();
        this.cliente = cliente;
        this.tecnicos = tecnicos;
        this.estado = EstadoVisita.PROGRAMADO;
        this.otrosCostos = otrosCostos;
        this.gastosAdicionales = gastosAdicionales;
    }

    public void setEstado(EstadoVisita estado) {
        this.estado = estado;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setTiempoTrabajado(int tiempoTrabajado) { this.tiempoTrabajado = tiempoTrabajado;}

    public void setMateriales(List<Articulo> materiales) { this.materiales = materiales; }

    public void setOtrosCostos(List<Articulo> otrosCostos) { this.otrosCostos = otrosCostos; }

    public void setGastosAdicionales(List<Articulo> gastosAdicionales) { this.gastosAdicionales = gastosAdicionales; }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getTiempoTrabajado() { return this.tiempoTrabajado; }

    public EstadoVisita getEstado() { return this.estado; }

    public List<Articulo> getOtrosCostos() { return otrosCostos; }

    public List<Articulo> getGastosAdicionales() { return gastosAdicionales; }
}
