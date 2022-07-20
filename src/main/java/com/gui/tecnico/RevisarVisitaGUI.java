package com.gui.tecnico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import com.Articulo;
import com.Tecnico;
import com.Usuario;
import com.exception.StockInsuficienteException;
import com.exception.VisitaNoExisteException;

public class RevisarVisitaGUI extends JFrame {

    public RevisarVisitaGUI(Usuario tecnico, String idVisita) {
        setIconImage(new ImageIcon("images/cable.png").getImage());
        setTitle("Revisar Servicio");

        JLabel tiempoTrabajadoLabel = new JLabel(" Tiempo trabajado");
        tiempoTrabajadoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        tiempoTrabajadoLabel.setBounds(178, 11, 202, 23);
        JTextField tiempoTrabajado = new JTextField();
        tiempoTrabajado.setBounds(390, 11, 202, 23);

        JButton otrosCostosButton = new JButton("Agregar otros costos");
        otrosCostosButton.setBounds(476, 70, 202, 23);

        JButton gastosAdicionalesButton = new JButton("Agregar gastos adicionales");
        gastosAdicionalesButton.setBounds(23, 70, 202, 23);

        JButton revisarButton = new JButton("Revisar");
        revisarButton.setBounds(670, 471, 105, 23);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(785, 471, 105, 23);

        JPanel revisarVisitaForm = new JPanel();
        revisarVisitaForm.setBounds(10, 10, 900, 505);
        revisarVisitaForm.setLayout(null);
        revisarVisitaForm.add(tiempoTrabajadoLabel);
        revisarVisitaForm.add(tiempoTrabajado);

        revisarVisitaForm.add(otrosCostosButton);
        revisarVisitaForm.add(gastosAdicionalesButton);
        revisarVisitaForm.add(revisarButton);
        revisarVisitaForm.add(cancelarButton);

        JPanel revisarVisitaPanel = new JPanel();
        revisarVisitaPanel.setLayout(null);
        revisarVisitaPanel.add(revisarVisitaForm);
        
        JList adicionalList = new JList();
        adicionalList.setBounds(57, 104, 359, 259);
        adicionalList.setFocusable(false);
		adicionalList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)adicionalList.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants .CENTER);
        revisarVisitaForm.add(adicionalList);
        ArrayList<Articulo> gastosAdicionales = new ArrayList<>();

        JList costosList = new JList();
        costosList.setBounds(499, 104, 359, 259);
        costosList.setFocusable(false);
		costosList.setSelectionBackground(Color.white);
		DefaultListCellRenderer cellRendererr = (DefaultListCellRenderer)costosList.getCellRenderer();
		cellRendererr.setHorizontalAlignment(SwingConstants .CENTER);
        revisarVisitaForm.add(costosList);
        ArrayList<Articulo> otrosCostos = new ArrayList<>();

        JButton refreshGastosAdicionalesButton = new JButton("Actualizar materiales adicionales");
        refreshGastosAdicionalesButton.setBounds(131, 374, 212, 23);
        revisarVisitaForm.add(refreshGastosAdicionalesButton);
        refreshGastosAdicionalesButton.addActionListener(e -> adicionalList.setListData(gastosAdicionales.toArray()));

        JButton refreshCostosAdicionalesButton = new JButton("Actualizar costos adicionales");
        refreshCostosAdicionalesButton.setBounds(581, 374, 202, 23);
        revisarVisitaForm.add(refreshCostosAdicionalesButton);
        refreshCostosAdicionalesButton.addActionListener(e -> costosList.setListData(otrosCostos.toArray()));

        JButton eliminarGastoAdicionalButton = new JButton("Eliminar gasto adicional");
        eliminarGastoAdicionalButton.setBounds(236, 70, 202, 23);
        revisarVisitaForm.add(eliminarGastoAdicionalButton);
        
        JButton eliminarOtroCostoButton = new JButton("Eliminar otro costo");
        eliminarOtroCostoButton.setBounds(688, 70, 202, 23);
        revisarVisitaForm.add(eliminarOtroCostoButton);

        revisarVisitaPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        otrosCostosButton.addActionListener(e -> new AgregarCostoAdicionalesGUI(otrosCostos));
        gastosAdicionalesButton.addActionListener(e -> new AgregarGastosAdicionalesGUI(gastosAdicionales));
        eliminarOtroCostoButton.addActionListener(e -> new EliminarCostoAdicionalGUI(otrosCostos));
        eliminarGastoAdicionalButton.addActionListener(e -> new EliminarGastoAdicionalGUI(gastosAdicionales));

        revisarButton.addActionListener(e -> {
            try {
                ((Tecnico) tecnico.getRol()).revisarVisita(idVisita, tiempoTrabajado.getText(), gastosAdicionales, otrosCostos);
                JOptionPane.showMessageDialog(null,"El servicio con id " + idVisita +" se reviso con exito.", "Revisar Servicio", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana(e);
            } catch(NumberFormatException nf) {
                JOptionPane.showMessageDialog(null,"Los datos ingresados no son validos", "Revisar visita", JOptionPane.ERROR_MESSAGE);
            } catch (StockInsuficienteException ex) {
                JOptionPane.showMessageDialog(null,"El stock es insuficiente para realizar la visita, comuniquese con un administrador de sistema.", "Revisar visita", JOptionPane.ERROR_MESSAGE);
            } catch (VisitaNoExisteException ex) {
                JOptionPane.showMessageDialog(null,"La visita con el id " + idVisita + " no existe.", "Revisar visita", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> cerrarVentana(e));

        getContentPane().add(revisarVisitaPanel);

        setSize(936,565);
        setMinimumSize(new Dimension(450, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }
}
