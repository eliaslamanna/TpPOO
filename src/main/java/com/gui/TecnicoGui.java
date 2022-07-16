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

public class TecnicoGui extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public TecnicoGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Tecnico");
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
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(532, 29, 1324, 904);
		contentPane.add(tabbedPane);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenid@");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.addTab("Home", null, lblNewLabel_1, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Asignar servicios", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Listar servicios", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Agenda", null, panel_2, null);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setBounds(205, 149, 972, 581);
		panel_2.add(table);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(205, 46, 63, 58);
		panel_2.add(btnNewButton_1);
		this.setVisible(true);
		
		
	}
}