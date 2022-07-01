package com;

public class Cliente {
	
	private static int generador = 0;
    private int idCliente;
    private String nombre;
    private String Direccion;
    private Agenda agenda = new Agenda();
    
    

    public Cliente(String nombre, String direccion, Agenda agenda) {
		super();
		Cliente.generador++;
		this.idCliente = generador;
		this.nombre = nombre;
		Direccion = direccion;
		this.agenda = agenda;
	}

	public int getIdCliente() {
        return idCliente;
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
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", Direccion=" + Direccion + ", agenda="
				+ agenda + "]";
	}
    
    
}
