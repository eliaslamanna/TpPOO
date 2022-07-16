package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.AdministradorSist;
import com.Empresa;
import com.Usuario;

public class LoginGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private boolean flag = false;
	public boolean accedio = false;
	private String usuario;
	private String contrasenia;
	public String rolMenu;

	
	public LoginGui() {
		
		Empresa empresa = Empresa.getInstancia();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 315);
		this.setTitle("Cable e Internet");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		ImageIcon logo = new ImageIcon("images/cei.png");
		ImageIcon ventana = new ImageIcon("images/cable.png");
		this.setIconImage(ventana.getImage());
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 88);
		lblNewLabel.setIcon(logo);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(166, 122, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(166, 145, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(166, 176, 86, 14);
		contentPane.add(lblNewLabel_2);
		
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
				Usuario usuarioLoggeado = Empresa.getInstancia().getUsuarios().stream().filter(u -> u.getUsuario().equals(usuario)).findFirst().get();
				rolMenu = usuarioLoggeado.getRolString();
				//System.out.println(rolMenu);
					
					//System.out.println("entro");
					switch (rolMenu) {
						case "Administrativo": {
							//funcionesAdministrativo(usuarioLoggeado.getRol().mostrarMenu(), (Administrativo)usuarioLoggeado.getRol());
							//System.out.println("admin");
							new AdministradorGui();
							break;
						}
						case "Call Center": {
							//funcionesCallCenter(usuarioLoggeado.getRol().mostrarMenu(), (Callcenter)usuarioLoggeado.getRol());
							new CallCenterGui();
							break;
						}
						case "Tecnico": {
							//funcionesTecnico(usuarioLoggeado.getRol().mostrarMenu(), (Tecnico)usuarioLoggeado.getRol());
							new TecnicoGui();
							break;
						}
						case "AdministradorSist": {
							//funcionesAdministradorSistema(usuarioLoggeado.getRol().mostrarMenu(), (AdministradorSist)usuarioLoggeado.getRol());
							//System.out.println(rolMenu);
							new AdministradorSistGui();
							break;
						}
					}
			} else {
				JOptionPane.showMessageDialog(null, "Usuario ingresado no registrado, favor comunicarse con su Administrador","ERROR",JOptionPane.ERROR_MESSAGE);
			}
	}

}
