package com.gui.tecnico;

import com.Articulo;
import com.exception.CantidadNegativaException;
import com.exception.PrecioNegativoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AgregarGastosAdicionalesGUI extends JFrame {

    public AgregarGastosAdicionalesGUI(ArrayList<Articulo> costosAdicionales) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Agregar Gasto Adicional");

        JLabel articuloLabel = new JLabel(" Gasto Adicional");
        JTextField articulo = new JTextField();

        JLabel cantidadLabel = new JLabel(" Cantidad");
        JTextField cantidad = new JTextField();

        JLabel precioLabel = new JLabel(" Precio");
        JTextField precio = new JTextField();

        JButton agregarGastoButton = new JButton("Agregar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel agregarGastoForm = new JPanel();
        agregarGastoForm.setLayout(new GridLayout(6,2,10,6));
        agregarGastoForm.add(articuloLabel);
        agregarGastoForm.add(articulo);
        agregarGastoForm.add(cantidadLabel);
        agregarGastoForm.add(cantidad);
        agregarGastoForm.add(precioLabel);
        agregarGastoForm.add(precio);

        agregarGastoForm.add(agregarGastoButton);
        agregarGastoForm.add(cancelarButton);

        JPanel agergarGastoPanel = new JPanel();
        agergarGastoPanel.setLayout(new BorderLayout(10,10));
        agergarGastoPanel.add(agregarGastoForm,BorderLayout.NORTH);
        agergarGastoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        agregarGastoButton.addActionListener(e -> {
            try {
                agregarMaterial(costosAdicionales, articulo.getText(), Float.valueOf(cantidad.getText()), Float.valueOf(precio.getText()));
                JOptionPane.showMessageDialog(null,"El gasto adicional " + articulo.getText() +" se agrego con exito.", "Agregar gasto adicional", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (CantidadNegativaException ex) {
                JOptionPane.showMessageDialog(null,"La cantidad del gasto adicional no puede ser negativa.","Agregar gasto adicional", JOptionPane.ERROR_MESSAGE);
            } catch (PrecioNegativoException ex) {
                JOptionPane.showMessageDialog(null,"El precio del gasto adicional no puede ser negativo.", "Agregar gasto adicional", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"Los datos ingresados no son validos", "Agregar gasto adicional", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(agergarGastoPanel);

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

    public void agregarMaterial(ArrayList<Articulo> articulosAdicionales, String nombre, float cantidad, float precio) throws CantidadNegativaException, PrecioNegativoException {
        if(cantidad < 0) {
            throw new CantidadNegativaException();
        }
        else if(precio < 0) {
            throw new PrecioNegativoException();
        }

        articulosAdicionales.add(new Articulo(nombre, cantidad, precio));
    }

}

