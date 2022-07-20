package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
                JOptionPane.showMessageDialog(null,"El articulo " + articulo.getText() +" se guardo con exito.", "Crear articulo", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (CantidadNegativaException ex) {
                JOptionPane.showMessageDialog(null,"La cantidad del articulo no puede ser negativa.","Crear articulo", JOptionPane.ERROR_MESSAGE);
            } catch (PrecioNegativoException ex) {
                JOptionPane.showMessageDialog(null,"El precio del articulo no puede ser negativo.", "Crear articulo", JOptionPane.ERROR_MESSAGE);
            } catch (ArticuloYaExisteException ex) {
                JOptionPane.showMessageDialog(null,"El articulo " + articulo.getText() + " ya existe en el stock.", "Crear articulo", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nf) {
            	JOptionPane.showMessageDialog(null,"El dato ingresado no es valido", "Crear articulo", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(crearArticuloPanel);

        setSize(450,200);
        setMinimumSize(new Dimension(450, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }

}
