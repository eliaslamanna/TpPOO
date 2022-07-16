package com;

public class Cliente {
	
    private int dniCliente;
    private String nombre;
    private String apellido;
    private String Direccion;
    private Agenda agenda = new Agenda();
    
    

    public Cliente(int dniCliente, String nombre, String apellido, String direccion) {
		super();
		this.dniCliente = dniCliente;
		this.nombre = nombre;
		Direccion = direccion;
        this.apellido = apellido;
	}

    public Agenda getAgenda() {
        return agenda;
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
