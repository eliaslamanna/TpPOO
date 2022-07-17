package com;

import com.exception.HorarioReservadoException;
import com.exception.RolNoExisteException;
import com.exception.StockInsuficienteException;
import com.exception.UsuarioYaExisteException;
import com.gui.LoginGui;
import com.gui.TecnicoGui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) throws RolNoExisteException, UsuarioYaExisteException {
		// TODO Auto-generated method stub

		iniciarPrograma();

	}

	public static void iniciarPrograma() throws UsuarioYaExisteException, RolNoExisteException {
		
		new LoginGui();
		
		//new TecnicoGui();
		
		
		
		
		/*boolean accedio = false;


		*//*new LoginGui();
		new AdministradorGui();
		new AdministradorSistGui(); /// Test GUI ///
		new CallCenterGui();
		new TecnicoGui();*//*


		String usuario = pedirUsuario();
		String contrasenia = pedirContrasenia();

		if (Empresa.getInstancia().signIn(usuario, contrasenia)) {
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
					accedio = Empresa.getInstancia().signIn(usuario, contrasenia);
					if (!accedio) {
						System.out.println("El sistema no encontro el usuario, verifique los datos ingresados o dese de alta");
						usuario = pedirUsuario();
						contrasenia = pedirContrasenia();
					}
					break;
				case 2:
					System.out.println("Ingrese su Rol");
					String rol = read.nextLine();

					Usuario nuevoUsuario = UsuarioFactory.getInstancia().crearUsuario(rol, usuario, contrasenia);
					Empresa.getInstancia().singUp(nuevoUsuario);

					accedio = true;
					System.out.println("Loggeo exitoso");
					break;
			}
		}

		String finalUsuario = usuario;
		Usuario usuarioLoggeado = Empresa.getInstancia().getUsuarios().stream().filter(u -> u.getUsuario().equals(finalUsuario)).findFirst().get();

		switch (usuarioLoggeado.getRolString()) {
			case "Administrativo": {
				funcionesAdministrativo(usuarioLoggeado.getRol().mostrarMenu(), (Administrativo)usuarioLoggeado.getRol());
				break;
			}
			case "Call Center": {
				funcionesCallCenter(usuarioLoggeado.getRol().mostrarMenu(), (CallCenter)usuarioLoggeado.getRol());
				break;
			}
			case "Tecnico": {
				funcionesTecnico(usuarioLoggeado.getRol().mostrarMenu(), (Tecnico)usuarioLoggeado.getRol());
				break;
			}
			case "AdministradorSist": {
				funcionesAdministradorSistema(usuarioLoggeado.getRol().mostrarMenu(), (AdministradorSistema)usuarioLoggeado.getRol());
				break;
			}
		}
		System.out.println("\n\n\n-----------------------------------");
	}

	public static String pedirUsuario() {
		System.out.println("Ingrese su usuario:");
		return read.nextLine();
	}

	public static String pedirContrasenia() {
		System.out.println("Ingrese su contrasenia:");
		return read.nextLine();
	}

	public static void funcionesAdministrativo(int opcionElegida, Administrativo admin) throws UsuarioYaExisteException, RolNoExisteException {
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
		iniciarPrograma();
	}

	public static void funcionesCallCenter(int opcionElegida, CallCenter callcenter) throws UsuarioYaExisteException, RolNoExisteException {
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
						System.out.println("Nombre: ");
						String apellido = read.next();
						read.nextLine();
						System.out.println("Direccion: ");
						String direccion = read.next();
						read.nextLine();
						cliente = Empresa.getInstancia().agregarCliente(dni, nombre, apellido, direccion);
					}

					System.out.println("Ingrese los datos de la visita");
					System.out.println("Ingresar el horario de inicio: ");
					int horarioInicio = read.nextInt();
					read.nextLine();
					System.out.println("Ingresar el horario de fin: ");
					int horarioFin = read.nextInt();
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
							callcenter.agendarVisita();
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
		iniciarPrograma();
	}

	public static void funcionesTecnico(int opcionElegida, Tecnico tecnico) throws UsuarioYaExisteException, RolNoExisteException {
		while (opcionElegida != 3) {
			switch (opcionElegida) {
				case 1:
					tecnico.ejecutarServicios();
					System.out.println("Todos los servicios asignados fueron ejecutados exitosamente");
					break;
				case 2:
					System.out.println("Los servicios asignados son los listados a continuacion: ");
					tecnico.listarServicios();
					break;
			}
			opcionElegida = tecnico.mostrarMenu();
		}
		System.out.println("Saliendo del sistema . . . ");
		iniciarPrograma();
	}

	public static void funcionesAdministradorSistema(int opcionElegida, AdministradorSistema adminSist) throws UsuarioYaExisteException, RolNoExisteException {
		while (opcionElegida != 4) {
			switch (opcionElegida) {
				case 1:
					adminSist.agregarArticulo();
					System.out.println("El articulo se ha agregado al stock exitosamente");
					break;
				case 2:
					System.out.println("Ingrese el nombre del articulo del cual se va a sumar el stock: ");
					String nombreArticulo = read.nextLine();
					System.out.println("Ingrese la cantidad a agregar: ");
					float cantidad = read.nextFloat();
					read.nextLine();

					adminSist.agregarStock(nombreArticulo, cantidad);
					System.out.println("El stock se sumó exitosamente");
					break;
				case 3:
					adminSist.restarStock();
					System.out.println("El stock se restó exitosamente");
					break;
			}
			opcionElegida = adminSist.mostrarMenu();
		}
		System.out.println("Saliendo del sistema . . . \n");
		iniciarPrograma();*/
	}
}
