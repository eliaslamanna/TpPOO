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

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return  " -- dia: " + dia +
                " -- mes: " + mes +
                " -- horaInicio: " + horaInicio +
                " -- horaFin: " + horaFin;
    }

}
