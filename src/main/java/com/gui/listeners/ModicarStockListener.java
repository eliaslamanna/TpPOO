package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.ModificarStockGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModicarStockListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public ModicarStockListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ModificarStockGUI(administradorSistema);
    }

}
