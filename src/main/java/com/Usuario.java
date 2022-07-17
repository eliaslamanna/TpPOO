package com;

import java.util.Objects;
public class Usuario {
    private Rol rol;
    private String usuario;
    private String password;


	public Usuario(Rol rol, String usuario, String password) {
		super();
		this.rol = rol;
		this.usuario = usuario;
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getRolString() {
		return rol.getRol();
	}

	public Rol getRol() {
		return rol;
	}
    
	public String getPassword() {
		return password;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario);
    }

	@Override
	public String toString() {
		return 	usuario +
				" --> Rol: " + rol.getRol();
	}
}
