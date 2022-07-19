package com;

import com.exception.HorarioReservadoException;

public class Cliente {
	
    private String dniCliente;
    private String nombre;
    private String apellido;
    private String Direccion;
    private Agenda agenda = new Agenda();

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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public boolean disponible(String dia, Integer horarioInicio, Integer horarioFin) {
        /*Integer horario = horarioInicio;

        while(horario <= horarioFin + 30 && horario != 2000) {
            if(!agenda.getHorarios().get(dia).get(horario)) {
                return false;
            }
            horario += 30;
        }*/
        return true;
    }

    public void agendarVisita(String dia, Integer horarioInicio, Integer horarioFin) {
        this.agenda.agendarVisita(dia,horarioInicio,horarioFin);
    }

    @Override
    public String toString() {
        return  " Dni: " + dniCliente +
                " Nombre: " + nombre +
                " Apellido: " + apellido +
                " Direccion: " + Direccion;
    }
}
