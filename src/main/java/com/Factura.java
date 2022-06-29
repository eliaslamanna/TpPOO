package com;

public class Factura {
    private int numeroFactura;
    private float costo;
    private float precioFinal;

    public Factura(int numeroFactura, float costo, float precioFinal) {
        this.numeroFactura = numeroFactura;
        this.costo = costo;
        this.precioFinal = precioFinal;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }
}
