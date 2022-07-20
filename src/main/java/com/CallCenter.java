package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.exception.ClienteExistenteException;
import com.exception.HorarioParaTurnoIncorrectoException;
import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;
import com.exception.TecnicosInsuficientesException;
import com.exception.TiempoMinimoInstalacionIncorrectoException;
import com.exception.TiempoMinimoReparacionIncorrectoException;

public class CallCenter extends Rol {

	private Scanner read = new Scanner(System.in);

	public CallCenter() {
		this.rol = "Call Center";
	}

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

	public void agendarVisita(String dniCliente, Integer horarioInicio, Integer horarioFin, Integer dia, Integer mes, String tipoVisita, Integer cantidadTecnicos) throws HorarioReservadoException, StockInsuficienteException, TecnicosInsuficientesException, HorarioParaTurnoIncorrectoException, TiempoMinimoInstalacionIncorrectoException, TiempoMinimoReparacionIncorrectoException {
		if ("Instalacion".equals(tipoVisita)) {
			if (stockInsuficienteInstalacion()) {
				System.out.println("Materiales insuficientes para continuar con el servicio.");
				throw new StockInsuficienteException();
			}
			if(horarioFin-horarioInicio < 100) {
				System.out.println("La duracion de la instalacion debe ser de al menos una hora");
				throw new TiempoMinimoInstalacionIncorrectoException();
			}
		} else {
			if(horarioFin-horarioInicio < 30) {
				System.out.println("La duracion de la instalacion debe ser de al menos 30 minutos");
				throw new TiempoMinimoReparacionIncorrectoException();
			} else {
				List<Usuario> tecnicos = new ArrayList<>();
				Cliente cliente = Empresa.getInstancia().getClientes().get(dniCliente);

				for (Usuario tecnico : new ArrayList<>(Empresa.getInstancia().getTecnicos().values())) {
					Tecnico tec = (Tecnico) tecnico.getRol();
					if (tec.disponible(dia, mes, horarioInicio, horarioFin) && cliente.disponible(dia, mes, horarioInicio, horarioFin)) {
						if (tecnicos.size() < cantidadTecnicos) {
							tec.agendarVisita(dia, mes, horarioInicio, horarioFin);
							cliente.agendarVisita(dia, mes, horarioInicio, horarioFin);
							tecnicos.add(tecnico);
						}
					}
				}

				if(tecnicos.size() != cantidadTecnicos) {
					System.out.println("No alcanza la cantidad de tecnicos requerida para el servicio");
					throw new TecnicosInsuficientesException();
				}

				if("Instalacion".equals(tipoVisita)) {
					Empresa.getInstancia().agregarVisita(new Instalacion(Empresa.getInstancia().getClientes().get(dniCliente), tecnicos, dia, mes, horarioInicio, horarioFin));
				} else {
					Empresa.getInstancia().agregarVisita(new Reparacion(Empresa.getInstancia().getClientes().get(dniCliente), tecnicos, dia, mes, horarioInicio, horarioFin));
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

	public void guardarCliente(String dniCliente, String nombreCliente, String apellidoCliente, String direccionCliente) throws ClienteExistenteException {
		
		/*
		System.out.println("Ingrese el DNI del cliente: ");
		String dniCliente = read.nextLine();

		System.out.println("\nIngrese el nombre del cliente: ");
		String nombreCliente = read.nextLine();
		System.out.println("\nIngrese el apellido del cliente: ");
		String apellidoCliente = read.nextLine();
		System.out.println("\nIngrese la direccion del cliente: \n");
		String direccionCliente = read.nextLine();
		*/
		
		if(Empresa.getInstancia().getClientes().containsKey(dniCliente)) {
			throw new ClienteExistenteException(dniCliente);
			}else {
				Empresa.getInstancia().agregarCliente(dniCliente,nombreCliente,apellidoCliente,direccionCliente);
			}
	}

}


