package com.gui.tecnico;

import com.Articulo;
import com.exception.CantidadNegativaException;
import com.exception.OtroCostoNoExisteException;
import com.exception.PrecioNegativoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EliminarCostoAdicionalGUI extends JFrame {

    public EliminarCostoAdicionalGUI(ArrayList<Articulo> costosAdicionales) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Eliminar Costo Adicional");

        JLabel articuloLabel = new JLabel(" Costo Adicional");
        JTextField articulo = new JTextField();

        JButton eliminarCostoButton = new JButton("Eliminar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel eliminarCostoForm = new JPanel();
        eliminarCostoForm.setLayout(new GridLayout(3,2,10,3));
        eliminarCostoForm.add(articuloLabel);
        eliminarCostoForm.add(articulo);

        eliminarCostoForm.add(eliminarCostoButton);
        eliminarCostoForm.add(cancelarButton);

        JPanel eliminarCostoPanel = new JPanel();
        eliminarCostoPanel.setLayout(new BorderLayout(10,10));
        eliminarCostoPanel.add(eliminarCostoForm,BorderLayout.NORTH);
        eliminarCostoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        eliminarCostoButton.addActionListener(e -> {
            try {
                eliminarMaterial(costosAdicionales, articulo.getText());
                JOptionPane.showMessageDialog(null,"El costo adicional " + articulo.getText() +" se elimino con exito.", "Eliminar costo adicional", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"Los datos ingresados no son validos", "Eliminar costo adicional", JOptionPane.ERROR_MESSAGE);
            } catch (OtroCostoNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"El costo adicional " + articulo.getText() + " no existe.", "Eliminar costo adicional", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(eliminarCostoPanel);

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

    public void eliminarMaterial(ArrayList<Articulo> articulosAdicionales, String nombre) throws OtroCostoNoExisteException {
        ArrayList<Articulo> articuloAEliminar = new ArrayList<>();

        for(Articulo articulo : articulosAdicionales) {
            if(nombre.equals(articulo.getNombre())) {
                articuloAEliminar.add(articulo);
            }
        }

        if(articuloAEliminar.size() == 0) {
            throw new OtroCostoNoExisteException(nombre);
        }

        for(Articulo articulo : articuloAEliminar) {
            articulosAdicionales.remove(articulo);
        }
    }

}

