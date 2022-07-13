package com;

public class Cliente {
	
    private int dniCliente;
    private String nombre;
    private String Direccion;
    private Agenda agenda = new Agenda();
    
    

    public Cliente(int dniCliente, String nombre, String direccion, Agenda agenda) {
		super();
		this.dniCliente = dniCliente;
		this.nombre = nombre;
		Direccion = direccion;
		this.agenda = agenda;
	}

	public int getDniCliente() {
        return dniCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Cliente: \n" +
                "    Dni: " + dniCliente + "\n" +
                "    Nombre: " + nombre + "\n" +
                "    Direccion: " + Direccion;
    }
}
