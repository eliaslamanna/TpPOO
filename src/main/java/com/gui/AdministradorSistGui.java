package com.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.AdministradorSistema;
import com.Empresa;
import com.Usuario;
import com.gui.listeners.CreateUserListener;

import static java.util.stream.Collectors.toList;

public class AdministradorSistGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public AdministradorSistGui(Usuario administradorSistema) {
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
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(190, 843, 141, 48);
		logoutButton.setFocusable(false);
		contentPane.add(logoutButton);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginGui();
				cerrarVentana(e);
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(532, 29, 1324, 904);
		contentPane.add(tabbedPane);
		
		JLabel homeLabel = new JLabel("Bienvenid@");
		homeLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.addTab("Home", null, homeLabel, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Usuarios", null, panel, null);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(350, 109, 665, 400);
		List<Usuario> usuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
		list.setListData(usuarios.toArray());
		panel.add(list);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.setBounds(434, 600, 131, 37);
		btnNewButton_1.setFocusable(false);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new CreateUserListener(administradorSistema));
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.setBounds(834, 600, 131, 37);
		btnNewButton_2.setFocusable(false);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Modificar");
		btnNewButton_3.setBounds(638, 600, 131, 37);
		btnNewButton_3.setFocusable(false);
		panel.add(btnNewButton_3);
		
		JButton refreshButton = new JButton("");
		refreshButton.setBounds(225, 109, 63, 58);
		refreshButton.setFocusable(false);
		refreshButton.setIcon(refresh);
		panel.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Usuario> refreshUsuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
				list.setListData(refreshUsuarios.toArray());
			}
		});
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tecnicos", null, panel_2, null);
		panel_2.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(352, 109, 665, 400);
		panel_2.add(list_1);

		
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

	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

}
