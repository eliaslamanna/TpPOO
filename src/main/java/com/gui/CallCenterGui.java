package com.gui;

import com.CallCenter;
import com.Empresa;
import com.Usuario;
import com.exception.*;
import com.gui.listeners.AgendarVisitaListener;
import com.gui.listeners.CreateCustomerListener;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class CallCenterGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textoTecnicos;
	private boolean find = false;
	private JButton buscarButton;
	private boolean press = false;
	private JLabel clienteNoExisteLabel;
	private JPanel registrarVisitaPanel;
	private JTextField textoDia;
	private JTextField textoMes;
	private JTextField textoInicio;
	private JTextField textoFin;
	private String tipoVisita = "";

	public CallCenterGui(Usuario callCenter) {
		
		this.setBounds(100, 100, 1900, 1000);
		this.setTitle("Cable e Internet - Call Center");
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon centro = new ImageIcon("images/cei.png");
		ImageIcon logo = new ImageIcon("images/cable.png");
		
		this.setIconImage(logo.getImage());
		JLabel contentLabel = new JLabel("");
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(45, 386, 447, 228);
		contentLabel.setIcon(centro);
		contentPane.add(contentLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(190, 843, 141, 48);
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(e -> {
			new LoginGui();
			cerrarVentana(e);
		});
		
		contentPane.add(logoutButton);
		
		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanel.setBounds(532, 29, 1324, 904);
		contentPane.add(tabbedPanel);
		
		JLabel bienvenidoLabel = new JLabel("Bienvenid@ " + callCenter.getUsuario());
		bienvenidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		bienvenidoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tabbedPanel.addTab("Home", null, bienvenidoLabel, null);
		
		JPanel programarVisitaPanel = new JPanel();
		tabbedPanel.addTab("Programar visita", null, programarVisitaPanel, null);
		programarVisitaPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(563, 88, 169, 33);
		programarVisitaPanel.add(textField);
		textField.setColumns(10);
		
		JLabel dniClienteLabel = new JLabel("Ingresar DNI del cliente");
		dniClienteLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dniClienteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dniClienteLabel.setBounds(384, 86, 169, 33);
		programarVisitaPanel.add(dniClienteLabel);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(761, 88, 89, 33);
		buscarButton.addActionListener(this);
		buscarButton.setFocusable(false);
		programarVisitaPanel.add(buscarButton);
		
		JButton cargarClienteButton = new JButton("Cargar Cliente");
		cargarClienteButton.setBounds(879, 88, 120, 33);
		cargarClienteButton.setFocusable(false);
		cargarClienteButton.addActionListener(new CreateCustomerListener(callCenter));
		programarVisitaPanel.add(cargarClienteButton);
		
		clienteNoExisteLabel = new JLabel("El cliente ingresado no existe");
		clienteNoExisteLabel.setForeground(Color.RED);
		clienteNoExisteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clienteNoExisteLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clienteNoExisteLabel.setBounds(545, 55, 201, 33);
		clienteNoExisteLabel.setVisible(false);
		programarVisitaPanel.add(clienteNoExisteLabel);

		registrarVisitaPanel = new JPanel();
		registrarVisitaPanel.setBounds(293, 156, 833, 550);
		programarVisitaPanel.add(registrarVisitaPanel);
		registrarVisitaPanel.setLayout(null);
		registrarVisitaPanel.setVisible(false);
		
		JLabel ingresarDiaLabel = new JLabel("Ingresar d\u00EDa");
		ingresarDiaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarDiaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ingresarDiaLabel.setBounds(226, 28, 169, 33);
		registrarVisitaPanel.add(ingresarDiaLabel);
		
		JLabel ingresarHorarioInicioLabel = new JLabel("Ingresar horario inicio");
		ingresarHorarioInicioLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarHorarioInicioLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ingresarHorarioInicioLabel.setBounds(226, 162, 169, 33);
		registrarVisitaPanel.add(ingresarHorarioInicioLabel);

		JLabel ingresarMesLabel = new JLabel("Ingresar mes");
		ingresarMesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarMesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ingresarMesLabel.setBounds(226, 93, 169, 33);
		registrarVisitaPanel.add(ingresarMesLabel);

		textoMes = new JTextField();
		textoMes.setColumns(10);
		textoMes.setBounds(420, 101, 169, 33);
		registrarVisitaPanel.add(textoMes);

		textoInicio = new JTextField();
		textoInicio.setColumns(10);
		textoInicio.setBounds(420, 162, 169, 33);
		registrarVisitaPanel.add(textoInicio);

		textoFin = new JTextField();
		textoFin.setColumns(10);
		textoFin.setBounds(420, 224, 169, 33);
		registrarVisitaPanel.add(textoFin);

		JLabel ingresarHorarioFinLabel = new JLabel("Ingresar horario fin");
		ingresarHorarioFinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ingresarHorarioFinLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ingresarHorarioFinLabel.setBounds(226, 224, 169, 33);
		registrarVisitaPanel.add(ingresarHorarioFinLabel);

		textoDia = new JTextField();
		textoDia.setBounds(420, 30, 169, 33);
		registrarVisitaPanel.add(textoDia);
		textoDia.setColumns(10);
		this.setVisible(true);

		JRadioButton instalacionButton = new JRadioButton("Instalaci\u00F3n");
		instalacionButton.setSelected(true);
		instalacionButton.setHorizontalAlignment(SwingConstants.CENTER);
		instalacionButton.setBounds(262, 305, 109, 23);
		instalacionButton.addActionListener(e -> {
			if(e.getSource() == instalacionButton) {
				tipoVisita = "Instalacion";
			}
		});
		registrarVisitaPanel.add(instalacionButton);
		
		JRadioButton reparacionButton = new JRadioButton("Reparaci\u00F3n");
		reparacionButton.setHorizontalAlignment(SwingConstants.CENTER);
		reparacionButton.setBounds(456, 305, 109, 23);
		reparacionButton.addActionListener(e -> {
			if(e.getSource() == reparacionButton) {
				tipoVisita = "Reparacion";
			}
		});
		
		registrarVisitaPanel.add(reparacionButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(instalacionButton);
		group.add(reparacionButton);
		
		JLabel tecnicosRequeridosLabel = new JLabel("Cantidad de tecnicos requeridos");
		tecnicosRequeridosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tecnicosRequeridosLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tecnicosRequeridosLabel.setBounds(155, 371, 216, 33);
		registrarVisitaPanel.add(tecnicosRequeridosLabel);
		
		textoTecnicos = new JTextField();
		textoTecnicos.setColumns(10);
		textoTecnicos.setBounds(420, 373, 169, 33);
		registrarVisitaPanel.add(textoTecnicos);

		JButton crearVisitaButton = new JButton("Crear visita");
		crearVisitaButton.setBounds(348, 475, 131, 33);
		crearVisitaButton.setFocusable(false);
		registrarVisitaPanel.add(crearVisitaButton);



		crearVisitaButton.addActionListener(e -> {
			Integer dia = Integer.valueOf(textoDia.getText());
			Integer mes = Integer.valueOf(textoMes.getText());
			Integer horarioInicio = Integer.valueOf(textoInicio.getText());
			Integer horarioFin = Integer.valueOf(textoFin.getText());
			String dniCliente = textField.getText();
			Integer cantTecnicos = Integer.valueOf(textoTecnicos.getText());

			try {
				((CallCenter) callCenter.getRol()).agendarVisita(dniCliente, horarioInicio, horarioFin, dia, mes, tipoVisita, cantTecnicos);
				JOptionPane.showMessageDialog(null,"Se agendo la visita con exito.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
			} catch (StockInsuficienteException ex) {
				JOptionPane.showMessageDialog(null,"Stock insuficiente, comuiniquese con un administrador de sistemas para cargar mas stock.", "Agendar visita", JOptionPane.ERROR_MESSAGE);
			} catch (HorarioParaTurnoIncorrectoException ex) {
				JOptionPane.showMessageDialog(null,"El horario no corresponde con el turno de los tecnicos disponibles.", "Agendar visita", JOptionPane.ERROR_MESSAGE);
			} catch (TecnicosInsuficientesException ex) {
				JOptionPane.showMessageDialog(null,"La cantidad de tecnicos disponibles no alcanza para agendar una visita.", "Agendar visita", JOptionPane.ERROR_MESSAGE);
			} catch (HorarioReservadoException ex) {
				JOptionPane.showMessageDialog(null,"El horario elegido esta reservado, por favor elija otro.", "Agendar visita", JOptionPane.ERROR_MESSAGE);
			} catch (TiempoMinimoInstalacionIncorrectoException ex) {
				JOptionPane.showMessageDialog(null,"El tiempo minimo para una instalacion es de 100.", "Agendar visita", JOptionPane.ERROR_MESSAGE);
			} catch (TiempoMinimoReparacionIncorrectoException ex) {
				JOptionPane.showMessageDialog(null,"El tiempo minimo para una reparacion es de 30.", "Agendar visita", JOptionPane.ERROR_MESSAGE);
			}
		});

	}
	
	public void cerrarVentana(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		Window win = SwingUtilities.getWindowAncestor(comp);
		win.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buscarButton) {
			String dniCliente = textField.getText();
		
			find = Empresa.getInstancia().getClientes().containsKey(dniCliente);
			press = true;
		
			if(press != false && find != true) {
				clienteNoExisteLabel.setVisible(true);
				registrarVisitaPanel.setVisible(false);
			}else {
				clienteNoExisteLabel.setVisible(false);
				registrarVisitaPanel.setVisible(true);
			}
		}
		
	}
}
