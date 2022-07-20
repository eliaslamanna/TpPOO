package com.gui.tecnico;

import com.Articulo;
import com.Empresa;
import com.exception.ArticuloYaExisteException;
import com.exception.CantidadNegativaException;
import com.exception.PrecioNegativoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AgregarCostoAdicionalesGUI extends JFrame {

    public AgregarCostoAdicionalesGUI(ArrayList<Articulo> costosAdicionales) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Agregar Costo Adicional");

        JLabel articuloLabel = new JLabel(" Costo Adicional");
        JTextField articulo = new JTextField();

        JLabel cantidadLabel = new JLabel(" Cantidad");
        JTextField cantidad = new JTextField();

        JLabel precioUnidadLabel = new JLabel(" PrecioUnidad");
        JTextField precioUnidad = new JTextField();

        JButton agregarCostoButton = new JButton("Agregar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel agregarCostoForm = new JPanel();
        agregarCostoForm.setLayout(new GridLayout(6,2,10,6));
        agregarCostoForm.add(articuloLabel);
        agregarCostoForm.add(articulo);
        agregarCostoForm.add(cantidadLabel);
        agregarCostoForm.add(cantidad);
        agregarCostoForm.add(precioUnidadLabel);
        agregarCostoForm.add(precioUnidad);

        agregarCostoForm.add(agregarCostoButton);
        agregarCostoForm.add(cancelarButton);

        JPanel agergarCostoPanel = new JPanel();
        agergarCostoPanel.setLayout(new BorderLayout(10,10));
        agergarCostoPanel.add(agregarCostoForm,BorderLayout.NORTH);
        agergarCostoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        agregarCostoButton.addActionListener(e -> {
            try {
                agregarMaterial(costosAdicionales, articulo.getText(), Float.valueOf(cantidad.getText()), Float.valueOf(precioUnidad.getText()));
                JOptionPane.showMessageDialog(null,"El costo adicional " + articulo.getText() +" se agrego con exito.", "Agregar costo adicional", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (CantidadNegativaException ex) {
                JOptionPane.showMessageDialog(null,"La cantidad del costo adicional no puede ser negativa.","Agregar costo adicional", JOptionPane.ERROR_MESSAGE);
            } catch (PrecioNegativoException ex) {
                JOptionPane.showMessageDialog(null,"El precio del costo adicional no puede ser negativo.", "Agregar costo adicional", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"Los datos ingresados no son validos", "Agregar costo adicional", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(agergarCostoPanel);

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

