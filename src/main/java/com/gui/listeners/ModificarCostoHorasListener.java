package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.ModificarCostoHorasGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarCostoHorasListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public ModificarCostoHorasListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ModificarCostoHorasGUI(administradorSistema);
    }
}
