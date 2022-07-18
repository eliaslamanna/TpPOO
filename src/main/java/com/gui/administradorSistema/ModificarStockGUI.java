package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Articulo;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarStockGUI extends JFrame {

    public ModificarStockGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Modificar Stock");

        JLabel articuloLabel = new JLabel(" Articulo");
        JTextField articulo = new JTextField();

        JLabel cantidadLabel = new JLabel(" Cantidad");
        JTextField cantidad = new JTextField();

        JLabel precioUnidadLabel = new JLabel(" Precio por Unidad");
        JTextField precioUnidad = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel modificarStockForm = new JPanel();
        modificarStockForm.setLayout(new GridLayout(6,2,10,6));
        modificarStockForm.add(articuloLabel);
        modificarStockForm.add(articulo);
        modificarStockForm.add(cantidadLabel);
        modificarStockForm.add(cantidad);
        modificarStockForm.add(precioUnidadLabel);
        modificarStockForm.add(precioUnidad);

        modificarStockForm.add(aceptarButton);
        modificarStockForm.add(cancelarButton);

        JPanel modificarStockPanel = new JPanel();
        modificarStockPanel.setLayout(new BorderLayout(10,10));
        modificarStockPanel.add(modificarStockForm,BorderLayout.NORTH);
        modificarStockPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        aceptarButton.addActionListener(e -> {
            try {
                administradorSistema.modificarArticulo(new Articulo(articulo.getText(), Float.valueOf(cantidad.getText()), Float.valueOf(precioUnidad.getText())));
                JOptionPane.showMessageDialog(modificarStockPanel,"El articulo " + articulo.getText() + " se modifico con exito.");
                cerrarVentana(e);
            } catch (ArticuloNoExisteException ex) {
                JOptionPane.showMessageDialog(modificarStockPanel,"El articulo " + articulo.getText() + " no existe.");
            } catch (CantidadNegativaException ex) {
                JOptionPane.showMessageDialog(modificarStockPanel,"La cantidad no puede ser negativa.");
            } catch (PrecioNegativoException ex) {
                JOptionPane.showMessageDialog(modificarStockPanel,"El precio por unidad no puede ser negativo.");
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(modificarStockPanel,"El costo o precio por unidad ingresado tiene que ser un numero.");
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(modificarStockPanel);

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
