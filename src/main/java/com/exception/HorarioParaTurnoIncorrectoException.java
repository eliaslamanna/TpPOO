package com.exception;

public class HorarioParaTurnoIncorrectoException extends Exception {
    public HorarioParaTurnoIncorrectoException() {
        super("El horario no corresponde con el turno del tecnico.");
    }
}
