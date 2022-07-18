package com.exception;

public class SeniorityNoExisteException extends Exception {

    public SeniorityNoExisteException() {
        super("El Seniority ingresado no existe");
    }
}
