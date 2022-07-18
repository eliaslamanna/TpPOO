package com.exception;

public class ArticuloYaExisteException extends Exception {
    public ArticuloYaExisteException(String nombre) {
        super("El articulo " + nombre + " ya existe en el stock.");
    }
}
