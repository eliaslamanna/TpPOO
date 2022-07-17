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
import javax.swing.JTable;
import javax.swing.JList;

public class TecnicoGui extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public TecnicoGui() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Tecnico");
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
		
		JList list = new JList();
		list.setBounds(289, 95, 675, 397);
		panel.add(list);
		
		JButton btnNewButton_2 = new JButton("Asignar servicios");
		btnNewButton_2.setBounds(556, 535, 158, 41);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Servicios");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setBounds(497, 45, 251, 39);
		panel.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Agenda", null, panel_2, null);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setBounds(205, 149, 972, 581);
		panel_2.add(table);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(205, 46, 63, 58);
		btnNewButton_1.setIcon(refresh);
		panel_2.add(btnNewButton_1);
		this.setVisible(true);
		
		
	}
}
