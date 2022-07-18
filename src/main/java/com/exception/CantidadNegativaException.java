package com.exception;

public class CantidadNegativaException extends Exception {
    public CantidadNegativaException() {
        super("La cantidad no puede ser negativa.");
    }
}
