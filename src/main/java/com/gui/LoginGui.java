package com.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.Empresa;
import com.Usuario;

public class LoginGui extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private boolean flag = false;
	public boolean accedio = false;
	private String usuario;
	private String contrasenia;
	public String rolMenu;

	
	public LoginGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 315);
		this.setTitle("Cable e Internet");
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		ImageIcon logo = new ImageIcon("images/cei.png");
		ImageIcon ventana = new ImageIcon("images/cable.png");
		this.setIconImage(ventana.getImage());
		JLabel logoLabel = new JLabel("");
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setBounds(10, 11, 414, 88);
		logoLabel.setIcon(logo);
		contentPane.add(logoLabel);
		
		JLabel usuarioLabel = new JLabel("Usuario");
		usuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usuarioLabel.setBounds(166, 122, 86, 14);
		contentPane.add(usuarioLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(166, 145, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel contraseniaLabel = new JLabel("Contrase\u00F1a");
		contraseniaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contraseniaLabel.setBounds(166, 176, 86, 14);
		contentPane.add(contraseniaLabel);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(166, 197, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setBounds(166, 242, 89, 23);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		usuario = textField.getText();
		contrasenia = passwordField.getText();
		
		flag = Empresa.getInstancia().signIn(usuario, contrasenia);
		
		 if (flag == true) {
			accedio = true;
			Usuario usuarioLoggeado = Empresa.getInstancia().getUsuarios().get(usuario);
			rolMenu = usuarioLoggeado.getRol().getRol();
			switch (rolMenu) {
				case "Administrativo": {
					new AdministradorGui(usuarioLoggeado);
					cerrarVentana(e);
					break;
				}
				case "Call Center": {
					new CallCenterGui(usuarioLoggeado);
					cerrarVentana(e);
					break;
				}
				case "Tecnico": {
					new TecnicoGui(usuarioLoggeado);
					cerrarVentana(e);
					break;
				}
				case "AdministradorSist": {
					new AdministradorSistGui(usuarioLoggeado);
					cerrarVentana(e);
					break;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuario ingresado no registrado, favor comunicarse con su Administrador","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

}
