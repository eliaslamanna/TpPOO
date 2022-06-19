package com.exception;

public class ArticuloNoExisteException extends Exception {

    public ArticuloNoExisteException() {
        super("El articulo no existe");
    }
}
