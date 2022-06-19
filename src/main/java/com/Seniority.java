package com;

public enum Seniority {
    JR(100), SSR(200), SR(300);

    public final float costoHoraTrabajo;

    Seniority(float costoHoraTrabajo) {
        this.costoHoraTrabajo = costoHoraTrabajo;
    }

}
