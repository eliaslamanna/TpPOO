package com.gui.tecnico;

import com.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevisarVisitaGUI extends JFrame {

    public RevisarVisitaGUI(Usuario tecnico, String idVisita) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Revisar Servicio");

        JLabel tiempoTrabajadoLabel = new JLabel(" Tiempo trabajado");
        JTextField tiempoTrabajado = new JTextField();

        JButton otrosCostosButton = new JButton("Agregar Otros Costos");
        JButton materialesAdicionalesButton = new JButton("Agregar Materiales Adicionales");
        JButton revisarButton = new JButton("Revisar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel revisarVisitaForm = new JPanel();
        revisarVisitaForm.setLayout(new GridLayout(5,2,10,5));
        revisarVisitaForm.add(tiempoTrabajadoLabel);
        revisarVisitaForm.add(tiempoTrabajado);

        revisarVisitaForm.add(otrosCostosButton);
        revisarVisitaForm.add(materialesAdicionalesButton);
        revisarVisitaForm.add(revisarButton);
        revisarVisitaForm.add(cancelarButton);

        JPanel revisarVisitaPanel = new JPanel();
        revisarVisitaPanel.setLayout(new BorderLayout(10,10));
        revisarVisitaPanel.add(revisarVisitaForm,BorderLayout.NORTH);
        revisarVisitaPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(revisarVisitaPanel);

        setSize(450,300);
        setMinimumSize(new Dimension(450, 300));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }

}
