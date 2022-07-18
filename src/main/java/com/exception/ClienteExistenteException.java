package com.exception;

public class ClienteExistenteException extends Exception{
	
	public ClienteExistenteException (String dniCliente){
		super("El cliente de DNI " + dniCliente + " ya existe");
	}

}
