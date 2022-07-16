package com;

import com.exception.UsuarioYaExisteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Empresa {

	private static Empresa instancia;

	// Mapa de stock de articulos de la empresa (clave nombre de articulo, valor
	// instancia de Articulo, con contidad y sus atributos)
	private HashMap<String, Articulo> stock;

	// Lista de los empleados de la empresa (clave legajo de empleado, valor
	// instancia de Empleado)
	private List<Usuario> usuarios = new ArrayList<>();

    //Mapa de clientes
    private HashMap<Integer, Cliente> clientes = new HashMap<>();

    //Lista tecnicos
    private List<Tecnico> tecnicos = new ArrayList<>();

	private Empresa() {


		Visita sVisita = new Visita();
		List<Visita> visitasJr = new ArrayList<>();
		List<Visita> visitasSr = new ArrayList<>();
		
		List<Articulo> articulos = new ArrayList<>();
		
		this.stock = new HashMap<>();
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


		Articulo artCable = new Articulo("Cable Coaxil", 10F, 100F, 50F);
		Articulo deco = new Articulo("Decodificador de TV", 20F, 15F, 100.3F);
		articulos.add(artCable);
		articulos.add(deco);

		this.stock.put("Cable Coaxil", artCable);
		this.stock.put("Decodificador de TV", deco);

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
		this.tecnicos.add(new Tecnico(Seniority.SSR,"tarde", agendaTarde2, visitas));

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

	public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public HashMap<Integer, Cliente> getClientes() {
        return clientes;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

	public Cliente agregarCliente(int dni, String nombre, String direccion, Agenda agenda) {
		this.clientes.put(dni, new Cliente(dni, nombre, direccion, agenda));
		return this.clientes.get(dni);
	}

	public boolean signIn(String usuario, String password) {
        return usuarios.stream().filter(u -> u.getUsuario().equals(usuario) && u.getPassword().equals(password)).collect(Collectors.toList()).size() > 0;
    }

    public Usuario singUp(Usuario nuevoUsuario) throws UsuarioYaExisteException {

        if(usuarios.contains(nuevoUsuario)) {
            throw new UsuarioYaExisteException(nuevoUsuario.getUsuario());
        }

        usuarios.add(nuevoUsuario);

        return nuevoUsuario;
    }
}
