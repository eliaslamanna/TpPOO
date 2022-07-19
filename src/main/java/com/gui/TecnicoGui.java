package com.gui;

import com.Empresa;
import com.Tecnico;
import com.Usuario;
import com.Visita;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TecnicoGui extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public TecnicoGui(Usuario tecnico) {
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
		JLabel contentLabel = new JLabel("");
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(45, 386, 447, 228);
		contentLabel.setIcon(centro);
		contentPane.add(contentLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(190, 843, 141, 48);
		contentPane.add(logoutButton);
		logoutButton.addActionListener(e -> {
			new LoginGui();
			cerrarVentana(e);
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(532, 29, 1324, 904);
		contentPane.add(tabbedPane);
		
		JLabel bienvenidoLabel = new JLabel("Bienvenid@");
		bienvenidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		bienvenidoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.addTab("Home", null, bienvenidoLabel, null);
		
		JPanel serviciosPanel = new JPanel();
		tabbedPane.addTab("Servicios", null, serviciosPanel, null);
		serviciosPanel.setLayout(null);

		JList serviciosList = new JList();
		serviciosList.setBounds(279, 173, 783, 487);
		serviciosList.setFocusable(false);
		serviciosList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)serviciosList.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		serviciosPanel.add(serviciosList);

		JButton revisarServiciosButton = new JButton("Revisar servicios");
		revisarServiciosButton.setBounds(556, 535, 158, 41);
		serviciosPanel.add(revisarServiciosButton);
		
		JLabel serviciosLabel = new JLabel("Servicios");
		serviciosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serviciosLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		serviciosLabel.setBounds(497, 45, 251, 39);
		serviciosPanel.add(serviciosLabel);

		List<Visita> visitasTecnico = new ArrayList<>();
		Integer idTecnico = ((Tecnico) tecnico.getRol()).getId();
		for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
			for(Usuario tec : visita.getTecnicos()) {
				if(((Tecnico) tec.getRol()).getId().intValue() == idTecnico.intValue()) {
					visitasTecnico.add(visita);
				}
			}
		}
		serviciosList.setListData(visitasTecnico.toArray());

		JPanel agendaPanel = new JPanel();
		tabbedPane.addTab("Agenda", null, agendaPanel, null);
		agendaPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(205, 149, 972, 581);
		agendaPanel.add(table);
		
		JButton refreshAgendaButton = new JButton("");
		refreshAgendaButton.setBounds(205, 46, 63, 58);
		refreshAgendaButton.setIcon(refresh);
		agendaPanel.add(refreshAgendaButton);
		this.setVisible(true);
	}

	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

}
