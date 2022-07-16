package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AdministradorGui extends JFrame {

private JPanel contentPane;
private JTextField textField;

	
	public AdministradorGui() {
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Administrador");
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon centro = new ImageIcon("images/cei.png");
		ImageIcon logo = new ImageIcon("images/cable.png");
		
		this.setIconImage(logo.getImage());
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(45, 386, 447, 228);
		lblNewLabel.setIcon(centro);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(190, 843, 141, 48);
		btnNewButton.setFocusable(false);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(532, 29, 1324, 904);
		contentPane.add(tabbedPane);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenid@");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.addTab("Home", null, lblNewLabel_1, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Servicios", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Actualizar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBounds(982, 776, 215, 39);
		btnNewButton_4.setFocusable(false);
		panel.add(btnNewButton_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(228, 125, 969, 621);
		panel.add(textPane);
		
		JLabel lblNewLabel_3 = new JLabel("Servicios");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(615, 75, 251, 39);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Facturacion", null, panel_1, null);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(553, 81, 230, 38);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingresar ID de visita");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(553, 52, 230, 30);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Generar factura");
		btnNewButton_1.setBounds(734, 609, 150, 38);
		btnNewButton_1.setFocusable(false);
		panel_1.add(btnNewButton_1);
		
		JList list = new JList();
		list.setBounds(420, 173, 503, 371);
		panel_1.add(list);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(823, 81, 117, 38);
		btnNewButton_2.setFocusable(false);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Modificar");
		btnNewButton_3.setBounds(471, 609, 150, 38);
		btnNewButton_3.setFocusable(false);
		panel_1.add(btnNewButton_3);
		this.setVisible(true);
		
	}
}
