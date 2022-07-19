package com;

import static com.EstadoVisita.EN_CURSO;
import static com.EstadoVisita.PROGRAMADO;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.Seniority.*;


public class Empresa {

	private static Empresa instancia;

	// Mapa de stock de articulos de la empresa (clave nombre de articulo, valor
	// instancia de Articulo, con contidad y sus atributos)
	private HashMap<String, Articulo> stock = new HashMap<>();

	// Lista de los empleados de la empresa (clave usuario de empleado, valor
	// instancia de Empleado)
	private HashMap<String, Usuario> usuarios = new HashMap<>();

    //Mapa de clientes (se busca por dni)
    private HashMap<String, Cliente> clientes = new HashMap<>();

    //Lista tecnicos (id de tecnico)
    private HashMap<Integer, Usuario> tecnicos = new HashMap<>();

	//Lista visitas (id visita)
	private HashMap<Integer, Visita> visitas = new HashMap<>();

	private LinkedHashMap<Seniority, Float> costoHoras = new LinkedHashMap<>();

	private Empresa() {
		costoHoras.put(JR, 100F);
		costoHoras.put(SSR, 200F);
		costoHoras.put(SR, 300F);

		List<Articulo> articulos = new ArrayList<>();

		Articulo artCable = new Articulo("Cable Coaxil",  100F, 50F);
		Articulo deco = new Articulo("Decodificador de TV",  15F, 100.3F);
		articulos.add(artCable);
		articulos.add(deco);

		this.stock.put("Cable Coaxil", artCable);
		this.stock.put("Decodificador de TV", deco);
		
		Usuario aUsuario = new Usuario(new AdministradorSistema(), "ad", "ad");
		Usuario bUsuario = new Usuario(new Tecnico(SR, "Tarde"), "tecnico", "tecnico");
		Usuario cUsuario = new Usuario(new Administrativo(), "admin", "admin");
		Usuario dUsuario = new Usuario(new CallCenter(), "callcenter", "callcenter");
		
		this.usuarios.put(aUsuario.getUsuario(), aUsuario);
		this.usuarios.put(bUsuario.getUsuario(), bUsuario);
		this.usuarios.put(cUsuario.getUsuario(), cUsuario);
		this.usuarios.put(dUsuario.getUsuario(), dUsuario);

		this.tecnicos.put(((Tecnico)bUsuario.getRol()).getId(), bUsuario);

		Cliente aCliente = new Cliente("202020", "Ramon", "Lorca","falsa 123");
		this.clientes.put(aCliente.getDniCliente(), aCliente);
		
		Cliente bCliente = new Cliente("101010", "Hector", "Lorcar","falsa 1234");
		this.clientes.put(bCliente.getDniCliente(), bCliente);
		
		List<Usuario> tecnicos = new ArrayList<>(getTecnicos().values());
		
		Articulo almuerzo = new Articulo("Almuerzo",(float) 2, (float) 200.00);
		
		Articulo combustible = new Articulo("Combustible", (float) 10, (float) 120.3);
		Articulo pintura = new Articulo("Pintura", (float) 2, (float) 130);
		Articulo clavo = new Articulo("Clavos", (float)200, (float) 2);
		
		List<Articulo> gastosAdicionales = new ArrayList<>();
		gastosAdicionales.add(combustible);
		gastosAdicionales.add(almuerzo);
		
		List<Articulo> costosAdicionales = new ArrayList<>();
		costosAdicionales.add(clavo);
		costosAdicionales.add(pintura);
		
		Reparacion aVisita = new Reparacion(aCliente, tecnicos, 1, 12, 1600, 1800);
		aVisita.setGastosAdicionales(gastosAdicionales);
		aVisita.setOtrosCostos(costosAdicionales);
		aVisita.setEstado(EN_CURSO);
		this.visitas.put(aVisita.getIdVisita(), aVisita);

		Instalacion bVisita = new Instalacion(bCliente, tecnicos, 9, 12, 1600, 1800);
		bVisita.setEstado(PROGRAMADO);
		this.visitas.put(bVisita.getIdVisita(), bVisita);
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

	public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public HashMap<Integer, Usuario> getTecnicos() {
        return tecnicos;
    }

	public HashMap<Integer, Visita> getVisitas() {
		return this.visitas;
	}

	public Cliente agregarCliente(String dni, String nombre, String apellido, String direccion) {
		this.clientes.put(dni, new Cliente(dni, nombre, apellido, direccion));
		return this.clientes.get(dni);
	}

	public void agregarTecnico(Usuario usuario, Integer idTecnico) {
		this.tecnicos.put(idTecnico, usuario);
	}

	public void agregarVisita(Visita visita) {
		this.visitas.put(visita.getIdVisita(), visita);
	}

	public boolean signIn(String usuario, String password) {
		return usuarios.containsKey(usuario) && password.equals(usuarios.get(usuario).getPassword());
    }

	//Solo da de alta usuarios administrador de sistemas, el resto se da de alta desde ese tipo de usuario
    public void singUp(String usuario, String password) {
		System.out.println("Dar de alta nuevo usuario Administrador de Sistemas:\n");

		if(Empresa.getInstancia().getUsuarios().containsKey(usuario)) {
			System.out.println("El usuario ya existe en la base de datos.");
		} else {
			Usuario nuevoUsuario = new Usuario(new AdministradorSistema(),usuario,password);
			Empresa.getInstancia().guardarUsuario(nuevoUsuario);

			System.out.println("\nEl usuario Administrador de Sistema " + usuario + " se dio de alta exitosamente.");
		}
    }

	public Usuario guardarUsuario(Usuario nuevoUsuario) {
		usuarios.put(nuevoUsuario.getUsuario(),nuevoUsuario);
		return nuevoUsuario;
	}

	public Float getCostoHora(Seniority seniority) {
		return costoHoras.get(seniority);
	}

	public LinkedHashMap<Seniority, Float> getCostoHoras() {
		return costoHoras;
	}

	public void setCostoHora(Seniority seniority, Float costo) {
		costoHoras.put(seniority, costo);
	}

}
