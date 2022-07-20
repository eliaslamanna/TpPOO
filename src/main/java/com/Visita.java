package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Visita {
	private int idVisita;
	private Cliente cliente;
	private List<Usuario> tecnicos = new ArrayList<>();
	private Integer dia;
	private Integer mes;
	private Integer horarioInicio;
	private Integer horarioFin;
	private List<Articulo> materiales = new ArrayList<>();
	private EstadoVisita estado;
	private int tiempoTrabajado;
	private List<Articulo> otrosCostos = new ArrayList<>();
	private List<Articulo> gastosAdicionales = new ArrayList<>();
	private Factura factura;

	public List<Articulo> getMateriales() {
		return this.materiales;
	}

	public void agregarArticulo(Articulo articulo) {
		this.materiales.add(articulo);
	}

	public Visita(Cliente cliente, List<Usuario> tecnicos, Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin) {
		this.idVisita = new Random().nextInt(10000);
		this.cliente = cliente;
		this.tecnicos = tecnicos;
		this.estado = EstadoVisita.PROGRAMADO;
		this.dia = dia;
		this.mes = mes;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
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

	public void setOtrosCostos(List<Articulo> otrosCostos) {
		this.otrosCostos = otrosCostos;
	}

	public void setGastosAdicionales(List<Articulo> gastosAdicionales) {
		this.gastosAdicionales = gastosAdicionales;
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

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return  " idVisita: " + idVisita +
				" -- cliente: " + cliente.getDniCliente() +
				" -- dia: " + dia +
				" -- mes: " + mes +
				" -- horarioInicio: " + horarioInicio +
				" -- horarioFin: " + horarioFin +
				" -- estado: " + estado;
	}

}
