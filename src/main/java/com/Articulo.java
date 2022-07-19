package com;

public class Articulo {
    private String nombre;
    private Float cantidad;
    private Float precioUnidad;

    public Articulo(String nombre, Float cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Articulo(String nombre, Float cantidad, Float precioUnidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
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

    public Float getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    @Override
    public String toString() {
        return " Nombre: " + nombre +
                " -- Cantidad: " + cantidad +
                " -- PrecioUnidad: $" + precioUnidad;
    }
}
