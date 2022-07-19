package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.ModificarPasswordGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPasswordListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public ModificarPasswordListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ModificarPasswordGUI(administradorSistema);
    }
}
