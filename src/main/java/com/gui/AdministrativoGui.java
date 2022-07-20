package com.gui;

import com.Administrativo;
import com.Empresa;
import com.Tecnico;
import com.Usuario;
import com.Visita;
import com.itextpdf.text.DocumentException;

import static java.util.stream.Collectors.toList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdministrativoGui extends JFrame {

private JPanel contentPane;
private JTextField textoId;
private JTextField textoTecnico;
private Administrativo admin;
	
	public AdministrativoGui(Usuario administrativo) {

		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Administrador");
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
		
		JButton logout = new JButton("Logout");
		logout.setBounds(190, 843, 141, 48);
		logout.setFocusable(false);
		logout.addActionListener(e -> {
			new LoginGui();
			cerrarVentana(e);
		});
		contentPane.add(logout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(532, 29, 1324, 904);
		contentPane.add(tabbedPane);
		
		JLabel bienvenidoLabel = new JLabel("Bienvenid@ " + administrativo.getUsuario());
		bienvenidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		bienvenidoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPane.addTab("Home", null, bienvenidoLabel, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Servicios", null, panel, null);
		panel.setLayout(null);

		JLabel idTecnicoLabel = new JLabel("Ingresar ID de tecnico");
		idTecnicoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idTecnicoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idTecnicoLabel.setBounds(463, 50, 230, 30);
		panel.add(idTecnicoLabel);

		textoTecnico = new JTextField();
		textoTecnico.setColumns(10);
		textoTecnico.setBounds(463, 81, 230, 38);
		panel.add(textoTecnico);

		JList serviciosList = new JList();
		serviciosList.setBounds(279, 173, 783, 487);
		serviciosList.setFocusable(false);
		serviciosList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)serviciosList.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants .CENTER);
		panel.add(serviciosList);
		
		JButton buscarServiciosButton = new JButton("Buscar");
		buscarServiciosButton.setFocusable(false);
		buscarServiciosButton.setBounds(768, 81, 117, 38);
		buscarServiciosButton.addActionListener(e -> {
			List<Visita> visitasTecnico = new ArrayList<>();
			try {
			Integer idTecnico = Integer.valueOf(textoTecnico.getText());
			for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
				for(Usuario tecnico : visita.getTecnicos()) {
					if(((Tecnico) tecnico.getRol()).getId().intValue() == idTecnico.intValue()) {
						visitasTecnico.add(visita);
					}
				}
			}}catch (NumberFormatException nf){
				JOptionPane.showMessageDialog(null, "El id de tecnico ingresado no es valido", "Error", JOptionPane.ERROR_MESSAGE);
			}
			serviciosList.setListData(visitasTecnico.toArray());
		});
		panel.add(buscarServiciosButton);
		
		JButton finalizarButton = new JButton("Finalizar servicio");
		finalizarButton.setFocusable(false);
		finalizarButton.setBounds(610, 699, 150, 38);
		finalizarButton.addActionListener(e -> {
			int answer = JOptionPane.showConfirmDialog(null, "Desea dar por finalizado un servicio?", "Finalizar servicio", JOptionPane.YES_NO_OPTION); 
        	if(answer == 0) {
        		String idVisita = JOptionPane.showInputDialog(null, "Ingrese el id de la visita a finalizar", "Finalizar servicio", JOptionPane.INFORMATION_MESSAGE);
        		try {
					((Administrativo) administrativo.getRol()).revisarServicios(idVisita);
        		}catch (NumberFormatException nf){
        			JOptionPane.showMessageDialog(null, "El id ingresado es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        		
    			List<Visita> visitasTecnico = new ArrayList<>();
    			for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
    				for(Usuario tecnico : visita.getTecnicos()) {
    					if(((Tecnico) tecnico.getRol()).getId().intValue() == Integer.valueOf(textoTecnico.getText()).intValue()) {
    						visitasTecnico.add(visita);
    					}
    				}
    			}
    			serviciosList.setListData(visitasTecnico.toArray());
    			
        	}});
		panel.add(finalizarButton);
		
		
		JPanel facturacionPanel = new JPanel();
		tabbedPane.addTab("Facturacion", null, facturacionPanel, null);
		facturacionPanel.setLayout(null);
		
		textoId = new JTextField();
		textoId.setBounds(463, 81, 230, 38);
		facturacionPanel.add(textoId);
		textoId.setColumns(10);
		
		JLabel idVisitaLabel = new JLabel("Ingresar ID de visita");
		idVisitaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idVisitaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idVisitaLabel.setBounds(463, 47, 230, 30);
		facturacionPanel.add(idVisitaLabel);
		
		JButton imprimirButton = new JButton("Imprimir factura");
		imprimirButton.setBounds(605, 604, 150, 38);
		imprimirButton.setFocusable(false);
		imprimirButton.addActionListener(e-> {
			int answer = JOptionPane.showConfirmDialog(null, "Desea imprimir la factura?", "Imprimir factura", JOptionPane.YES_NO_OPTION); 
        	if(answer == 0) {
        		admin = new Administrativo();
        		String idVisita = JOptionPane.showInputDialog(null, "Ingrese el id de la visita cuya factura desea imprimir", "Imprimir factura", JOptionPane.INFORMATION_MESSAGE);
        		try {
        			admin.imprimirFactura(idVisita);
        		}catch (NumberFormatException nf){
        			JOptionPane.showMessageDialog(null, "El id ingresado es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        		}catch (DocumentException de) {
        			JOptionPane.showMessageDialog(null, "La factura no se pudo imprimir", "Error", JOptionPane.ERROR_MESSAGE);
        		}catch (FileNotFoundException nf) {
        			JOptionPane.showMessageDialog(null, "La factura no se pudo imprimir", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        		
    			List<Visita> visitasTecnico = new ArrayList<>();
    			for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
    				for(Usuario tecnico : visita.getTecnicos()) {
    					if(((Tecnico) tecnico.getRol()).getId().intValue() == Integer.valueOf(textoTecnico.getText()).intValue()) {
    						visitasTecnico.add(visita);
    					}
    				}
    			}
    			serviciosList.setListData(visitasTecnico.toArray());
    			
        	}
		});
		facturacionPanel.add(imprimirButton);
		
		JList facturasList = new JList();
		facturasList.setBounds(283, 173, 794, 371);
		facturasList.setFocusable(false);
		facturasList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderera = (DefaultListCellRenderer)facturasList.getCellRenderer();
		cellRenderera.setHorizontalAlignment(SwingConstants .CENTER);
		facturacionPanel.add(facturasList);
		
		JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(e -> {
			List<Visita> visitasTecnico = new ArrayList<>();
			try{
				Integer idVisita = Integer.valueOf(textoId.getText());
				visitasTecnico = new ArrayList<>(Empresa.getInstancia().getVisitas().values()).stream().filter(visita -> visita.getIdVisita() == idVisita.intValue()).collect(toList());
			}catch (NumberFormatException nf) {
				JOptionPane.showMessageDialog(null, "El formato del id de visita ingresado es invalido", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			facturasList.setListData(visitasTecnico.toArray());
		});

		searchButton.setBounds(747, 81, 117, 38);
		searchButton.setFocusable(false);
		facturacionPanel.add(searchButton);
		this.setVisible(true);
	}
	
	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

}
