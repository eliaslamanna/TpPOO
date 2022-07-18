package com.gui.listeners;

import com.AdministradorSistema;
import com.Usuario;
import com.gui.administradorSistema.CrearUsuarioGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserListener implements ActionListener {

    private AdministradorSistema administradorSistema;

    public CreateUserListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CrearUsuarioGui(administradorSistema);
    }

}
