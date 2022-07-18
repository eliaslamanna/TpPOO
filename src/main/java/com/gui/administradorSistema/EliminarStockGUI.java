package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarStockGUI extends JFrame {

    public EliminarStockGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Crear Usuario");

        JLabel articuloLabel = new JLabel(" Articulo");
        JTextField articulo = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel eliminarStockForm = new JPanel();
        eliminarStockForm.setLayout(new GridLayout(6,2,10,6));
        eliminarStockForm.add(articuloLabel);
        eliminarStockForm.add(articulo);

        eliminarStockForm.add(aceptarButton);
        eliminarStockForm.add(cancelarButton);

        JPanel eliminarStockPanel = new JPanel();
        eliminarStockPanel.setLayout(new BorderLayout(10,10));
        eliminarStockPanel.add(eliminarStockForm,BorderLayout.NORTH);
        eliminarStockPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        aceptarButton.addActionListener(e -> {
            try {
                administradorSistema.eliminarArticulo(articulo.getText());
                JOptionPane.showMessageDialog(eliminarStockPanel,"El articulo " + articulo.getText() + " se elimino con exito.");
                cerrarVentana(e);
            } catch (ArticuloNoExisteException ex) {
                JOptionPane.showMessageDialog(eliminarStockPanel,"El articulo " + articulo.getText() + " no existe.");
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(eliminarStockPanel);

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
