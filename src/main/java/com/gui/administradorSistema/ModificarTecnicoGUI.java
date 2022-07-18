package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Seniority;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        seniorityForm.setLayout(new GridLayout(1,2,10,1));
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

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!"SR".equalsIgnoreCase(seniority.getText()) && !"SSR".equalsIgnoreCase(seniority.getText()) && !"JR".equalsIgnoreCase(seniority.getText())) {
                        throw new SeniorityNoExisteException();
                    }
                    administradorSistema.cambiarSeniority(Integer.valueOf(id.getText()),Seniority.valueOf(seniority.getText()));
                    JOptionPane.showMessageDialog(seniorityPanel,"El tecnico con id " + id.getText() + " se modifico con exito.");
                    cerrarVentana(e);
                } catch (SeniorityNoExisteException ex) {
                    JOptionPane.showMessageDialog(seniorityPanel,"El seniority ingresado no existe.");
                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(seniorityPanel,"El costo ingresado es incorrecto.");
                } catch (TecnicoNoExisteException ex) {
                    JOptionPane.showMessageDialog(seniorityPanel,"El tecnico con id " + id.getText() + " no existe.");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana(e);
            }
        });

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
