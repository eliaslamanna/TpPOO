package com;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.exception.*;

public class CallCenter extends Rol {

	public CallCenter() {
		this.rol = "Call Center";
	}

	public void agendarVisita(String dniCliente, Integer horarioInicio, Integer horarioFin, Integer dia, Integer mes, String tipoVisita, Integer cantidadTecnicos) throws HorarioReservadoException, StockInsuficienteException, TecnicosInsuficientesException, HorarioParaTurnoIncorrectoException, TiempoMinimoInstalacionIncorrectoException, TiempoMinimoReparacionIncorrectoException, MesIncorrectoException, DiaIncorrectoException, FormatoHoraIncorrecto {
		if(dia > 31 || dia <= 0 || dia == null) {
			throw new DiaIncorrectoException();
		}

		if(mes > 12 || mes <= 0 || mes == null) {
			throw new MesIncorrectoException();
		}

		if(!validarHora(horarioInicio.toString()) || !validarHora(horarioFin.toString())) {
			throw new FormatoHoraIncorrecto();
		}

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

				if(tecnicos.size() != cantidadTecnicos || cantidadTecnicos < 0) {
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

	public boolean validarHora(String hora) {
		if (hora == null) {
			return false;
		}

		if (hora.length() != 4) {
			return false;
		}

		char horaUno = hora.charAt(0);
		char horaDos = hora.charAt(1);
		char minutoUno = hora.charAt(2);
		char minutoDos = hora.charAt(3);

		if (horaUno != '0' && horaUno != '1' && horaUno != '2') {
			return false;
		}

		if (horaUno == '0' || horaUno == '1') {
			if (horaDos < '0' || horaDos > '9') {
				return false;
			}
		} else {

			if (horaDos < '0' || horaDos > '3') {
				return false;
			}
		}

		if (minutoUno < '0' || minutoUno > '5') {
			return false;
		}

		if (minutoDos < '0' || minutoDos > '9') {
			return false;
		}

		return true;
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
		if(Empresa.getInstancia().getClientes().containsKey(dniCliente)) {
			throw new ClienteExistenteException(dniCliente);
			}else {
				Empresa.getInstancia().agregarCliente(dniCliente,nombreCliente,apellidoCliente,direccionCliente);
			}
	}

}


