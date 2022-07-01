package com;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Empresa empresa = Empresa.getInstancia();
		
		
		
		
		
		/*
		 * empresa.getClientes().entrySet().forEach(e -> {
		 * System.out.println(e.getValue().toString()); });
		 */
		
		
		int opc = 0;
		Scanner read = new Scanner(System.in);
		
		System.out.println("*****\t\tSeleccione perfil\t\t*****");
		System.out.println("1) Administrativo");
		System.out.println("2) Call Center");
		System.out.println("3) Tecnico");
		System.out.println("4) Administrador del sistema");
		System.out.print("opcion:");
		opc = read.nextInt();
		
		switch (opc) {
		case 1: {
			Administrativo admin = new Administrativo();
			admin.mostrarMenu();
			break;
		}
		case 2: {
			Callcenter callC = new Callcenter();
			callC.mostrarMenu();
			break;
		}
		case 3: {
			Tecnico t = new Tecnico();
			t.mostrarMenu();
			break;
		}
		case 4: {
			AdministradorSist adminSist = new AdministradorSist();
			adminSist.mostrarMenu();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opc);
		}
	}

}
