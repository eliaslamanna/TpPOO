package com.exception;

public class TecnicoNoExisteException extends Exception{
    public TecnicoNoExisteException(Integer id) {
        super("El tecnico con id " + id + " no existe");
    }
}
