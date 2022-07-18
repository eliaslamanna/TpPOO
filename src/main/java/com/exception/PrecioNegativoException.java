package com.exception;

public class PrecioNegativoException extends Exception {
    public PrecioNegativoException() {
        super("El precio no puede ser negativo.");
    }
}
