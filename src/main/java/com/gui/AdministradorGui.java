package com.gui;

import com.Administrativo;
import com.CallCenter;
import com.Empresa;
import com.Tecnico;
import com.Usuario;
import com.Visita;
import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;
import com.exception.TecnicosInsuficientesException;

import static com.EstadoVisita.EN_CURSO;
import static java.util.stream.Collectors.toList;

import java.awt.Font;
import java.awt.Window;

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

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class AdministradorGui extends JFrame {

private JPanel contentPane;
private JTextField textoId;
private JTextField textoTecnico;
private Administrativo administrativoR;
	
	public AdministradorGui(Usuario administrativo) {

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
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginGui();
				cerrarVentana(e);
			}
		});
		contentPane.add(logout);
		
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
		
		JButton endBtn = new JButton("Finalizar servicio");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		endBtn.setBounds(590, 582, 144, 39);
		endBtn.setFocusable(false);
		panel.add(endBtn);
		
		JList serviciosList = new JList();
		serviciosList.setBounds(420, 173, 503, 371);
		panel.add(serviciosList);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ingresar ID de tecnico");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(463, 50, 230, 30);
		panel.add(lblNewLabel_2_1);
		
		textoTecnico = new JTextField();
		textoTecnico.setColumns(10);
		textoTecnico.setBounds(463, 81, 230, 38);
		panel.add(textoTecnico);
		
		JButton searchBtn = new JButton("Buscar");
		searchBtn.setFocusable(false);
		searchBtn.setBounds(768, 81, 117, 38);
		panel.add(searchBtn);
		searchBtn.addActionListener(e -> {
			Integer idTecnico = Integer.valueOf(textoTecnico.getText());
			List<Visita> visitasTecnico = new ArrayList<>();
			for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
				for(Usuario tecnico : visita.getTecnicos()) {
					if(((Tecnico) tecnico.getRol()).getId().intValue() == idTecnico.intValue()) {
						visitasTecnico.add(visita);
					}
				}
			}
			serviciosList.setListData(visitasTecnico.toArray());
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Facturacion", null, panel_1, null);
		panel_1.setLayout(null);
		
		textoId = new JTextField();
		textoId.setBounds(463, 81, 230, 38);
		panel_1.add(textoId);
		textoId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingresar ID de visita");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(463, 47, 230, 30);
		panel_1.add(lblNewLabel_2);
		
		JButton imprimir = new JButton("Generar factura");
		imprimir.setBounds(605, 604, 150, 38);
		imprimir.setFocusable(false);
		panel_1.add(imprimir);
		
		JList facturasList = new JList();
		facturasList.setBounds(420, 173, 503, 371);
		panel_1.add(facturasList);
		
		JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchButton.setBounds(747, 81, 117, 38);
		searchButton.setFocusable(false);
		panel_1.add(searchButton);
		this.setVisible(true);
		
	}
	
	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}
}
