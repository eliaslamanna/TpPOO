package com;

import com.exception.RolNoExisteException;
import com.exception.UsuarioYaExisteException;
import com.gui.LoginGui;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws RolNoExisteException, UsuarioYaExisteException {
		// TODO Auto-generated method stub
		iniciarPrograma();
	}

	public static void iniciarPrograma() {
		new LoginGui();
	}
}
