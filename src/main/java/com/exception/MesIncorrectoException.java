package com.exception;

public class MesIncorrectoException extends Exception {
    public MesIncorrectoException() {
        super("El mes es incorrecto.");
    }
}
