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

	// Mapa de los empleados de la empresa (clave legajo de empleado, valor
	// instancia de Empleado)
	private List<Usuario> usuarios = new ArrayList<>();

    //Mapa de clientes
    private List<Cliente> clientes = new ArrayList<>();

    //Mapa tecnicos
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

		/* AdministradorSist adminSist = new AdministradorSist(); */
		Usuario aUsuario = new Usuario(admin, "Juan", "administrativo");
		Usuario sUsuario = new Usuario(adminSist, "Rodrigo", "admin sistema");
		Usuario cUsuario = new Usuario(cc, "Dario", "call center");

		// -----------------------------------------------------------------
		Cliente c1 = new Cliente("Pablo", "Lez", new Agenda());
		Cliente c2 = new Cliente("Pablo", "Lez", new Agenda());
		Cliente c3 = new Cliente("Mario", "Bross", new Agenda());
		// -----------------------------------------------------------------
		this.usuarios.add(aUsuario);
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
		visitasJr.add(pVisita);

		Tecnico t = new Tecnico(Seniority.JR, "Tarde", new Agenda(), visitasJr);
		Usuario tUsuario = new Usuario(t, "Leonel", "tecnico");
		this.usuarios.add(tUsuario);
		this.clientes.add(c1);
		this.clientes.add(c2);
		this.clientes.add(c3);

		//tecnicos
		this.tecnicos.add(new Tecnico(Seniority.JR,"tarde", new Agenda(), List.of(pVisita)));
		this.tecnicos.add(new Tecnico(Seniority.SR,"maniana", new Agenda(), List.of(pVisita)));
		this.tecnicos.add(new Tecnico(Seniority.SSR,"tarde", new Agenda(), List.of(pVisita)));

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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

	public void agregarCliente(int id, String nombre, String direccion, Agenda agenda) {
		Cliente cliente = this.clientes.get(id);
		if (cliente == null) {
			this.clientes.add(id, new Cliente(nombre, direccion, agenda));
		}
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

	private Integer proxLegajoEmpleado = 1;
	private Integer proxCliente = 1;
	private Integer proxIdVisita = 1;
	private Integer proxNroFactura = 1;
}
