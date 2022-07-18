package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Seniority;
import com.exception.CostoNegativoException;
import com.exception.SeniorityNoExisteException;
import com.exception.TecnicoNoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarCostoHorasGUI extends JFrame {

    public ModificarCostoHorasGUI(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Configurar Costo Horas");

        JLabel seniorityLabel = new JLabel(" Seniority");
        JTextField seniority = new JTextField();

        JLabel costoLabel = new JLabel(" Costo Hora");
        JTextField costo = new JTextField();

        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel seniorityForm = new JPanel();
        seniorityForm.setLayout(new GridLayout(1,2,10,1));
        seniorityForm.add(seniorityLabel);
        seniorityForm.add(seniority);
        seniorityForm.add(costoLabel);
        seniorityForm.add(costo);

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
                    else if(Float.valueOf(costo.getText()) < 0) {
                        throw new CostoNegativoException();
                    }
                    administradorSistema.configurarCostoHora(Seniority.valueOf(seniority.getText()), Float.valueOf(costo.getText()));
                    JOptionPane.showMessageDialog(seniorityPanel,"El valor del Seniority " + seniority.getText() + " se modifico con exito.");
                    cerrarVentana(e);
                } catch (SeniorityNoExisteException ex) {
                    JOptionPane.showMessageDialog(seniorityPanel,"El seniority ingresado no existe.");
                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(seniorityPanel,"El costo ingresado es incorrecto.");
                } catch (CostoNegativoException ex) {
                    JOptionPane.showMessageDialog(seniorityPanel,"El costo ingresado no puede ser negativo.");
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }

}
