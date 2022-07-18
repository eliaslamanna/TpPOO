package com.exception;

public class TurnoNoExisteException extends Exception {

    public TurnoNoExisteException() {
        super("El turno ingresado no existe");
    }
}
