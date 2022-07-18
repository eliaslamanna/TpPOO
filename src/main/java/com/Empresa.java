package com;

import java.util.ArrayList;
import java.util.HashMap;
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

	private HashMap<Seniority, Float> costoHoras = new HashMap<>();

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
		
		/*Visita sVisita = new Visita();
		List<Visita> visitasJr = new ArrayList<>();
		List<Visita> visitasSr = new ArrayList<>();

		Administrativo admin = new Administrativo();
		AdministradorSist adminSist = new AdministradorSist();
		Callcenter cc = new Callcenter();
		Tecnico tecnico = new Tecnico();

		Usuario aUsuario = new Usuario(adminSist, "adminsist", "adminsist");
		Usuario bUsuario = new Usuario(admin, "admin", "admin");
		Usuario sUsuario = new Usuario(tecnico, "tecnico", "tecnico");
		Usuario cUsuario = new Usuario(cc, "callcenter", "callcenter");

		// -----------------------------------------------------------------
		Cliente c1 = new Cliente(40956834, "Pablo", "Lez", new Agenda());
		Cliente c2 = new Cliente(32544601,"Arturo", "Lo", new Agenda());
		Cliente c3 = new Cliente(20156398,"Mario", "Bross", new Agenda());
		// -----------------------------------------------------------------
		this.usuarios.add(aUsuario);
		this.usuarios.add(bUsuario);
		this.usuarios.add(sUsuario);
		this.usuarios.add(cUsuario);

		Visita pVisita = new Visita(c1, this.tecnicos, articulos, new ArrayList<>());
		pVisita.setEstado(EstadoVisita.FINALIZADO);
		List visitas = new ArrayList(){{
			add(pVisita);
		}};

		visitasJr.add(pVisita);

		Tecnico t = new Tecnico(Seniority.JR, "Tarde", new Agenda(), visitasJr);
		Usuario tUsuario = new Usuario(t, "Leonel", "tecnico");
		this.usuarios.add(tUsuario);
		this.clientes.put(c1.getDniCliente(), c1);
		this.clientes.put(c2.getDniCliente(), c2);
		this.clientes.put(c3.getDniCliente(), c3);

		Agenda agendaTarde1 = new Agenda();
		agendaTarde1.setTurno("Tarde");
		Agenda agendaTarde2 = new Agenda();
		agendaTarde2.setTurno("Tarde");
		Agenda agendaManiana = new Agenda();
		agendaManiana.setTurno("Mañana");
		//tecnicos
		this.tecnicos.add(new Tecnico(Seniority.JR,"tarde", agendaTarde1, visitas));
		this.tecnicos.add(new Tecnico(Seniority.SR,"mañana", agendaManiana, visitas));
		this.tecnicos.add(new Tecnico(Seniority.SSR,"tarde", agendaTarde2, visitas));*/

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

	public void setCostoHora(Seniority seniority, Float costo) {
		costoHoras.put(seniority, costo);
	}

}
