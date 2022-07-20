package com.gui.tecnico;

import com.Articulo;
import com.exception.GastoAdicionalNoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EliminarGastoAdicionalGUI extends JFrame {

    public EliminarGastoAdicionalGUI(ArrayList<Articulo> gastosAdicionales) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Eliminar Gasto Adicional");

        JLabel articuloLabel = new JLabel(" Gasto Adicional");
        JTextField articulo = new JTextField();

        JButton eliminarGastoButton = new JButton("Eliminar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel eliminarGastoForm = new JPanel();
        eliminarGastoForm.setLayout(new GridLayout(3,2,10,3));
        eliminarGastoForm.add(articuloLabel);
        eliminarGastoForm.add(articulo);

        eliminarGastoForm.add(eliminarGastoButton);
        eliminarGastoForm.add(cancelarButton);

        JPanel eliminarGastoPanel = new JPanel();
        eliminarGastoPanel.setLayout(new BorderLayout(10,10));
        eliminarGastoPanel.add(eliminarGastoForm,BorderLayout.NORTH);
        eliminarGastoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        eliminarGastoButton.addActionListener(e -> {
            try {
                eliminarGastoAdicional(gastosAdicionales, articulo.getText());
                JOptionPane.showMessageDialog(null,"El gasto adicional " + articulo.getText() +" se elimino con exito.", "Eliminar gasto adicional", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"Los datos ingresados no son validos", "Eliminar gasto adicional", JOptionPane.ERROR_MESSAGE);
            } catch (GastoAdicionalNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"El gasto adicional " + articulo.getText() + " no existe.", "Agregar gasto adicional", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(eliminarGastoPanel);

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

    public void eliminarGastoAdicional(ArrayList<Articulo> articulosAdicionales, String nombre) throws GastoAdicionalNoExisteException {
        ArrayList<Articulo> articuloAEliminar = new ArrayList<>();

        for(Articulo articulo : articulosAdicionales) {
            if(nombre.equals(articulo.getNombre())) {
                articuloAEliminar.add(articulo);
            }
        }

        if(articuloAEliminar.size() == 0) {
            throw new GastoAdicionalNoExisteException(nombre);
        }

        for(Articulo articulo : articuloAEliminar) {
            articulosAdicionales.remove(articulo);
        }
    }

}

