package com;

public class Articulo {
    private String nombre;
    private Integer cantida;
    private Float precio;

    public String getNombre() {
        return nombre;
    }

    public Integer getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
