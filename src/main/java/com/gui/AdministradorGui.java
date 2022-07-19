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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class AdministradorGui extends JFrame {

private JPanel contentPane;
private JTextField textoId;
private JTextField textoTecnico;
	
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
		//serviciosList.addMouseListener(mouseListener);
		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)serviciosList.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants .CENTER);
		panel.add(serviciosList);
		
		JButton buscarServiciosButton = new JButton("Buscar");
		buscarServiciosButton.setFocusable(false);
		buscarServiciosButton.setBounds(768, 81, 117, 38);
		buscarServiciosButton.addActionListener(e -> {
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
		panel.add(buscarServiciosButton);
		
		JButton finalizarButton = new JButton("Finalizar servicio");
		finalizarButton.setFocusable(false);
		finalizarButton.setBounds(610, 699, 150, 38);
		finalizarButton.addActionListener(e -> {
			int answer = JOptionPane.showConfirmDialog(null, "Desea dar por finalizado un servicio?", "Finalizar servicio", JOptionPane.YES_NO_OPTION); 
        	if(answer == 0) {
        		Administrativo admin = new Administrativo();
        		Integer idVisita = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese el id de la visita a finalizar", "Finalizar servicio", JOptionPane.INFORMATION_MESSAGE));
        		admin.revisarServicios(idVisita);
      
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
		
		JButton imprimirButton = new JButton("Generar factura");
		imprimirButton.setBounds(605, 604, 150, 38);
		imprimirButton.setFocusable(false);
		facturacionPanel.add(imprimirButton);
		
		JList facturasList = new JList();
		facturasList.setBounds(420, 173, 503, 371);
		facturacionPanel.add(facturasList);
		
		JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(e -> {
			/*Integer idVisita = Integer.valueOf(textoId.getText());
			List<Visita> visitasTecnico = new ArrayList<>();
			for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
				for(Usuario tecnico : visita.getIdVisita()) {
					if(((Tecnico) tecnico.getRol()).getId().intValue() == idTecnico.intValue()) {
						visitasTecnico.add(visita);
					}
				}
			}
			serviciosList.setListData(visitasTecnico.toArray());
			*/
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
	
	/*MouseListener mouseListener = new MouseAdapter() {
	      public void mouseClicked(MouseEvent mouseEvent) {
	        JList serviciosList = (JList) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() >= 1) {
	          int index = serviciosList.locationToIndex(mouseEvent.getPoint());
	          if (index >= 0) {
	        	int answer = JOptionPane.showConfirmDialog(null, "�Desea dar por finalizado estos servicios?", "Finalizar servicio", JOptionPane.YES_NO_OPTION); 
	        	if(answer == 0) {
	        		Administrativo admin = new Administrativo();
	        		admin.revisarServicios(Integer.valueOf(textoTecnico.getText()));
	      
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
	           
	          }
	        }
	      }
	    };
	    */
}
