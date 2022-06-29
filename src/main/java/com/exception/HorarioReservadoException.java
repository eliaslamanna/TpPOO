package com.exception;

public class HorarioReservadoException extends Exception {
    public HorarioReservadoException(String dia, Integer horario) {
        super("El horario de: " + horario + " para el dia: " + dia + " no esta disonible");
    }
}
