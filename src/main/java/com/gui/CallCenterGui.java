package com.gui;

import com.CallCenter;
import com.Empresa;
import com.Usuario;
import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;
import com.exception.TecnicosInsuficientesException;
import com.gui.listeners.CreateCustomerListener;
import com.gui.listeners.CreateUserListener;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class CallCenterGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textoTecnicos;
	private boolean find = false;
	private JButton btnNewButton_1;
	private boolean press = false;
	private JLabel lblNewLabel_2_1;
	private JPanel panel_2;
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
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginGui();
				cerrarVentana(e);
			}
		});
		
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
		
		btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(761, 88, 89, 33);
		btnNewButton_1.addActionListener(this); 
		btnNewButton_1.setFocusable(false);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Crear");
		btnNewButton_1_1.setBounds(879, 88, 89, 33);
		btnNewButton_1_1.setFocusable(false);
		btnNewButton_1_1.addActionListener(new CreateCustomerListener(callCenter));
		panel.add(btnNewButton_1_1);
		
		lblNewLabel_2_1 = new JLabel("El cliente ingresado no existe");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(545, 55, 201, 33);
		lblNewLabel_2_1.setVisible(false);
		panel.add(lblNewLabel_2_1);
		
		
		panel_2 = new JPanel();
		panel_2.setBounds(293, 156, 833, 550);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		
		JLabel lblNewLabel_2_2 = new JLabel("Ingresar d\u00EDa");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(226, 28, 169, 33);
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Ingresar horario inicio");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(226, 162, 169, 33);
		panel_2.add(lblNewLabel_2_2_1);
		
		JRadioButton instalacion = new JRadioButton("Instalaci\u00F3n");
		instalacion.setSelected(true);
		instalacion.setHorizontalAlignment(SwingConstants.CENTER);
		instalacion.setBounds(262, 305, 109, 23);
		instalacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == instalacion) {
					
					tipoVisita += "Instalacion";
				}
			}
		});
		panel_2.add(instalacion);
		
		JRadioButton reparacion = new JRadioButton("Reparaci\u00F3n");
		reparacion.setHorizontalAlignment(SwingConstants.CENTER);
		reparacion.setBounds(456, 305, 109, 23);
		reparacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == reparacion) {
					
					tipoVisita += "Reparacion";
				}
			}
		});
		
		panel_2.add(reparacion);
		
		ButtonGroup group = new ButtonGroup();
		group.add(instalacion);
		group.add(reparacion);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Cantidad de tecnicos requeridos");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1_1.setBounds(155, 371, 216, 33);
		panel_2.add(lblNewLabel_2_2_1_1);
		
		textoTecnicos = new JTextField();
		textoTecnicos.setColumns(10);
		textoTecnicos.setBounds(420, 373, 169, 33);
		panel_2.add(textoTecnicos);
		
		JButton crearVisita = new JButton("Crear visita");
		crearVisita.setBounds(348, 475, 131, 33);
		crearVisita.setFocusable(false);
		panel_2.add(crearVisita);
		
		textoDia = new JTextField();
		textoDia.setBounds(420, 30, 169, 33);
		panel_2.add(textoDia);
		textoDia.setColumns(10);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Ingresar mes");
		lblNewLabel_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_2.setBounds(226, 93, 169, 33);
		lblNewLabel_2_2_2.setVisible(false);
		panel_2.add(lblNewLabel_2_2_2);
		
		textoMes = new JTextField();
		textoMes.setColumns(10);
		textoMes.setBounds(420, 101, 169, 33);
		textoMes.setVisible(false);
		panel_2.add(textoMes);
		
		textoInicio = new JTextField();
		textoInicio.setColumns(10);
		textoInicio.setBounds(420, 162, 169, 33);
		panel_2.add(textoInicio);
		
		textoFin = new JTextField();
		textoFin.setColumns(10);
		textoFin.setBounds(420, 224, 169, 33);
		panel_2.add(textoFin);
		
		JLabel lblNewLabel_2_2_1_2 = new JLabel("Ingresar horario fin");
		lblNewLabel_2_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1_2.setBounds(226, 224, 169, 33);
		panel_2.add(lblNewLabel_2_2_1_2);
		this.setVisible(true);
		
		crearVisita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == crearVisita) {
							
							String dia = textoDia.getText();
							Integer horarioInicio = Integer.parseInt(textoInicio.getText());
							Integer horarioFin = Integer.parseInt(textoFin.getText());
							String dniCliente = textField.getText();
							Integer cantTecnicos = Integer.parseInt(textoTecnicos.getText());
							
							CallCenter cc = new CallCenter();
							
							try {
								cc.agendarVisita(dniCliente, horarioInicio, horarioFin, dia, tipoVisita, cantTecnicos);
							} catch (HorarioReservadoException e1) {
								JOptionPane.showMessageDialog(null, "El horario seleccionado se encuentra reservado","ERROR" ,JOptionPane.ERROR_MESSAGE );
							}catch (StockInsuficienteException e2) {
								JOptionPane.showMessageDialog(null, "El stock es insuficiente","ERROR" ,JOptionPane.ERROR_MESSAGE );
							}catch (TecnicosInsuficientesException e1) {
								JOptionPane.showMessageDialog(null, "Actualmente no se posee disponibilidad de la cantidad de tecnicos requerida para el servicio","ERROR" ,JOptionPane.ERROR_MESSAGE );
							}
						}
					
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
		
		if(e.getSource() == btnNewButton_1) {
			String dniCliente = textField.getText();
		
			find = Empresa.getInstancia().getClientes().containsKey(dniCliente);
			press = true;
		
			if(press != false && find != true) {
				lblNewLabel_2_1.setVisible(true);
				panel_2.setVisible(false);
			}else {
				lblNewLabel_2_1.setVisible(false);
				panel_2.setVisible(true);
			}
		}
		
	}
}
