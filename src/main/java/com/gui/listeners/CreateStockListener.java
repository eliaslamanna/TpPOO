package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.CrearStockGUI;
import com.gui.administradorSistema.CrearUsuarioGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStockListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public CreateStockListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CrearStockGUI(administradorSistema);
    }

}
