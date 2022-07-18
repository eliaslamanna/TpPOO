package com.gui.listeners;

import com.AdministradorSistema;
import com.CallCenter;
import com.Usuario;
import com.exception.RolNoExisteException;
import com.gui.administradorSistema.CrearUsuarioGui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserListener implements ActionListener {

    private JPanel contentPane;
    private JTextField textField;
    private AdministradorSistema administradorSistema;

    public CreateUserListener(Usuario administradorSistema) {
        this.administradorSistema = (AdministradorSistema) administradorSistema.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //try {
            new CrearUsuarioGui(administradorSistema);
            //administradorSistema.guardarUsuario();

    }

}
