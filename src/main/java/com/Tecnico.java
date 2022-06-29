package com;

import com.exception.HorarioReservadoException;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Rol {

    private Seniority seniority;
    private String turno;
    private Agenda agenda = new Agenda();
    private List<Visita> visitas = new ArrayList<>();

    public Tecnico() {
        agenda.setTurno(this.turno);
    }

    public List<Visita> listarServicios() {
        return this.visitas;
    }

    public boolean disponible(String dia, Integer horario) {
        return agenda.getHorarios().get(dia).get(horario) == null;
    }

    public void agendarVisita(String dia, Integer horario) throws HorarioReservadoException {
        agenda.agendarVisita(horario, dia);
    }

    public void ejecutarServicios(int tiempoTrabajado, List<Articulo> materialesUsados, List<Articulo> otrosCostos, List<Articulo> gastos) {
        this.visitas.forEach(visita -> {
            visita.setEstado(EstadoVisita.EN_CURSO);
            visita.setTiempoTrabajado(tiempoTrabajado);
            visita.setMateriales(materialesUsados);
            visita.setOtrosCostos(otrosCostos);
            visita.setGastosAdicionales(gastos);
        });
    }

    public List<Visita> getVisitas() { return this.visitas; }

    public Seniority getSeniority() { return seniority; }

    public String getTurno() { return turno; }

    @Override
    public void mostrarMenu() {

    }
}
