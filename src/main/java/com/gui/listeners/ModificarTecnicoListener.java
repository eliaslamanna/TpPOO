package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.ModificarTecnicoGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarTecnicoListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public ModificarTecnicoListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ModificarTecnicoGUI(administradorSistema);
    }

}
