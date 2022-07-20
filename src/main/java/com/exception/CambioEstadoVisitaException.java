package com.exception;

public class CambioEstadoVisitaException extends Exception {
    public CambioEstadoVisitaException() {
        super("No se puede cambiar el estado de la visita.");
    }
}
