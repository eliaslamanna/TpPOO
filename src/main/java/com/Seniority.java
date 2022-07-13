package com;

public enum Seniority {
    JR(100), SSR(200), SR(300);

    public static float costoHoraTrabajo;

    Seniority(float costoHoraTrabajo) {
        costoHoraTrabajo = costoHoraTrabajo;
    }

    public static void JR(float nuevoCosto) {
        costoHoraTrabajo = nuevoCosto;
    }
    public static void SSR(float nuevoCosto) {
        costoHoraTrabajo = nuevoCosto;
    }
    public static void SR(float nuevoCosto) {
        costoHoraTrabajo = nuevoCosto;
    }

}
