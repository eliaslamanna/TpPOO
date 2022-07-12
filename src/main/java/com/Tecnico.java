package com;

import com.exception.HorarioReservadoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tecnico extends Rol {

    private Seniority seniority;
    private String turno;
    private Agenda agenda = new Agenda();
    private List<Visita> visitas = new ArrayList<>();
    private String rol = "Tecnico";

    
    public Tecnico(Seniority seniority, String turno, Agenda agenda, List<Visita> visitas) {
		super();
		this.seniority = seniority;
		this.turno = turno;
		this.agenda = agenda;
		this.visitas = visitas;
        this.rol = "Tecnico";
	}

    public Tecnico(Seniority seniority, String turno) {
        super();
        this.seniority = seniority;
        this.turno = turno;
        this.rol = "Tecnico";
    }

	public Tecnico() {
		/* agenda.setTurno(this.turno); */
        this.rol = "Tecnico";
    }

    public boolean disponible(String dia, Integer horario) {
        return agenda.getHorarios().get(dia).get(horario);
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
    public Integer mostrarMenu() {
    	int opc = 0;
    	Scanner read = new Scanner(System.in);
		System.out.println("\n\n\n\n");
		System.out.println("---------------------------------------------");
		System.out.println("*****\t\tTecnico\t\t*****\n\n");
		System.out.println("Lista servicios:\n");
		
		
		System.out.println();
        return null;
    }
}
