package com.exception;

public class TiempoMinimoReparacionIncorrectoException extends Exception {
    public TiempoMinimoReparacionIncorrectoException() {
        super("El tiempo minimo para reparacion es incorrecto.");
    }
}
