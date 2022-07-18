package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Seniority;
import com.exception.RolNoExisteException;
import com.exception.SeniorityNoExisteException;
import com.exception.TurnoNoExisteException;
import com.exception.UsuarioYaExisteException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuarioGui extends JFrame {

    public CrearUsuarioGui(AdministradorSistema administradorSistema) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Crear Usuario");

        JLabel usuarioLabel = new JLabel(" Usuario");
        JTextField usuario = new JTextField();

        JLabel contraseniaLabel = new JLabel(" Contrase\u00F1a");
        JTextField contrasenia = new JTextField();

        JLabel rolLabel = new JLabel(" Rol");
        JTextField rol = new JTextField();

        JLabel seniorityLabel = new JLabel(" Seniority");
        JTextField seniority = new JTextField();
        seniority.setEnabled(false);

        JLabel turnoLabel = new JLabel(" Turno");
        JTextField turno = new JTextField();
        turno.setEnabled(false);

        JButton crearButton = new JButton("Crear");
        JButton cancelarButton = new JButton("Cancelar");

        JPanel crearUsuarioForm = new JPanel();
        crearUsuarioForm.setLayout(new GridLayout(6,2,10,6));
        crearUsuarioForm.add(usuarioLabel);
        crearUsuarioForm.add(usuario);
        crearUsuarioForm.add(contraseniaLabel);
        crearUsuarioForm.add(contrasenia);
        crearUsuarioForm.add(rolLabel);
        crearUsuarioForm.add(rol);
        crearUsuarioForm.add(seniorityLabel);
        crearUsuarioForm.add(seniority);
        crearUsuarioForm.add(turnoLabel);
        crearUsuarioForm.add(turno);

        crearUsuarioForm.add(crearButton);
        crearUsuarioForm.add(cancelarButton);

        JPanel crearUsuarioPanel = new JPanel();
        crearUsuarioPanel.setLayout(new BorderLayout(10,10));
        crearUsuarioPanel.add(crearUsuarioForm,BorderLayout.NORTH);
        crearUsuarioPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    administradorSistema.guardarUsuario(usuario.getText(),contrasenia.getText(),rol.getText(), seniority.getText(),turno.getText());
                    JOptionPane.showMessageDialog(crearUsuarioPanel,"El usuario se guardo con exito.");
                    cerrarVentana(e);
                } catch (RolNoExisteException ex) {
                    JOptionPane.showMessageDialog(crearUsuarioPanel,"El rol que quiere agregar no existe.");
                } catch (UsuarioYaExisteException ex) {
                    JOptionPane.showMessageDialog(crearUsuarioPanel,"El usuario ya existe en la base de datos.");
                } catch (SeniorityNoExisteException ex) {
                    JOptionPane.showMessageDialog(crearUsuarioPanel,"El seniority ingresado no existe.");
                } catch (TurnoNoExisteException ex) {
                    JOptionPane.showMessageDialog(crearUsuarioPanel,"El turno ingresado no existe.");
                }
            }
        });

        rol.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setVisibilidad();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setVisibilidad();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            public void setVisibilidad() {
                if (rol.getText().equalsIgnoreCase("Tecnico")){
                    seniority.setEnabled(true);
                    turno.setEnabled(true);
                } else {
                    seniority.setEnabled(false);
                    turno.setEnabled(false);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana(e);
            }
        });

        add(crearUsuarioPanel);

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
