package com.exception;

public class VisitaNoExisteException extends Exception {
    public VisitaNoExisteException(String idVisita) {
        super("La visita con el id " + idVisita + " no existe.");
    }
}
