package com.exception;

public class PasswordIncorrectaException extends Exception {
    public PasswordIncorrectaException() {
        super("La contraseña ingresada es incorrecta");
    }
}
