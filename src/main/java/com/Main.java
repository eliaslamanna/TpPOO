package com;

import com.exception.HorarioReservadoException;
import com.exception.RolNoExisteException;
import com.exception.StockInsuficienteException;
import com.exception.UsuarioYaExisteException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) throws RolNoExisteException, UsuarioYaExisteException {
		// TODO Auto-generated method stub

		Empresa empresa = Empresa.getInstancia();


		boolean accedio = false;

		String usuario = pedirUsuario();
		String contraseña = pedirContraseña();

		if (Empresa.getInstancia().signIn(usuario, contraseña)) {
			System.out.println("Loggeado exitosamente");
			accedio = true;
		} else {
			System.out.println("El sistema no encontro el usuario, verifique los datos ingresados o dese de alta");
		}

		while(!accedio) {
			System.out.println("*****\t\tSeleccione accion\t\t*****");
			System.out.println("1) Loggearse");
			System.out.println("2) Darse de alta");

			int opc = read.nextInt();
			read.nextLine();



			switch (opc) {
				case 1:
					accedio = Empresa.getInstancia().signIn(usuario, contraseña);
					if (!accedio) {
						System.out.println("El sistema no encontro el usuario, verifique los datos ingresados o dese de alta");
						usuario = pedirUsuario();
						contraseña = pedirContraseña();
					}
					break;
				case 2:
					System.out.println("Ingrese su Rol");
					String rol = read.nextLine();

					Usuario nuevoUsuario = UsuarioFactory.getInstancia().crearUsuario(rol, usuario, contraseña);
					Empresa.getInstancia().singUp(nuevoUsuario);

					accedio = true;
					System.out.println("Loggeo exitoso");
					break;
			}
		}

		String finalUsuario = usuario;
		Usuario usuarioLoggeado = Empresa.getInstancia().getUsuarios().stream().filter(u -> u.getUsuario().equals(finalUsuario)).findFirst().get();
		int opcionElegida;

		switch (usuarioLoggeado.getRol()) {
			case "Administrativo": {
				Administrativo admin = new Administrativo();
				funcionesAdministrativo(admin.mostrarMenu(), admin);
				break;
			}
			case "Call Center": {
				Callcenter callC = new Callcenter();
				funcionesCallCenter(callC.mostrarMenu(), callC);
				break;
			}
			case "Tecnico": {
				Tecnico t = new Tecnico();
				opcionElegida = t.mostrarMenu();
				break;
			}
			case "AdministradorSist": {
				AdministradorSist adminSist = new AdministradorSist();
				opcionElegida = adminSist.mostrarMenu();
				break;
			}
		}
		System.out.println("\n\n\n-----------------------------------");

	}

	public static String pedirUsuario() {
		System.out.println("Ingrese su usuario:");
		return read.nextLine();
	}

	public static String pedirContraseña() {
		System.out.println("Ingrese su contraseña:");
		return read.nextLine();
	}

	public static void funcionesAdministrativo(int opcionElegida, Administrativo admin) {

		while (opcionElegida != 3) {
			switch (opcionElegida) {
				case 1:
					admin.revisarServicios();
					System.out.println("Todos los servicios fueron revisados exitosamente");
					break;
				case 2:
					System.out.println("Ingrese el id de la visita de la cual desea imprimir la factura");
					int idVisita = read.nextInt();
					read.nextLine();
					admin.imprimirFactura(idVisita);
					break;
			}
			opcionElegida = admin.mostrarMenu();
		}
		System.out.println("Saliendo del sistema . . . ");
	}

	public static void funcionesCallCenter(int opcionElegida, Callcenter callcenter) {

		while (opcionElegida != 2) {
			switch (opcionElegida) {
				case 1:
					System.out.println("Ingresar DNI del cliente: ");
					int dni = read.nextInt();
					read.nextLine();

					Cliente cliente = Empresa.getInstancia().getClientes().get(dni);
					if(cliente == null) {
						System.out.println("No se encontro el DNI en el sistema. Por favor, ingrese los datos solicitados para darlo de alta: ");
						System.out.println("Nombre: ");
						String nombre = read.next();
						read.nextLine();
						System.out.println("Direccion: ");
						String direccion = read.next();
						read.nextLine();
						cliente = Empresa.getInstancia().agregarCliente(dni, nombre, direccion, new Agenda());
					}

					System.out.println("Ingrese los datos de la visita");
					System.out.println("Ingresar el horario: ");
					int horario = read.nextInt();
					read.nextLine();
					System.out.println("Ingresar el dia: ");
					String dia = read.next();
					read.nextLine();
					System.out.println("Ingresar el tipo de visita (Instalacion/Reparacion): ");
					String tipoVisita = read.next();
					System.out.println("Ingresar la cantidad de tecnicos: ");
					int cantTec = read.nextInt();
					read.nextLine();

					List<Articulo> articulos = new ArrayList<>();
					articulos.add(new Articulo("Cable Coaxil", 2F, 100F, 50F));
					articulos.add(new Articulo("Decodificador de TV", 3F, 15F, 100.3F));

					Visita visita = new Visita();
					visita.setMateriales(articulos);

					boolean agendaFinalizada = false;

					while (!agendaFinalizada) {
						try {
							visita = callcenter.agendarVisita(horario, dia, cliente, tipoVisita, articulos, articulos, cantTec);
							agendaFinalizada = true;
							System.out.println("La visita fue programada con exito");
						} catch (HorarioReservadoException hre) {
							System.out.println("El horario ingresado no esta disponible, por favor ingrese otro: ");
							System.out.println("Ingresar el horario: ");
							horario = read.nextInt();
							read.nextLine();
							System.out.println("Ingresar el dia: ");
							dia = read.next();
							read.nextLine();
						} catch (StockInsuficienteException e) {
							System.out.println("los insumos para realizar esta visita son insufisientes.");
							agendaFinalizada = true;
						}
					}
			}
			opcionElegida = callcenter.mostrarMenu();
		}
		System.out.println("Saliendo del sistema . . . ");
	}
}
