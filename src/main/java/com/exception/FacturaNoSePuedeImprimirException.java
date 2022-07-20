package com.exception;

public class FacturaNoSePuedeImprimirException extends Exception {
    public FacturaNoSePuedeImprimirException() {
        super("La factura no se puede imprimir.");
    }
}
