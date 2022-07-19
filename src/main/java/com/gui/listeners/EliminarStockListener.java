package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.EliminarStockGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarStockListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public EliminarStockListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new EliminarStockGUI(administradorSistema);
    }
}
