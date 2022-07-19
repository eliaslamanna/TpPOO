package com;

import com.exception.HorarioParaTurnoIncorrectoException;
import com.exception.HorarioReservadoException;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
    private String dniCliente;
    private String nombre;
    private String apellido;
    private String Direccion;
    private List<Reserva> agenda = new ArrayList<>();

    public Cliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Cliente(String dniCliente, String nombre, String apellido, String direccion) {
		super();
		this.dniCliente = dniCliente;
		this.nombre = nombre;
		Direccion = direccion;
        this.apellido = apellido;
	}

    public String getDniCliente() {
        return this.dniCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public List<Reserva> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Reserva> agenda) {
        this.agenda = agenda;
    }

    public boolean disponible(Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin) throws HorarioReservadoException {
        for(Reserva reserva : agenda) {
            if(dia.equals(reserva.getDia()) && mes.equals(reserva.getMes())) {
                if(horarioInicio.intValue() >= reserva.getHoraInicio() && horarioFin <= reserva.getHoraFin() + 30) {
                    throw new HorarioReservadoException();
                }
            }
        }

        return true;
    }

    public void agendarVisita(Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin) {
        agenda.add(new Reserva(dia,mes,horarioInicio,horarioFin));
    }

    @Override
    public String toString() {
        return  " Dni: " + dniCliente +
                " Nombre: " + nombre +
                " Apellido: " + apellido +
                " Direccion: " + Direccion;
    }
}
