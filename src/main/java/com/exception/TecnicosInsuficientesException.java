package com.exception;

public class TecnicosInsuficientesException extends Exception{

	    public TecnicosInsuficientesException() {
	        super("Actualmente no se posee disponibilidad de la cantidad de tecnicos requerida para el servicio");
	    }
	
}
