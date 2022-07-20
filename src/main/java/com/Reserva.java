package com;

public class Reserva {

    private Integer dia;
    private Integer mes;
    private Integer horaInicio;
    private Integer horaFin;

    public Reserva(Integer dia, Integer mes, Integer horaInicio, Integer horaFin) {
        this.dia = dia;
        this.mes = mes;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getDia() {
        return dia;
    }


    public Integer getMes() {
        return mes;
    }


    public Integer getHoraInicio() {
        return horaInicio;
    }

    public Integer getHoraFin() {
        return horaFin;
    }


    @Override
    public String toString() {
        return  " -- dia: " + dia +
                " -- mes: " + mes +
                " -- horaInicio: " + horaInicio +
                " -- horaFin: " + horaFin;
    }

}
