package com;

public class Usuario {
	private static int generador = 999;
    private Rol rol;
    private int legajo;
    private String nombre;
    private String perfil;
    
	public Usuario(Rol rol, String nombre, String perfil) {
		super();
		Usuario.generador++;
		this.rol = rol;
		this.legajo = generador;
		this.nombre = nombre;
		this.perfil = perfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public int getLegajo() {
		return legajo;
	}
    
	
   
}
