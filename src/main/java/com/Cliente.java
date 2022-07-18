package com;

import com.exception.HorarioReservadoException;

public class Cliente {
	
    private String dniCliente;
    private String nombre;
    private String apellido;
    private String Direccion;
    private Agenda agenda = new Agenda();

    public Cliente(String dniCliente, String nombre, String apellido, String direccion) {
		super();
		this.dniCliente = dniCliente;
		this.nombre = nombre;
		Direccion = direccion;
        this.apellido = apellido;
	}

    public Agenda getAgenda() {
        return agenda;
    }
    
    public String getDniCliente() {
		return dniCliente;
	}

	public boolean disponible(String dia, Integer horarioInicio, Integer horarioFin) {
        Integer horario = horarioInicio;

        while(horario <= horarioFin + 30 && horario != 2000) {
            if(!agenda.getHorarios().get(dia).get(horario)) {
                return false;
            }
            horario += 30;
        }
        return true;
    }

    public void agendarVisita(String dia, Integer horarioInicio, Integer horarioFin) {
        this.agenda.agendarVisita(dia,horarioInicio,horarioFin);
    }

    @Override
    public String toString() {
        return "Cliente: \n" +
                "    Dni: " + dniCliente + "\n" +
                "    Nombre: " + nombre + "\n" +
                "    Apellido: " + apellido + "\n" +
                "    Direccion: " + Direccion;
    }
}
