package com.exception;

public class CostoNegativoException extends Exception {
    public CostoNegativoException() {
        super("El costo no puede ser negativo.");
    }
}
