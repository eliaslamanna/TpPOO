package com.gui.tecnico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.Usuario;
import javax.swing.SwingConstants;

public class RevisarVisitaGUI extends JFrame {

    public RevisarVisitaGUI(Usuario tecnico, String idVisita) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Revisar Servicio");

        JLabel tiempoTrabajadoLabel = new JLabel(" Tiempo trabajado");
        tiempoTrabajadoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        tiempoTrabajadoLabel.setBounds(0, 0, 202, 23);
        JTextField tiempoTrabajado = new JTextField();
        tiempoTrabajado.setBounds(212, 0, 202, 23);

        JButton otrosCostosButton = new JButton("Agregar Otros Costos");
        otrosCostosButton.setBounds(0, 34, 202, 23);
        JButton materialesAdicionalesButton = new JButton("Agregar Materiales Adicionales");
        materialesAdicionalesButton.setBounds(0, 68, 202, 23);
        JButton revisarButton = new JButton("Revisar");
        revisarButton.setBounds(0, 117, 202, 23);
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(212, 117, 202, 23);

        JPanel revisarVisitaForm = new JPanel();
        revisarVisitaForm.setBounds(10, 10, 414, 151);
        revisarVisitaForm.setLayout(null);
        revisarVisitaForm.add(tiempoTrabajadoLabel);
        revisarVisitaForm.add(tiempoTrabajado);

        revisarVisitaForm.add(otrosCostosButton);
        revisarVisitaForm.add(materialesAdicionalesButton);
        revisarVisitaForm.add(revisarButton);
        revisarVisitaForm.add(cancelarButton);

        JPanel revisarVisitaPanel = new JPanel();
        revisarVisitaPanel.setLayout(null);
        revisarVisitaPanel.add(revisarVisitaForm);
        revisarVisitaPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        getContentPane().add(revisarVisitaPanel);

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
