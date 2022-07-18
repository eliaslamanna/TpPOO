package com.gui.administradorSistema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.*;
import com.gui.LoginGui;
import com.gui.listeners.ModificarCostoHorasListener;
import com.gui.listeners.CreateUserListener;
import com.gui.listeners.ModificarTecnicoListener;

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
		
		JList usuariosList = new JList();
		usuariosList.setBounds(350, 109, 665, 400);
		List<Usuario> usuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
		usuariosList.setListData(usuarios.toArray());
		usuariosPanel.add(usuariosList);
		
		JButton crearUsuarioButton = new JButton("Crear");
		crearUsuarioButton.setBounds(434, 600, 131, 37);
		crearUsuarioButton.setFocusable(false);
		usuariosPanel.add(crearUsuarioButton);
		crearUsuarioButton.addActionListener(new CreateUserListener(administradorSistema));
		
		JButton eliminarUsuarioButton = new JButton("Eliminar");
		eliminarUsuarioButton.setBounds(834, 600, 131, 37);
		eliminarUsuarioButton.setFocusable(false);
		usuariosPanel.add(eliminarUsuarioButton);

		JButton refreshButton = new JButton("");
		refreshButton.setBounds(225, 109, 63, 58);
		refreshButton.setFocusable(false);
		refreshButton.setIcon(refresh);
		usuariosPanel.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Usuario> refreshUsuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
				usuariosList.setListData(refreshUsuarios.toArray());
			}
		});
		
		JPanel tecnicosPanel = new JPanel();
		tabbedPane.addTab("Tecnicos", null, tecnicosPanel, null);
		tecnicosPanel.setLayout(null);

		JButton modificarTecnicoButton = new JButton("Modificar");
		modificarTecnicoButton.setBounds(638, 600, 131, 37);
		modificarTecnicoButton.setFocusable(false);
		tecnicosPanel.add(modificarTecnicoButton);
		modificarTecnicoButton.addActionListener(new ModificarTecnicoListener(administradorSistema));
		
		JList tecnicosList = new JList();
		tecnicosList.setBounds(352, 109, 665, 400);
		List<Tecnico> tecnicos = new ArrayList<>(Empresa.getInstancia().getTecnicos().values()).stream().map(tecnico -> ((Tecnico) tecnico.getRol())).collect(toList());
		tecnicosList.setListData(tecnicos.toArray());
		tecnicosPanel.add(tecnicosList);

		JButton refreshTecnicosButton = new JButton("");
		refreshTecnicosButton.setBounds(225, 109, 63, 58);
		refreshTecnicosButton.setFocusable(false);
		refreshTecnicosButton.setIcon(refresh);
		tecnicosPanel.add(refreshTecnicosButton);
		refreshTecnicosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Tecnico> refreshTecnicos = new ArrayList<>(Empresa.getInstancia().getTecnicos().values()).stream().map(tecnico -> (Tecnico) tecnico.getRol()).collect(toList());
				tecnicosList.setListData(refreshTecnicos.toArray());
			}
		});


		JPanel stockPanel = new JPanel();
		tabbedPane.addTab("Stock", null, stockPanel, null);
		stockPanel.setLayout(null);

		JList stockList = new JList();
		stockList.setBounds(352, 109, 665, 400);
		List<Articulo> articulos = new ArrayList<>(Empresa.getInstancia().getStock().values());
		stockList.setListData(articulos.toArray());
		stockPanel.add(stockList);

		JButton refreshStockButton = new JButton("");
		refreshStockButton.setBounds(218, 110, 63, 58);
		refreshStockButton.setFocusable(false);
		refreshStockButton.setIcon(refresh);
		stockPanel.add(refreshStockButton);
		refreshStockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Articulo> refreshArticulos = new ArrayList<>(Empresa.getInstancia().getStock().values());
				stockList.setListData(refreshArticulos.toArray());
			}
		});
		
		JButton crearStockButton = new JButton("Crear");
		crearStockButton.setBounds(392, 562, 131, 37);
		crearStockButton.setFocusable(false);
		stockPanel.add(crearStockButton);
		
		JButton modificarStockButton = new JButton("Modificar");
		modificarStockButton.setBounds(610, 562, 131, 37);
		modificarStockButton.setFocusable(false);
		stockPanel.add(modificarStockButton);
		
		JButton eliminarStockButton = new JButton("Eliminar");
		eliminarStockButton.setBounds(834, 562, 131, 37);
		eliminarStockButton.setFocusable(false);
		stockPanel.add(eliminarStockButton);



		JPanel costoHorasPanel = new JPanel();
		tabbedPane.addTab("Costo Horas", null, costoHorasPanel, null);
		costoHorasPanel.setLayout(null);

		JButton modificarCostoHorasButton = new JButton("Modificar");
		modificarCostoHorasButton.setBounds(638, 600, 131, 37);
		modificarCostoHorasButton.setFocusable(false);
		costoHorasPanel.add(modificarCostoHorasButton);
		modificarCostoHorasButton.addActionListener(new ModificarCostoHorasListener(administradorSistema));

		JList costoHorasList = new JList();
		costoHorasList.setBounds(352, 109, 665, 400);
		HashMap<Seniority, Float> costoHoras = Empresa.getInstancia().getCostoHoras();
		costoHorasList.setListData(costoHoras.entrySet().toArray());
		costoHorasPanel.add(costoHorasList);

		JButton refreshCostoHorasButton = new JButton("");
		refreshCostoHorasButton.setBounds(225, 109, 63, 58);
		refreshCostoHorasButton.setFocusable(false);
		refreshCostoHorasButton.setIcon(refresh);
		costoHorasPanel.add(refreshCostoHorasButton);
		refreshCostoHorasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<Seniority, Float> costoHorasRefresh = Empresa.getInstancia().getCostoHoras();
				costoHorasList.setListData(costoHoras.entrySet().toArray());
				costoHorasList.setListData(costoHorasRefresh.entrySet().toArray());
			}
		});

		this.setVisible(true);
	}

	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

}
