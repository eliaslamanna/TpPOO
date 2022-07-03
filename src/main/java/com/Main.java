package com;

import com.exception.RolNoExisteException;
import com.exception.UsuarioYaExisteException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws RolNoExisteException, UsuarioYaExisteException {
		// TODO Auto-generated method stub

		Empresa empresa = Empresa.getInstancia();

		
		int opc = 0;
		while (opc != 5) {

			Scanner read = new Scanner(System.in);

			boolean accedio = false;

			System.out.println("Ingrese su usuario:");
			String usuario = read.nextLine();

			System.out.println("Ingrese su contraseña:");
			String password = read.nextLine();

			while(!accedio) {
				System.out.println("*****\t\tSeleccione accion\t\t*****");
				System.out.println("1) Loggearse");
				System.out.println("2) Darse de alta");

				opc = read.nextInt();
				read.nextLine();

				switch (opc) {
					case 1:
						accedio = Empresa.getInstancia().signIn(usuario,password);
						break;
					case 2:
						System.out.println("Ingrese su Rol");
						String rol = read.nextLine();

						Usuario nuevoUsuario = UsuarioFactory.getInstancia().crearUsuario(rol,usuario,password);

						Empresa.getInstancia().singUp(nuevoUsuario);
				}
			}

			Usuario usuarioLoggeado = Empresa.getInstancia().getUsuarios().stream().filter(u -> u.getUsuario().equals(usuario)).findFirst().get();

			/*System.out.println("*****\t\tSeleccione perfil\t\t*****");
			System.out.println("1) Administrativo");
			System.out.println("2) Call Center");
			System.out.println("3) Tecnico");
			System.out.println("4) Administrador del sistema");
			System.out.println("5) Salir");
			System.out.print("opcion:");*/
			switch (usuarioLoggeado.getRol()) {
			case "Administrativo": {
				Administrativo admin = new Administrativo();
				admin.mostrarMenu();
				break;
			}
			case "Call Center": {
				Callcenter callC = new Callcenter();
				callC.mostrarMenu();
				break;
			}
			case "Tecnico": {
				Tecnico t = new Tecnico();
				t.mostrarMenu();
				break;
			}
			case "AdministradorSist": {
				AdministradorSist adminSist = new AdministradorSist();
				adminSist.mostrarMenu();
				break;
			}
			}
			System.out.println("\n\n\n-----------------------------------");

		}

	}
}
