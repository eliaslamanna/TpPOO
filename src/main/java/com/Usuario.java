package com;

import java.util.Objects;

public class Usuario {
    private Rol rol;
    private int legajo;
    private String usuario;
    private String password;
    private String perfil;

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public Usuario(Rol rol, int legajo, String usuario, String password) {
        this.rol = rol;
        this.legajo = legajo;
        this.usuario = usuario;
        this.password = password;
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
