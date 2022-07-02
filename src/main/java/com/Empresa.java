package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;

public class Empresa {

	private static Empresa instancia;

	// Mapa de stock de articulos de la empresa (clave nombre de articulo, valor
	// instancia de Articulo, con contidad y sus atributos)
	private HashMap<String, Articulo> stock;

	// Mapa de los empleados de la empresa (clave legajo de empleado, valor
	// instancia de Empleado)
	private HashMap<Integer, Usuario> usuarios;

	// Mapa de clientes
	private HashMap<Integer, Cliente> clientes;

	// Mapa tecnicos
	private List<Tecnico> tecnicos;

	private Empresa() {

		Visita pVisita = new Visita();
		Visita sVisita = new Visita();
		List<Visita> visitasJr = new ArrayList<>();
		List<Visita> visitasSr = new ArrayList<>();
		
		List<Articulo> articulos = new ArrayList<>();
		this.tecnicos = new ArrayList<>();
		this.clientes = new HashMap<>();
		this.usuarios = new HashMap<>();
		this.stock = new HashMap<>();
		Administrativo admin = new Administrativo();
		AdministradorSist adminSist = new AdministradorSist();
		Callcenter cc = new Callcenter();

		/* AdministradorSist adminSist = new AdministradorSist(); */
		Usuario aUsuario = new Usuario(admin, "Juan", "administrativo");
		Usuario sUsuario = new Usuario(adminSist, "Rodrigo", "admin sistema");
		Usuario cUsuario = new Usuario(cc, "Dario", "call center");

		// -----------------------------------------------------------------
		Cliente c1 = new Cliente("Pablo", "Lez", new Agenda());
		Cliente c2 = new Cliente("Pablo", "Lez", new Agenda());
		Cliente c3 = new Cliente("Mario", "Bross", new Agenda());
		// -----------------------------------------------------------------
		this.usuarios.put(Integer.valueOf(aUsuario.getLegajo()), aUsuario);
		this.usuarios.put(Integer.valueOf(sUsuario.getLegajo()), sUsuario);
		this.usuarios.put(Integer.valueOf(cUsuario.getLegajo()), cUsuario);


		Articulo artCable = new Articulo("Cable Coaxil", 10F, 100F, 50F);
		Articulo deco = new Articulo("Decodificador de TV", 20F, 15F, 100.3F);
		articulos.add(artCable);
		articulos.add(deco);
		this.stock.put("Cable Coaxil", artCable);
		this.stock.put("Decodificador de TV", deco);
		/*
		 * this.stock.put("Modem de Internet", new Articulo("Modem de Internet", 20F,
		 * 20F, 120.5F)); this.stock.put("Divisor Coaxial", new
		 * Articulo("Divisor Coaxial", 30F, 12F, 120F));
		 * this.stock.put("Conectores RG6", new Articulo("Conectores RG6", 10F, 25F,
		 * 100F)); this.stock.put("Repuestos", new Articulo("Repuestos", 20F, 100F,
		 * 15F));
		 */

		/*
		 * try { pVisita = this.callCenter.agendarVisita(10, "Lunes", clientes.get(1),
		 * "Instalacion", new ArrayList<>(), new ArrayList<>(), 1); sVisita =
		 * this.callCenter.agendarVisita(14, "Martes", clientes.get(2), "Reparacion",
		 * new ArrayList<>(), new ArrayList<>(), 1); } catch (HorarioReservadoException
		 * | StockInsuficienteException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		pVisita.setMateriales(articulos);
		visitasJr.add(pVisita);
		/* visitasSr.add(sVisita); */

		Tecnico t = new Tecnico(Seniority.JR, "Tarde", new Agenda(), visitasJr);
		Usuario tUsuario = new Usuario(t, "Leonel", "tecnico");
		this.usuarios.put(Integer.valueOf(tUsuario.getLegajo()), tUsuario);
		this.clientes.put(c1.getIdCliente(), c1);
		this.clientes.put(c2.getIdCliente(), c2);
		this.clientes.put(c3.getIdCliente(), c3);

		/*
		 * this.tecnicos.add(new Tecnico(Seniority.JR, "tarde", new Agenda(),
		 * visitasJr)); this.tecnicos.add(new Tecnico(Seniority.SR, "maniana", new
		 * Agenda(), visitasSr));
		 */

	}

	public static Empresa getInstancia() {
		if (instancia == null) {
			instancia = new Empresa();
		}

		return instancia;
	}

	public HashMap<String, Articulo> getStock() {
		return stock;
	}

	public HashMap<Integer, Usuario> getUsuarios() {
		return usuarios;
	}

	public HashMap<Integer, Cliente> getClientes() {
		return this.clientes;
	}

	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}

	public void agregarCliente(int id, String nombre, String direccion, Agenda agenda) {
		Cliente cliente = this.clientes.get(id);
		if (cliente == null) {
			this.clientes.put(id, new Cliente(nombre, direccion, agenda));
		}
	}

	private Integer proxLegajoEmpleado = 1;
	private Integer proxCliente = 1;
	private Integer proxIdVisita = 1;
	private Integer proxNroFactura = 1;
}
