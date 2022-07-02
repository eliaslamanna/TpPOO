package com;

import java.util.Objects;
public class Usuario {
	private static int generador = 999;
    private Rol rol;
    private int legajo;
    private String usuario;
    private String password;
    private String perfil;
    
	public Usuario(Rol rol, String usuario, String perfil) {
		super();
		Usuario.generador++;
		this.rol = rol;
		this.legajo = generador;
		this.usuario = usuario;
		this.perfil = perfil;
	}

	public Usuario(Rol rol, int legajo, String usuario, String perfil) {
		super();
		this.rol = rol;
		this.legajo = legajo;
		this.usuario = usuario;
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
    
	public String getPassword() {
		return password;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return legajo == usuario1.legajo && Objects.equals(usuario, usuario1.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legajo, usuario, password);
    }
   
}
