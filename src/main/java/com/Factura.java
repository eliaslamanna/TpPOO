package com;

public class Factura {
    private int numeroFactura;
    private float costo;
    private float precioFinal;
    private boolean yaSeImprimio = false;

    public Factura(int numeroFactura, float costo, float precioFinal) {
        this.numeroFactura = numeroFactura;
        this.costo = costo;
        this.precioFinal = precioFinal;
    }

    public int getNumeroFactura() {
        return numeroFactura;
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

    public boolean yaSeImprimio() {
        return yaSeImprimio;
    }

    public void setYaSeImprimio(boolean yaSeImprimio) {
        this.yaSeImprimio = yaSeImprimio;
    }

    @Override
    public String toString() {
        return "Factura: \n" +
                "    - numero Factura = " + numeroFactura + '\n' +
                "    - costo = " + costo + '\n' +
                "    - precio Final = " + precioFinal + '\n';
    }
}
