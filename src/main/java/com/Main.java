package com;

import com.exception.HorarioReservadoException;
import com.exception.RolNoExisteException;
import com.exception.UsuarioYaExisteException;
import com.gui.LoginGui;

public class Main {
	
	public static void main(String[] args) throws RolNoExisteException, UsuarioYaExisteException, HorarioReservadoException {
		// TODO Auto-generated method stub
		iniciarPrograma();
	}

	public static void iniciarPrograma() {
		new LoginGui();
	}
}
