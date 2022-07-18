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
		
		JPanel usuariosPanel = new JPanel();
		tabbedPane.addTab("Usuarios", null, usuariosPanel, null);
		usuariosPanel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(350, 109, 665, 400);
		List<Usuario> usuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
		list.setListData(usuarios.toArray());
		usuariosPanel.add(list);
		
		JButton crearUsuarioButton = new JButton("Crear");
		crearUsuarioButton.setBounds(434, 600, 131, 37);
		crearUsuarioButton.setFocusable(false);
		usuariosPanel.add(crearUsuarioButton);
		crearUsuarioButton.addActionListener(new CreateUserListener(administradorSistema));
		
		JButton eliminarUsuarioButton = new JButton("Eliminar");
		eliminarUsuarioButton.setBounds(834, 600, 131, 37);
		eliminarUsuarioButton.setFocusable(false);
		usuariosPanel.add(eliminarUsuarioButton);
		
		JButton ModificarUsuarioButton = new JButton("Modificar");
		ModificarUsuarioButton.setBounds(638, 600, 131, 37);
		ModificarUsuarioButton.setFocusable(false);
		usuariosPanel.add(ModificarUsuarioButton);
		
		JButton refreshButton = new JButton("");
		refreshButton.setBounds(225, 109, 63, 58);
		refreshButton.setFocusable(false);
		refreshButton.setIcon(refresh);
		usuariosPanel.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Usuario> refreshUsuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
				list.setListData(refreshUsuarios.toArray());
			}
		});
		
		JPanel tecnicosPanel = new JPanel();
		tabbedPane.addTab("Tecnicos", null, tecnicosPanel, null);
		tecnicosPanel.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(352, 109, 665, 400);
		tecnicosPanel.add(list_1);

		
		JPanel stockPanel = new JPanel();
		tabbedPane.addTab("Stock", null, stockPanel, null);
		stockPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(350, 110, 636, 407);
		stockPanel.add(table);
		
		JButton refreshStockButton = new JButton("");
		refreshStockButton.setBounds(218, 110, 63, 58);
		refreshStockButton.setFocusable(false);
		refreshStockButton.setIcon(refresh);
		stockPanel.add(refreshStockButton);
		
		JButton crearStockButton = new JButton("Crear");
		crearStockButton.setBounds(392, 562, 131, 37);
		crearStockButton.setFocusable(false);
		stockPanel.add(crearStockButton);
		
		JButton aniadirStockButton = new JButton("A\u00F1adir");
		aniadirStockButton.setBounds(610, 562, 131, 37);
		aniadirStockButton.setFocusable(false);
		stockPanel.add(aniadirStockButton);
		
		JButton eliminarStockButton = new JButton("Eliminar");
		eliminarStockButton.setBounds(834, 562, 131, 37);
		eliminarStockButton.setFocusable(false);
		stockPanel.add(eliminarStockButton);
		this.setVisible(true);
	}

	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

}
