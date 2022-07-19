package com.gui.listeners;

import com.CallCenter;
import com.Usuario;
import com.exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendarVisitaListener implements ActionListener {

    private CallCenter callCenter;
    private Integer dia;
    private Integer mes;
    private Integer horarioInicio;
    private Integer horarioFin;
    private String tipoVisita;
    private String dniCliente;
    private Integer cantTecnicos;

    public AgendarVisitaListener(Usuario callCenter, Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin, String tipoVisita, String dniCliente, Integer cantTecnicos) {
        this.callCenter = (CallCenter) callCenter.getRol();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            callCenter.agendarVisita(dniCliente, horarioInicio, horarioFin, dia, mes, tipoVisita, cantTecnicos);
            JOptionPane.showMessageDialog(null,"Se agendo la visita con exito.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
            cerrarVentana(e);
        } catch (StockInsuficienteException ex) {
            JOptionPane.showMessageDialog(null,"Stock insuficiente, comuiniquese con un administrador de sistemas para cargar mas stock.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
        } catch (HorarioParaTurnoIncorrectoException ex) {
            JOptionPane.showMessageDialog(null,"El horario no corresponde con el turno de los tecnicos disponibles.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
        } catch (TecnicosInsuficientesException ex) {
            JOptionPane.showMessageDialog(null,"La cantidad de tecnicos disponibles no alcanza para agendar una visita.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
        } catch (HorarioReservadoException ex) {
            JOptionPane.showMessageDialog(null,"El horario elegido esta reservado, por favor elija otro.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
        } catch (TiempoMinimoInstalacionIncorrectoException ex) {
            JOptionPane.showMessageDialog(null,"El tiempo minimo para una instalacion es de 100.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
        } catch (TiempoMinimoReparacionIncorrectoException ex) {
            JOptionPane.showMessageDialog(null,"El tiempo minimo para una reparacion es de 30.", "Agendar visita", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cerrarVentana(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }
}
