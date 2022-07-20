package com.gui.callCenter;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.CallCenter;
import com.exception.ClienteExistenteException;
import javax.swing.SwingConstants;

public class CrearClienteGui extends JFrame {

	private JPanel contentPane;

	public CrearClienteGui(CallCenter callCenter) {
		setTitle("Generar cliente");

		this.setBounds(100, 100, 450, 120);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.setIconImage(new ImageIcon("images/cable.png").getImage());
		this.setLocationRelativeTo(null);
		
		JLabel dniLabel = new JLabel(" DNI Cliente");
		dniLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dniLabel.setBounds(0, 0, 210, 23);
        JTextField dni = new JTextField();
        dni.setBounds(220, 0, 210, 23);

        JLabel nombreLabel = new JLabel(" Nombre");
        nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nombreLabel.setBounds(0, 46, 210, 23);
        JTextField nombre = new JTextField();
        nombre.setBounds(220, 46, 210, 23);

        JLabel apellidoLabel = new JLabel(" Apellido");
        apellidoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        apellidoLabel.setBounds(0, 90, 210, 23);
        
        JTextField apellido = new JTextField();
        apellido.setBounds(220, 91, 210, 23);
        

        JLabel addressLabel = new JLabel(" Dirección");
        addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        addressLabel.setBounds(0, 130, 210, 23);
        JTextField address = new JTextField();
        address.setBounds(220, 130, 210, 23);

        JButton crearButton = new JButton("Crear");
        crearButton.setBounds(220, 179, 210, 23);
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(0, 179, 210, 23);

        JPanel jpForm = new JPanel();
        jpForm.setBounds(10, 11, 445, 242);
        jpForm.setLayout(null);
        jpForm.add(dniLabel);
        jpForm.add(dni);
        jpForm.add(nombreLabel);
        jpForm.add(nombre);
        jpForm.add(apellidoLabel);
        jpForm.add(apellido);
        jpForm.add(address);
        jpForm.add(addressLabel);

        jpForm.add(crearButton);
        jpForm.add(cancelarButton);

        JPanel jpMainPanel = new JPanel();
        jpMainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        jpMainPanel.setBounds(5, 5, 813, 535);
        jpMainPanel.setLayout(null);
        jpMainPanel.add(jpForm);
        
        crearButton.addActionListener(e -> {
            try {
                callCenter.guardarCliente(dni.getText(),nombre.getText(),apellido.getText(),address.getText());
                JOptionPane.showMessageDialog(jpMainPanel,"El cliente se guardo con exito.", "Crear cliente", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch (ClienteExistenteException ex) {
                JOptionPane.showMessageDialog(jpMainPanel,"El cliente que desea agregar ya existe", "Crear cliente", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelarButton.addActionListener(e -> cerrarVentana(e));
        contentPane.setLayout(null);
        
        getContentPane().add(jpMainPanel);

        this.setSize(481,289);
        this.setMinimumSize(new Dimension(450, 300));
        this.setVisible(true);
	}
	
	  public void cerrarVentana(ActionEvent e) {
	        JComponent comp = (JComponent) e.getSource();
	        Window win = SwingUtilities.getWindowAncestor(comp);
	        win.dispose();
	    }


}
