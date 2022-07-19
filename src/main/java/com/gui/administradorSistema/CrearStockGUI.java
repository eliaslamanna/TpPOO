package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.exception.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearStockGUI extends JFrame {

    public CrearStockGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Crear Articulo");

        JLabel articuloLabel = new JLabel(" Articulo");
        JTextField articulo = new JTextField();

        JLabel cantidadLabel = new JLabel(" Cantidad");
        JTextField cantidad = new JTextField();

        JLabel precioUnidadLabel = new JLabel(" PrecioUnidad");
        JTextField precioUnidad = new JTextField();

        JButton crearButton = new JButton("Crear");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel crearArticuloForm = new JPanel();
        crearArticuloForm.setLayout(new GridLayout(6,2,10,6));
        crearArticuloForm.add(articuloLabel);
        crearArticuloForm.add(articulo);
        crearArticuloForm.add(cantidadLabel);
        crearArticuloForm.add(cantidad);
        crearArticuloForm.add(precioUnidadLabel);
        crearArticuloForm.add(precioUnidad);

        crearArticuloForm.add(crearButton);
        crearArticuloForm.add(cancelarButton);

        JPanel crearArticuloPanel = new JPanel();
        crearArticuloPanel.setLayout(new BorderLayout(10,10));
        crearArticuloPanel.add(crearArticuloForm,BorderLayout.NORTH);
        crearArticuloPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        crearButton.addActionListener(e -> {
            try {
                administradorSistema.crearArticulo(articulo.getText(), Float.valueOf(cantidad.getText()), Float.valueOf(precioUnidad.getText()));
                JOptionPane.showMessageDialog(crearArticuloPanel,"El articulo " + articulo.getText() +" se guardo con exito.");
                cerrarVentana(e);
            } catch (CantidadNegativaException ex) {
                JOptionPane.showMessageDialog(crearArticuloPanel,"La cantidad del articulo no puede ser negativa.");
            } catch (PrecioNegativoException ex) {
                JOptionPane.showMessageDialog(crearArticuloPanel,"El precio del articulo no puede ser negativo.");
            } catch (ArticuloYaExisteException ex) {
                JOptionPane.showMessageDialog(crearArticuloPanel,"El articulo " + articulo.getText() + " ya existe en el stock.");
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(crearArticuloPanel);

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
