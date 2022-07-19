package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EliminarStockGUI extends JFrame {

    public EliminarStockGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Eliminar articulo");

        JLabel articuloLabel = new JLabel(" Articulo");
        JTextField articulo = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel eliminarStockForm = new JPanel();
        eliminarStockForm.setLayout(new GridLayout(3,2,10,3));
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
                JOptionPane.showMessageDialog(null,"El articulo " + articulo.getText() + " se elimino con exito.", "Eliminar articulo", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (ArticuloNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"El articulo " + articulo.getText() + " no existe.", "Eliminar articulo", JOptionPane.ERROR_MESSAGE);
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
