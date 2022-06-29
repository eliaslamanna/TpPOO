package com.exception;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException() {
        super("El stock es insuficiente");
    }
}
