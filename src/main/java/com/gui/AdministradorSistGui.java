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
import javax.swing.JList;
import javax.swing.JTable;

public class AdministradorSistGui extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public AdministradorSistGui() {
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Administrador Sistema");
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon centro = new ImageIcon("images/cei.png");
		ImageIcon logo = new ImageIcon("images/cable.png");
		ImageIcon refresh = new ImageIcon("images/refresh.png");
		
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
		tabbedPane.addTab("Usuarios", null, panel, null);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(350, 109, 665, 400);
		panel.add(list);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.setBounds(434, 600, 131, 37);
		btnNewButton_1.setFocusable(false);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.setBounds(834, 600, 131, 37);
		btnNewButton_2.setFocusable(false);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Modificar");
		btnNewButton_3.setBounds(638, 600, 131, 37);
		btnNewButton_3.setFocusable(false);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(225, 109, 63, 58);
		btnNewButton_4.setFocusable(false);
		btnNewButton_4.setIcon(refresh);
		panel.add(btnNewButton_4);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tecnicos", null, panel_2, null);
		panel_2.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(352, 109, 665, 400);
		panel_2.add(list_1);
		
		JButton btnNewButton_4_1 = new JButton("");
		btnNewButton_4_1.setBounds(223, 109, 63, 58);
		btnNewButton_4_1.setFocusable(false);
		btnNewButton_4_1.setIcon(refresh);
		panel_2.add(btnNewButton_4_1);
		
		JButton btnNewButton_1_1 = new JButton("Modificar");
		btnNewButton_1_1.setBounds(647, 555, 131, 37);
		btnNewButton_1_1.setFocusable(false);
		panel_2.add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Stock", null, panel_1, null);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(350, 110, 636, 407);
		panel_1.add(table);
		
		JButton btnNewButton_4_1_1 = new JButton("");
		btnNewButton_4_1_1.setBounds(218, 110, 63, 58);
		btnNewButton_4_1_1.setFocusable(false);
		btnNewButton_4_1_1.setIcon(refresh);
		panel_1.add(btnNewButton_4_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Crear");
		btnNewButton_1_1_1.setBounds(392, 562, 131, 37);
		btnNewButton_1_1_1.setFocusable(false);
		panel_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_5 = new JButton("A\u00F1adir");
		btnNewButton_5.setBounds(610, 562, 131, 37);
		btnNewButton_5.setFocusable(false);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Eliminar");
		btnNewButton_6.setBounds(834, 562, 131, 37);
		btnNewButton_6.setFocusable(false);
		panel_1.add(btnNewButton_6);
		this.setVisible(true);
	}
}
