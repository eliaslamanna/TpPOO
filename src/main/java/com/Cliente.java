package com;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String Direccion;
    private Agenda agenda = new Agenda();

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
}
