package com;

import com.exception.HorarioParaTurnoIncorrectoException;
import com.exception.HorarioReservadoException;
import com.exception.StockInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tecnico extends Rol {

    private Integer id;
    private Seniority seniority;
    private String turno;
    private List<Reserva> agenda = new ArrayList<>();

    public Tecnico(Seniority seniority, String turno, List<Reserva> agenda) {
		super();
        this.id = new Random().nextInt(1000);
		this.seniority = seniority;
		this.turno = turno;
		this.agenda = agenda;
        this.rol = "Tecnico";
	}

    public Tecnico(Seniority seniority, String turno) {
        super();
        this.id = new Random().nextInt(1000);
        this.seniority = seniority;
        this.turno = turno;
        this.rol = "Tecnico";
    }

	public Tecnico() {
		/* agenda.setTurno(this.turno); */
        this.id = new Random().nextInt(1000);
        this.rol = "Tecnico";
    }

    public boolean disponible(Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin) throws HorarioParaTurnoIncorrectoException, HorarioReservadoException {
        if((horarioInicio < 1400 && "Tarde".equalsIgnoreCase(turno)) ||
                (horarioFin > 1400 && "Mañana".equalsIgnoreCase(turno))){
            throw new HorarioParaTurnoIncorrectoException();
        } else {
            for(Reserva reserva : agenda) {
                if(dia.equals(reserva.getDia()) && mes.equals(reserva.getMes())) {
                    if(horarioInicio.intValue() >= reserva.getHoraInicio() && horarioFin <= reserva.getHoraFin() + 30) {
                        throw new HorarioReservadoException();
                    }
                }
            }
        }

        return true;
    }

    public void agendarVisita(Integer dia, Integer mes, Integer horarioInicio, Integer horarioFin) throws HorarioReservadoException {
        agenda.add(new Reserva(dia,mes,horarioInicio,horarioFin));
    }

    public void revisarVisita(String idVisita, String tiempoTrabajado, List<Articulo> gastosAdicionales, List<Articulo> costosAdicionales) throws StockInsuficienteException {
        Visita visita = Empresa.getInstancia().getVisitas().get(Integer.valueOf(idVisita));

        visita.setEstado(EstadoVisita.EN_CURSO);
        visita.setTiempoTrabajado(Integer.valueOf(tiempoTrabajado));
        visita.setOtrosCostos(costosAdicionales);
        visita.setGastosAdicionales(gastosAdicionales);

        List<Articulo> materialesUsados = visita.getMateriales();

        for(Articulo articulo : materialesUsados) {
            Empresa.getInstancia().getStock().get(articulo.getNombre()).setCantidad(Empresa.getInstancia().getStock().get(articulo.getNombre()).getCantidad() - articulo.getCantidad());
            if(Empresa.getInstancia().getStock().get(articulo.getNombre()).getCantidad() < 0) {
                throw new StockInsuficienteException();
            }
        }

    }

    public Seniority getSeniority() { return seniority; }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public String getTurno() { return turno; }

    public Integer getId() {
        return this.id;
    }

    public List<Reserva> getAgenda() {
        return agenda;
    }

    @Override
    public String toString() {
        return " id: " + id +
                " -- seniority: " + seniority +
                " -- turno: " + turno;
    }

}
