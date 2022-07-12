package com.exception;

public class RolNoExisteException extends Exception {
    public RolNoExisteException(String rol) {
        super("El rol " + rol + " no existe");
    }
}
