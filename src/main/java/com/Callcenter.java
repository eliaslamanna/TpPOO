package com;

import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Callcenter extends Rol {

	private List<Visita> visitas;

	public Callcenter() {
		this.rol = "Call Center";
		// Visita v1
	}

	@Override
	public Integer mostrarMenu() {
		int opcion = 0;
		Scanner read = new Scanner(System.in);

		System.out.println("\n\n\n\n");
		System.out.println("---------------------------------------------");
		System.out.println("*****\t\tCall Center\t\t*****\n\n");
		System.out.println("1) Programar visita");
		System.out.println("2) Salir");
		opcion = read.nextInt();
		read.nextLine();

		return opcion;
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}

	public Visita agendarVisita(Integer horario, String dia, Cliente cliente, String tipoVisita,
			List<Articulo> gastosAdicionales, List<Articulo> otrosCostos, int cantidadTecnicos)
			throws HorarioReservadoException, StockInsuficienteException {

		materialesEnStock(tipoVisita, otrosCostos);
		List<Tecnico> tecnicos = new ArrayList<>();

		for (Tecnico tecnico : Empresa.getInstancia().getTecnicos()) {
			if (tecnico.disponible(dia, horario)) {
				if (tecnicos.size() < cantidadTecnicos) {
					tecnico.agendarVisita(dia, horario);
					cliente.getAgenda().agendarVisita(horario, dia);
					tecnicos.add(tecnico);
				}
			}
		}

		if(tecnicos.size() != cantidadTecnicos) {
			throw new HorarioReservadoException(dia, horario);
		}

		Visita visita = new Instalacion(cliente, tecnicos, otrosCostos, gastosAdicionales);

		if (tipoVisita.equals("Reparacion")) {
			visita = new Reparacion(cliente, tecnicos, otrosCostos, gastosAdicionales);
		}

		for (Tecnico tecnico : tecnicos) {
			tecnico.getVisitas().add(visita);
		}

		return visita;
	}

	public void materialesEnStock(String tipoVisita, List<Articulo> otrosCostos) throws StockInsuficienteException {
		if (tipoVisita.equals("Instalacion")) {
			HashMap<String, Articulo> stock = Empresa.getInstancia().getStock();
			if (stock.get("Cable Coaxil").getStock() < 4.5F)
				throw new StockInsuficienteException();
			if (stock.get("Decodificador de TV").getStock() < 1)
				throw new StockInsuficienteException();
		}

		List<Articulo> materialesAdicionales = otrosCostos.stream()
				.filter(otroCosto -> otroCosto.getNombre().equals("Materiales Adicionales"))
				.collect(Collectors.toList());
		for (Articulo materialAdicional : materialesAdicionales) {
			if (materialAdicional.getStock() < materialAdicional.getCantidad()) {
				throw new StockInsuficienteException();
			}
		}
	}
}
