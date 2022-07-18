package com.exception;

public class ClienteNoExisteException extends Exception{

	public ClienteNoExisteException(String dniCliente){
		super("El cliente de DNI " + dniCliente + " no existe");
	}

}
