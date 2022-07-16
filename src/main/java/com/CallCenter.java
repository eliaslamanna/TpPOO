package com;

import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CallCenter extends Rol {

	private Scanner read = new Scanner(System.in);

	public CallCenter() {
		this.rol = "Call Center";
	}

	@Override
	public Integer mostrarMenu() {
		Scanner read = new Scanner(System.in);

		System.out.println("\n---------------------------------------------");
		System.out.println("*****\t\tCall Center\t\t*****\n\n");
		System.out.println("1) Programar visita");
		System.out.println("2) Salir");

		int opcion = read.nextInt();
		read.nextLine();

		return opcion;
	}

	public void agendarVisita() throws HorarioReservadoException {
		System.out.println("Ingrese el dni del cliente: ");
		String dniCliente = read.nextLine();

		if(!Empresa.getInstancia().getClientes().containsKey(dniCliente)) {
			System.out.println("\nEl cliente no existe, por favor darlo de alta antes de continuar.");
			//TODO dar de alta cliente
		} else {
			System.out.println("\nIngrese el horario de la visita: ");
			Integer horarioInicio = read.nextInt();

			System.out.println("\nIngrese el horario de fin la visita: ");
			Integer horarioFin = read.nextInt();

			System.out.println("\nIngrese el dia de la visita: ");
			String dia = read.nextLine();

			System.out.println("\nQue tipo de servicio desea?");
			String tipoVisita = read.nextLine();

			System.out.println("\nIngrese la cantidad de tecnicos");
			Integer cantidadTecnicos = read.nextInt();
			read.nextLine();

			if ("Instalacion".equals(tipoVisita)) {
				if (stockInsuficienteInstalacion()) {
					System.out.println("Materiales insifucientes para continuar con el servicio.");
				}
				if(horarioFin-horarioInicio < 100) {
					System.out.println("La duracion de la instalacion debe ser de al menos una hora");
				}
			} else {
				if(horarioFin-horarioInicio < 30) {
					System.out.println("La duracion de la instalacion debe ser de al menos 30 minutos");
				} else {
					List<Usuario> tecnicos = new ArrayList<>();
					Cliente cliente = Empresa.getInstancia().getClientes().get(dniCliente);

					for (Usuario tecnico : new ArrayList<>(Empresa.getInstancia().getTecnicos().values())) {
						Tecnico tec = (Tecnico) tecnico.getRol();
						if (tec.disponible(dia, horarioInicio, horarioFin) && cliente.disponible(dia, horarioInicio, horarioFin)) {
							if (tecnicos.size() < cantidadTecnicos) {
								tec.agendarVisita(dia, horarioInicio, horarioFin);
								cliente.agendarVisita(dia, horarioInicio, horarioFin);
								tecnicos.add(tecnico);
							}
						}
					}

					if(tecnicos.size() != cantidadTecnicos) {
						System.out.println("No alcanza la cantidad de tecnicos requerida para el servicio");
					}

					Empresa.getInstancia().agregarVisita(new Visita(Empresa.getInstancia().getClientes().get(dniCliente), tecnicos));
				}
			}
		}

	}

	public boolean stockInsuficienteInstalacion() {
		HashMap<String, Articulo> stock = Empresa.getInstancia().getStock();
		return stock.get("Cable Coaxil").getCantidad() < 4.5F ||
				stock.get("Decodificador de TV").getCantidad() < 1 ||
				stock.get("Modem de internet").getCantidad() < 1 ||
				stock.get("Divisor coaxial").getCantidad() < 1 ||
				stock.get("Conector RG6").getCantidad() < 1;
	}

}
