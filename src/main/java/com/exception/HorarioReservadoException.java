package com.exception;

public class HorarioReservadoException extends Exception {
    public HorarioReservadoException() {
        super("El horario no esta disonible para una reserva en esa fecha");
    }
}
