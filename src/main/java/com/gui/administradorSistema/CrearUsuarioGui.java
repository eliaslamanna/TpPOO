package com.gui.administradorSistema;

import com.AdministradorSistema;
import com.Usuario;
import com.exception.RolNoExisteException;
import com.exception.UsuarioYaExisteException;
import com.gui.listeners.CreateUserListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuarioGui extends JFrame {

    public CrearUsuarioGui(AdministradorSistema administradorSistema) {
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

        JPanel jpForm = new JPanel();
        jpForm.setLayout(new GridLayout(6,2,10,6));
        jpForm.add(usuarioLabel);
        jpForm.add(usuario);
        jpForm.add(contraseniaLabel);
        jpForm.add(contrasenia);
        jpForm.add(rolLabel);
        jpForm.add(rol);
        jpForm.add(seniorityLabel);
        jpForm.add(seniority);
        jpForm.add(turnoLabel);
        jpForm.add(turno);

        jpForm.add(crearButton);
        jpForm.add(cancelarButton);

        JPanel jpMainPanel = new JPanel();
        jpMainPanel.setLayout(new BorderLayout(10,10));
        jpMainPanel.add(jpForm,BorderLayout.NORTH);
        jpMainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.setIconImage(new ImageIcon("images/cable.png").getImage());
        this.setTitle("Crear Usuario");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    administradorSistema.guardarUsuario(usuario.getText(),contrasenia.getText(),rol.getText());
                    JOptionPane.showMessageDialog(jpMainPanel,"El usuario se guardo con exito.");
                    cerrarVentana(e);
                } catch (RolNoExisteException ex) {
                    JOptionPane.showMessageDialog(jpMainPanel,"El rol que quiere agregar no existe.");
                }
                catch (UsuarioYaExisteException ex) {
                    JOptionPane.showMessageDialog(jpMainPanel,"El usuario ya existe en la base de datos.");
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

        add(jpMainPanel);

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
