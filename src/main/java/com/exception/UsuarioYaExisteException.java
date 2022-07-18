package com.exception;

public class UsuarioYaExisteException extends Exception {
    public UsuarioYaExisteException(String usuario) {
        super("El usuario con usuario: " + usuario +" ya existe");
    }
}
