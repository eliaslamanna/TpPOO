package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.exception.PasswordIncorrectaException;
import com.exception.UsuarioNoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModificarPasswordGUI extends JFrame {

    public ModificarPasswordGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Modificar Tecnico");

        JLabel usuarioLabel = new JLabel(" Usuario");
        JTextField usuario = new JTextField();

        JLabel passwordLabel = new JLabel(" Nueva Contrase\\u00f1a");
        JTextField password = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel modificarPasswordForm = new JPanel();
        modificarPasswordForm.setLayout(new GridLayout(4,2,10,4));
        modificarPasswordForm.add(usuarioLabel);
        modificarPasswordForm.add(usuario);
        modificarPasswordForm.add(passwordLabel);
        modificarPasswordForm.add(password);

        modificarPasswordForm.add(aceptarButton);
        modificarPasswordForm.add(cancelarButton);

        JPanel modificarPasswordPanel = new JPanel();
        modificarPasswordPanel.setLayout(new BorderLayout(10,10));
        modificarPasswordPanel.add(modificarPasswordForm,BorderLayout.NORTH);
        modificarPasswordPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        aceptarButton.addActionListener(e -> {
            try {
                administradorSistema.modificarPassword(usuario.getText(), password.getText());
                JOptionPane.showMessageDialog(modificarPasswordPanel,"La contrase\\u00f1a del usuario " + usuario.getText() + " se modifico con exito.");
                cerrarVentana(e);
            } catch (UsuarioNoExisteException ex) {
                JOptionPane.showMessageDialog(modificarPasswordPanel,"El usuario " + usuario.getText() + " no existe.");
            } catch (PasswordIncorrectaException ex) {
                JOptionPane.showMessageDialog(modificarPasswordPanel,"La contrase\\u00f1a " + password.getText() + " es incorrecta.");
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(modificarPasswordPanel);

        setSize(900,600);
        setMinimumSize(new Dimension(450, 300));
        setVisible(true);
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }

}
