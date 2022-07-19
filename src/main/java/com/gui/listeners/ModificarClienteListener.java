package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.ModificarClienteGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarClienteListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public ModificarClienteListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ModificarClienteGUI(administradorSistema);
    }
}
