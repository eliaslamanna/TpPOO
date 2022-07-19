package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Cliente;
import com.exception.ClienteNoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModificarClienteGUI extends JFrame {

    public ModificarClienteGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Modificar Cliente");

        JLabel dniLabel = new JLabel(" DNI");
        JTextField dni = new JTextField();

        JLabel nombreLabel = new JLabel(" nombre");
        JTextField nombre = new JTextField();

        JLabel apellidoLabel = new JLabel(" apellido");
        JTextField apellido = new JTextField();

        JLabel direccionLabel = new JLabel(" direccion");
        JTextField direccion = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel modificarClienteForm = new JPanel();
        modificarClienteForm.setLayout(new GridLayout(6,2,10,6));
        modificarClienteForm.add(dniLabel);
        modificarClienteForm.add(dni);
        modificarClienteForm.add(nombreLabel);
        modificarClienteForm.add(nombre);
        modificarClienteForm.add(apellidoLabel);
        modificarClienteForm.add(apellido);
        modificarClienteForm.add(direccionLabel);
        modificarClienteForm.add(direccion);

        modificarClienteForm.add(aceptarButton);
        modificarClienteForm.add(cancelarButton);

        JPanel modificarClientePanel = new JPanel();
        modificarClientePanel.setLayout(new BorderLayout(10,10));
        modificarClientePanel.add(modificarClienteForm,BorderLayout.NORTH);
        modificarClientePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        aceptarButton.addActionListener(e -> {
            try {
                administradorSistema.modificarCliente(new Cliente(dni.getText(), nombre.getText(), apellido.getText(), direccion.getText()));
                JOptionPane.showMessageDialog(null,"El cliente con dni " + dni.getText() + " se modifico con exito.", "Modificar cliente", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"Los datos ingresados no son correctos.", "Modificar cliente", JOptionPane.ERROR_MESSAGE);
            } catch (ClienteNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"El cliente con dni " + dni.getText() + " no existe.", "Modificar cliente", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(modificarClientePanel);

        setSize(450,250);
        setMinimumSize(new Dimension(450, 250));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }

}
