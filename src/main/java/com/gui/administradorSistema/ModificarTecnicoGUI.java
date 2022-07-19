package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Seniority;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModificarTecnicoGUI extends JFrame {

    public ModificarTecnicoGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Modificar Tecnico");

        JLabel idLabel = new JLabel(" idTecnico");
        JTextField id = new JTextField();

        JLabel seniorityLabel = new JLabel(" Seniority");
        JTextField seniority = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel seniorityForm = new JPanel();
        seniorityForm.setLayout(new GridLayout(4,2,10,4));
        seniorityForm.add(idLabel);
        seniorityForm.add(id);
        seniorityForm.add(seniorityLabel);
        seniorityForm.add(seniority);

        seniorityForm.add(aceptarButton);
        seniorityForm.add(cancelarButton);

        JPanel seniorityPanel = new JPanel();
        seniorityPanel.setLayout(new BorderLayout(10,10));
        seniorityPanel.add(seniorityForm,BorderLayout.NORTH);
        seniorityPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        aceptarButton.addActionListener(e -> {
            try {
                if(!"SR".equalsIgnoreCase(seniority.getText()) && !"SSR".equalsIgnoreCase(seniority.getText()) && !"JR".equalsIgnoreCase(seniority.getText())) {
                    throw new SeniorityNoExisteException();
                }
                administradorSistema.cambiarSeniority(Integer.valueOf(id.getText()),Seniority.valueOf(seniority.getText()));
                JOptionPane.showMessageDialog(null,"El tecnico con id " + id.getText() + " se modifico con exito.", "Modificar tecnico", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (SeniorityNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"El seniority ingresado no existe.", "Modificar tecnico", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"El costo ingresado es incorrecto.", "Modificar tecnico", JOptionPane.ERROR_MESSAGE);
            } catch (TecnicoNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"El tecnico con id " + id.getText() + " no existe.", "Modificar tecnico", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        add(seniorityPanel);

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
