package com.exception;

public class GastoAdicionalNoExisteException extends Exception {
    public GastoAdicionalNoExisteException(String gastoAdicional) {
        super("El gasto adicional " + gastoAdicional + " no existe.");
    }
}
