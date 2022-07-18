package com.gui;

import com.Usuario;
import com.gui.listeners.CreateUserListener;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class CallCenterGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	public CallCenterGui(Usuario callCenter) {
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Call Center");
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
		tabbedPane.addTab("Programar visita", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(563, 88, 169, 33);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingresar DNI del cliente");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(384, 86, 169, 33);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(761, 88, 89, 33);
		btnNewButton_1.setFocusable(false);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Crear");
		btnNewButton_1_1.setBounds(879, 88, 89, 33);
		btnNewButton_1_1.setFocusable(false);
		panel.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("El cliente ingresado no existe");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(545, 55, 201, 33);
		panel.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(293, 156, 833, 550);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("Seleccionar fecha");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(226, 28, 169, 33);
		panel_2.add(lblNewLabel_2_2);
		
		JList list = new JList();
		list.setBounds(420, 28, 169, 33);
		panel_2.add(list);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Seleccionar horario");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(226, 137, 169, 33);
		panel_2.add(lblNewLabel_2_2_1);
		
		JList list_1 = new JList();
		list_1.setBounds(420, 137, 169, 33);
		panel_2.add(list_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Instalaci\u00F3n");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setBounds(262, 250, 109, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnReparacion = new JRadioButton("Reparaci\u00F3n");
		rdbtnReparacion.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnReparacion.setBounds(459, 250, 109, 23);
		panel_2.add(rdbtnReparacion);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnReparacion);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Cantidad de tecnicos requeridos");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1_1.setBounds(155, 355, 216, 33);
		panel_2.add(lblNewLabel_2_2_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(420, 357, 169, 33);
		panel_2.add(textField_1);
		
		JButton btnNewButton_1_2 = new JButton("Crear visita");
		btnNewButton_1_2.setBounds(348, 475, 131, 33);
		btnNewButton_1_2.setFocusable(false);
		panel_2.add(btnNewButton_1_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Agenda", null, panel_1, null);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(205, 149, 972, 581);
		panel_1.add(table);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(205, 46, 63, 58);
		btnNewButton_2.setIcon(refresh);
		btnNewButton_2.setFocusable(false);
		panel_1.add(btnNewButton_2);
		this.setVisible(true);
		
	}
}
