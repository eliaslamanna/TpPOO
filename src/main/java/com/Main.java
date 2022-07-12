package com;

import com.exception.RolNoExisteException;
import com.exception.UsuarioYaExisteException;

import java.sql.SQLOutput;
import java.util.Arrays;
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
				opcionElegida = callC.mostrarMenu();
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
}
