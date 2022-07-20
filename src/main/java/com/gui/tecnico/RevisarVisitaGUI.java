package com.gui.tecnico;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.Articulo;
import com.Usuario;
import javax.swing.SwingConstants;
import javax.swing.JList;

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
        otrosCostosButton.setBounds(551, 70, 202, 23);

        JButton materialesAdicionalesButton = new JButton("Agregar materiales adicionales");
        materialesAdicionalesButton.setBounds(141, 70, 202, 23);

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
        revisarVisitaForm.add(materialesAdicionalesButton);
        revisarVisitaForm.add(revisarButton);
        revisarVisitaForm.add(cancelarButton);

        JPanel revisarVisitaPanel = new JPanel();
        revisarVisitaPanel.setLayout(null);
        revisarVisitaPanel.add(revisarVisitaForm);
        
        JList adicionalList = new JList();
        adicionalList.setBounds(114, 104, 256, 259);
        revisarVisitaForm.add(adicionalList);

        JList costosList = new JList();
        costosList.setBounds(527, 104, 256, 259);
        revisarVisitaForm.add(costosList);
        ArrayList<Articulo> otrosCostos = new ArrayList<>();

        JButton refreshMaterialesAdicionalesButton = new JButton("Actualizar materiales adicionales");
        refreshMaterialesAdicionalesButton.setBounds(131, 374, 212, 23);
        revisarVisitaForm.add(refreshMaterialesAdicionalesButton);
        
        JButton refreshCostosAdicionalesButton = new JButton("Actualizar costos adicionales");
        refreshCostosAdicionalesButton.setBounds(551, 374, 202, 23);
        revisarVisitaForm.add(refreshCostosAdicionalesButton);
        refreshCostosAdicionalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costosList.setListData(otrosCostos.toArray());
            }
        });

        revisarVisitaPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        otrosCostosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarCostoAdicionalesGUI(otrosCostos);
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
