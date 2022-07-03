package com.exception;

public class UsuarioYaExisteException extends Exception {
    public UsuarioYaExisteException(String usuario) {
        super("El usurio con usuario: " + usuario +" ya existe");
    }
}
