package com;

import com.exception.HorarioParaTurnoIncorrectoException;
import com.exception.HorarioReservadoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    /*public void ejecutarServicios() {
        List<Visita> visitasRevisadas = new ArrayList<>();

        for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
            for(Usuario tecnico : visita.getTecnicos()) {
                if(((Tecnico) tecnico.getRol()).getId() == id) {
                    revisarVisita(visita);
                    Empresa.getInstancia().getVisitas().put(visita.getIdVisita(), visita);
                }
            }
        }
    }*/

    public void revisarVisita(String idVisita, String tiempoTrabajado, List<Articulo> gastosAdicionales, List<Articulo> costosAdicionales){
        Visita visita = Empresa.getInstancia().getVisitas().get(Integer.valueOf(idVisita));

        visita.setEstado(EstadoVisita.EN_CURSO);
        visita.setTiempoTrabajado(Integer.valueOf(tiempoTrabajado));
        visita.setOtrosCostos(costosAdicionales);
        visita.setGastosAdicionales(gastosAdicionales);
    }

    public List<Articulo> cargarArticulos() {
        Scanner read = new Scanner(System.in);
        boolean continuar = true;
        List<Articulo> articulos = new ArrayList<>();

        while(continuar) {
            System.out.print("Descripcion: ");
            String material = read.next();
            read.nextLine();
            System.out.print("Precio: ");
            float cantidad = read.nextFloat();
            read.nextLine();
            articulos.add(new Articulo(material, cantidad));
            System.out.print("¿Desea agregar mas materiales? [s/n]: ");
            String opcion = read.next();
            if ("n".equalsIgnoreCase(opcion)) {
                continuar = false;
            }
        }
        return articulos;
    }

    public List<Visita> listarServicios() {
        List<Visita> visitasTecnico = new ArrayList<>();

        for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
            for(Usuario tecnico : visita.getTecnicos()) {
                if(((Tecnico) tecnico.getRol()).getId() == id) {
                    visitasTecnico.add(visita);
                }
            }
        }

        return visitasTecnico;
    }

    public Seniority getSeniority() { return seniority; }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public String getTurno() { return turno; }

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return " id: " + id +
                " -- seniority: " + seniority +
                " -- turno: " + turno;
    }

    public Integer mostrarMenu() {
    	Scanner read = new Scanner(System.in);

        System.out.println("\n---------------------------------------------");
		System.out.println("*****\t\tTecnico\t\t*****\n\n");
		System.out.println("1) Ejecutar Servicios");
        System.out.println("2) Ver Servicios Asignados");
        System.out.println("3) Salir");

        int opcion = read.nextInt();
        read.nextLine();

        return opcion;
    }
}
