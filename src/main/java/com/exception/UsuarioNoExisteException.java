package com.exception;

public class UsuarioNoExisteException extends Exception {
    public UsuarioNoExisteException(String usuario) {
        super("El usuario con usuario: " + usuario +" no existe");
    }
}
