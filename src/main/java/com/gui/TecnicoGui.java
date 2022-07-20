package com.gui;

import com.*;
import com.gui.tecnico.RevisarVisitaGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static com.EstadoVisita.PROGRAMADO;

public class TecnicoGui extends JFrame {

	private JPanel contentPane;

	public TecnicoGui(Usuario tecnico) {

		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				new LoginGui();
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
		});

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
		
		JLabel bienvenidoLabel = new JLabel("Bienvenid@\n " + tecnico.getUsuario() + " \n,Id de tecnico: " + ((Tecnico) tecnico.getRol()).getId());
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
		serviciosLabel.setBounds(554, 123, 251, 39);
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
		
		JButton revisarBtn = new JButton("Revisar servicios");
		revisarBtn.setBounds(607, 686, 157, 41);
		revisarBtn.setFocusable(false);
		revisarBtn.addActionListener(e -> {
			int answer = JOptionPane.showConfirmDialog(null, "Desea dar por revisar un servicio?", "Revisar servicio", JOptionPane.YES_NO_OPTION);
			if(answer == 0) {
				String idVisita = JOptionPane.showInputDialog(null, "Ingrese el id de la visita a revisar", "Revisar servicio", JOptionPane.INFORMATION_MESSAGE);
				if(Empresa.getInstancia().getVisitas().get(Integer.valueOf(idVisita)).getEstado() == PROGRAMADO) {
					new RevisarVisitaGUI(tecnico, idVisita);
				} else {
					JOptionPane.showMessageDialog(null, "La visita con id " + idVisita + " ya fue revisada.", "Revisar servicio", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		serviciosPanel.add(revisarBtn);

		JPanel agendaPanel = new JPanel();
		tabbedPane.addTab("Agenda", null, agendaPanel, null);
		agendaPanel.setLayout(null);

		JList agendaList = new JList();
		agendaList.setBounds(350, 109, 665, 400);
		agendaList.setFocusable(false);
		agendaList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRend = (DefaultListCellRenderer)agendaList.getCellRenderer();
		cellRend.setHorizontalAlignment(SwingConstants .CENTER);
		List<Reserva> reservas = ((Tecnico) tecnico.getRol()).getAgenda();
		agendaList.setListData(reservas.toArray());
		agendaPanel.add(agendaList);
		
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
