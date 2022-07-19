package com.gui.administradorSistema;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.toList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.*;
import com.gui.LoginGui;
import com.gui.listeners.*;
import com.gui.listeners.CreateStockListener;
import com.gui.listeners.CreateUserListener;
import com.gui.listeners.ModificarCostoHorasListener;
import com.gui.listeners.ModificarTecnicoListener;

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
		logoutButton.addActionListener(e -> {
			new LoginGui();
			cerrarVentana(e);
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
		usuariosList.setFocusable(false);
		usuariosList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)usuariosList.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants .CENTER);
		List<Usuario> usuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
		usuariosList.setListData(usuarios.toArray());
		usuariosPanel.add(usuariosList);
		
		JButton crearUsuarioButton = new JButton("Crear");
		crearUsuarioButton.setBounds(434, 600, 131, 37);
		crearUsuarioButton.setFocusable(false);
		usuariosPanel.add(crearUsuarioButton);
		crearUsuarioButton.addActionListener(new CreateUserListener(administradorSistema));
		
		JButton modificarPasswordButton = new JButton("Cambiar Contrase\\u00f1a");
		modificarPasswordButton.setBounds(834, 600, 131, 37);
		modificarPasswordButton.setFocusable(false);
		usuariosPanel.add(modificarPasswordButton);
		modificarPasswordButton.addActionListener(new ModificarPasswordListener(administradorSistema));

		JButton refreshButton = new JButton("");
		refreshButton.setBounds(225, 109, 63, 58);
		refreshButton.setFocusable(false);
		refreshButton.setIcon(refresh);
		usuariosPanel.add(refreshButton);
		refreshButton.addActionListener(e -> {
			List<Usuario> refreshUsuarios = new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
			usuariosList.setListData(refreshUsuarios.toArray());
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
		tecnicosList.setFocusable(false);
		tecnicosList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRendererr = (DefaultListCellRenderer)tecnicosList.getCellRenderer();
		cellRendererr.setHorizontalAlignment(SwingConstants .CENTER);
		List<Tecnico> tecnicos = new ArrayList<>(Empresa.getInstancia().getTecnicos().values()).stream().map(tecnico -> ((Tecnico) tecnico.getRol())).collect(toList());
		tecnicosList.setListData(tecnicos.toArray());
		tecnicosPanel.add(tecnicosList);

		JButton refreshTecnicosButton = new JButton("");
		refreshTecnicosButton.setBounds(225, 109, 63, 58);
		refreshTecnicosButton.setFocusable(false);
		refreshTecnicosButton.setIcon(refresh);
		tecnicosPanel.add(refreshTecnicosButton);
		refreshTecnicosButton.addActionListener(e -> {
			List<Tecnico> refreshTecnicos = new ArrayList<>(Empresa.getInstancia().getTecnicos().values()).stream().map(tecnico -> (Tecnico) tecnico.getRol()).collect(toList());
			tecnicosList.setListData(refreshTecnicos.toArray());
		});

		JPanel stockPanel = new JPanel();
		tabbedPane.addTab("Stock", null, stockPanel, null);
		stockPanel.setLayout(null);

		JList stockList = new JList();
		stockList.setBounds(352, 109, 665, 400);
		stockList.setFocusable(false);
		stockList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer_3 = (DefaultListCellRenderer)stockList.getCellRenderer();
		cellRenderer_3.setHorizontalAlignment(SwingConstants .CENTER);
		List<Articulo> articulos = new ArrayList<>(Empresa.getInstancia().getStock().values());
		stockList.setListData(articulos.toArray());
		stockPanel.add(stockList);

		JButton refreshStockButton = new JButton("");
		refreshStockButton.setBounds(218, 110, 63, 58);
		refreshStockButton.setFocusable(false);
		refreshStockButton.setIcon(refresh);
		stockPanel.add(refreshStockButton);
		refreshStockButton.addActionListener(e -> {
			List<Articulo> refreshArticulos = new ArrayList<>(Empresa.getInstancia().getStock().values());
			stockList.setListData(refreshArticulos.toArray());
		});
		
		JButton crearStockButton = new JButton("Crear");
		crearStockButton.setBounds(392, 562, 131, 37);
		crearStockButton.setFocusable(false);
		stockPanel.add(crearStockButton);
		crearStockButton.addActionListener(new CreateStockListener(administradorSistema));

		JButton modificarStockButton = new JButton("Modificar");
		modificarStockButton.setBounds(610, 562, 131, 37);
		modificarStockButton.setFocusable(false);
		stockPanel.add(modificarStockButton);
		modificarStockButton.addActionListener(new ModicarStockListener(administradorSistema));
		
		JButton eliminarStockButton = new JButton("Eliminar");
		eliminarStockButton.setBounds(834, 562, 131, 37);
		eliminarStockButton.setFocusable(false);
		stockPanel.add(eliminarStockButton);
		eliminarStockButton.addActionListener(new EliminarStockListener(administradorSistema));

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
		costoHorasList.setFocusable(false);
		costoHorasList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer_1 = (DefaultListCellRenderer)costoHorasList.getCellRenderer();
		cellRenderer_1.setHorizontalAlignment(SwingConstants .CENTER);
		HashMap<Seniority, Float> costoHoras = Empresa.getInstancia().getCostoHoras();
		costoHorasList.setListData(costoHoras.entrySet().toArray());
		costoHorasPanel.add(costoHorasList);

		JButton refreshCostoHorasButton = new JButton("");
		refreshCostoHorasButton.setBounds(225, 109, 63, 58);
		refreshCostoHorasButton.setFocusable(false);
		refreshCostoHorasButton.setIcon(refresh);
		costoHorasPanel.add(refreshCostoHorasButton);
		refreshCostoHorasButton.addActionListener(e -> {
			HashMap<Seniority, Float> costoHorasRefresh = Empresa.getInstancia().getCostoHoras();
			costoHorasList.setListData(costoHoras.entrySet().toArray());
			costoHorasList.setListData(costoHorasRefresh.entrySet().toArray());
		});

		JPanel clientePanel = new JPanel();
		tabbedPane.addTab("Clientes", null, clientePanel, null);
		clientePanel.setLayout(null);
		
		JList clientesList = new JList();
		clientesList.setBounds(352, 109, 665, 400);
		clientesList.setFocusable(false);
		clientesList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer_2 = (DefaultListCellRenderer)clientesList.getCellRenderer();
		cellRenderer_2.setHorizontalAlignment(SwingConstants .CENTER);
		clientePanel.add(clientesList);
		List<Cliente> clientes = new ArrayList<>(Empresa.getInstancia().getClientes().values());
		clientesList.setListData(clientes.toArray());
		clientePanel.add(clientesList);
		
		JButton refreshClientesButton = new JButton("");
		refreshClientesButton.setFocusable(false);
		refreshClientesButton.setBounds(225, 109, 63, 58);
		refreshClientesButton.setIcon(refresh);
		clientePanel.add(refreshClientesButton);
		refreshClientesButton.addActionListener(e -> {
			List<Cliente> refreshClientes = new ArrayList<>(Empresa.getInstancia().getClientes().values());
			clientesList.setListData(refreshClientes.toArray());
		});

		JButton modificarClienteButton = new JButton("Modificar");
		modificarClienteButton.setBounds(610, 562, 131, 37);
		modificarClienteButton.setFocusable(false);
		clientePanel.add(modificarClienteButton);
		modificarClienteButton.addActionListener(new ModificarClienteListener(administradorSistema));

		this.setVisible(true);
	}

	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}
}
