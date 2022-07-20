package com;

public class Articulo {
    private String nombre;
    private Float cantidad;
    private Float precio;

    public Articulo(String nombre, Float cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Articulo(String nombre, Float cantidad, Float precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return " Nombre: " + nombre +
                " -- Cantidad: " + cantidad +
                " -- Precio: $" + precio;
    }
}
