package com.exception;

public class OtroCostoNoExisteException extends Exception {
    public OtroCostoNoExisteException(String costoAdicional) {
        super("El costo adicional " + costoAdicional + " no existe.");
    }
}
