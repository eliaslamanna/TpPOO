package com;

import java.util.List;
import java.util.Random;

public class Visita {
	private int idVisita;
	private Cliente cliente;
	private List<Usuario> tecnicos;
	// el almuerzo se suma como articulo (se le da el costo que queramos)
	private List<Articulo> materiales;
	private EstadoVisita estado;
	private int tiempoTrabajado;
	private List<Articulo> otrosCostos;
	private List<Articulo> gastosAdicionales;
	private Factura factura;

	public Visita() {}

	public List<Articulo> getMateriales() {
		return this.materiales;
	}

	public void agregarArticulo(Articulo articulo) {
		this.materiales.add(articulo);
	}

	public Visita(Cliente cliente, List<Usuario> tecnicos) {
		this.idVisita = new Random().nextInt(10000);
		this.cliente = cliente;
		this.tecnicos = tecnicos;
		this.estado = EstadoVisita.PROGRAMADO;
	}

	public void setEstado(EstadoVisita estado) {
		this.estado = estado;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public void setTiempoTrabajado(int tiempoTrabajado) {
		this.tiempoTrabajado = tiempoTrabajado;
	}

	public void setMateriales(List<Articulo> materiales) {
		this.materiales = materiales;
	}

	public void setOtrosCostos(List<Articulo> otrosCostos) {
		this.otrosCostos = otrosCostos;
	}

	public void setGastosAdicionales(List<Articulo> gastosAdicionales) {
		this.gastosAdicionales = gastosAdicionales;
	}

	public void setIdVisita(int idVisita) {
		this.idVisita = idVisita;
	}

	public int getIdVisita() {
		return idVisita;
	}

	public int getTiempoTrabajado() {
		return this.tiempoTrabajado;
	}

	public EstadoVisita getEstado() {
		return this.estado;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public List<Articulo> getOtrosCostos() {
		return this.otrosCostos;
	}

	public List<Articulo> getGastosAdicionales() {
		return this.gastosAdicionales;
	}

	public List<Usuario> getTecnicos() { return this.tecnicos;}

	public void  obtenerDatosVisita() {
		System.out.println("Visita: \n" + "\t - Id: " + idVisita + "\n" + "\t - " + cliente.toString() + "\n" + "\t - Estado: " + estado + "\n" + "\t - Tiempo Trabajado: " + tiempoTrabajado + "\n");

		System.out.println("\t - Materiales: ");
		materiales.forEach(material -> System.out.println(("\t \t " + material.getNombre() + "(" + material.getCantidad() + ")\n")));
		System.out.println("\t - Otros costos: ");
		otrosCostos.forEach(otroCosto -> System.out.println("\t \t " + otroCosto.getNombre() + "(" + otroCosto.getCantidad() + ")\n"));
		System.out.println("\t - Gastos Adicionales: ");
		gastosAdicionales.forEach(gastoAdicional -> System.out.println(("\t \t " + gastoAdicional.getNombre() + "(" + gastoAdicional.getCantidad() + ")\n")));

		if(this.factura != null) {
			System.out.println(this.factura.toString());
		}
	}
}
