package com.exception;

public class UsuarioYaExisteException extends Exception {
    public UsuarioYaExisteException(int legajo, String usuario) {
        super("El usurio con legajo: "+ legajo +" y usuario: " + usuario +" ya existe");
    }
}
