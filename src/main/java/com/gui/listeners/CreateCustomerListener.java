package com.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.CallCenter;
import com.Usuario;
import com.gui.callCenter.CrearClienteGui;

public class CreateCustomerListener implements ActionListener {
	
	private CallCenter callCenter;

	public CreateCustomerListener (Usuario callCenter) {
		this.callCenter = (CallCenter) callCenter.getRol();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		new CrearClienteGui(callCenter);
	}

}
