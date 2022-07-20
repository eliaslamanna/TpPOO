package com;

import static com.EstadoVisita.EN_CURSO;
import static com.EstadoVisita.PROGRAMADO;

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
		Articulo modem = new Articulo("Modem de internet",  25F, 200.3F);
		Articulo divisor = new Articulo("Divisor coaxial",  15F, 175.5F);
		Articulo conector = new Articulo("Conector RG6",  30F, 100.5F);
		articulos.add(artCable);
		articulos.add(deco);
		articulos.add(modem);
		articulos.add(divisor);
		articulos.add(conector);

		this.stock.put("Cable Coaxil", artCable);
		this.stock.put("Decodificador de TV", deco);
		this.stock.put("Modem de internet", deco);
		this.stock.put("Divisor coaxial", deco);
		this.stock.put("Conector RG6", deco);

		Usuario aUsuario = new Usuario(new AdministradorSistema(), "ad", "ad");
		Usuario bUsuario = new Usuario(new Tecnico(SR, "Tarde"), "tecnico", "tecnico");
		Usuario cUsuario = new Usuario(new Administrativo(), "admin", "admin");
		Usuario dUsuario = new Usuario(new CallCenter(), "callcenter", "callcenter");
		
		this.usuarios.put(aUsuario.getUsuario(), aUsuario);
		this.usuarios.put(bUsuario.getUsuario(), bUsuario);
		this.usuarios.put(cUsuario.getUsuario(), cUsuario);
		this.usuarios.put(dUsuario.getUsuario(), dUsuario);

		this.tecnicos.put(((Tecnico)bUsuario.getRol()).getId(), bUsuario);
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
