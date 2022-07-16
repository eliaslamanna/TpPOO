package com;

import com.exception.HorarioReservadoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Tecnico extends Rol {

    private Integer id;
    private Seniority seniority;
    private String turno;
    private Agenda agenda = new Agenda();

    
    public Tecnico(Seniority seniority, String turno, Agenda agenda) {
		super();
        this.id = new Random().nextInt(1000);
		this.seniority = seniority;
		this.turno = turno;
		this.agenda = agenda;
        if("Tarde".equals(turno)) {
            this.agenda.setTurno("Tarde");
        } else {
            this.agenda.setTurno("Mañana");
        }
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

    public boolean disponible(String dia, Integer horarioInicio, Integer horarioFin) {
        if((horarioInicio < 1400 && "Tarde".equals(turno)) ||
                (horarioFin > 1400 && "Mañana".equals(turno)) ||
                ("Sabado".equals(dia) && "Tarde".equals(turno))){
            System.out.println("El horario es incorrecto.");
        } else {
            Integer horario = horarioInicio;

            while(horario <= horarioFin + 30 && horario != 2000) {
                if(!agenda.getHorarios().get(dia).get(horario)) {
                    return false;
                }
                horario += 30;
            }
            return true;
        }

        return false;
    }

    public void agendarVisita(String dia, Integer horarioInicio, Integer horarioFin) throws HorarioReservadoException {
        agenda.agendarVisita(dia, horarioInicio, horarioFin);
    }

    public void ejecutarServicios() {
        List<Visita> visitasRevisadas = new ArrayList<>();

        for(Visita visita : new ArrayList<>(Empresa.getInstancia().getVisitas().values())) {
            for(Usuario tecnico : visita.getTecnicos()) {
                if(((Tecnico) tecnico.getRol()).getId() == id) {
                    revisarVisita(visita);
                    Empresa.getInstancia().getVisitas().put(visita.getIdVisita(), visita);
                }
            }
        }
    }

    public void revisarVisita(Visita visita){
        Scanner read = new Scanner(System.in);

        System.out.println("Complete los datos solicitados para la visita " + visita.getIdVisita());
        System.out.println("Tiempo trabajado: ");
        int tiempoTrabajado = read.nextInt();
        read.nextLine();
        System.out.println("---------------------------------------");
        System.out.println("Lista de materiales usados: ");
        List<Articulo> materialesUsados = cargarArticulos();

        System.out.println("---------------------------------------");
        System.out.println("Lista de otros costos: ");
        List<Articulo> otrosCostos = cargarArticulos();

        System.out.println("---------------------------------------");
        System.out.println("Lista de gastos: ");
        List<Articulo> gastos = cargarArticulos();

        visita.setEstado(EstadoVisita.EN_CURSO);
        visita.setTiempoTrabajado(tiempoTrabajado);
        visita.setMateriales(materialesUsados);
        visita.setOtrosCostos(otrosCostos);
        visita.setGastosAdicionales(gastos);
    }

    public List<Articulo> cargarArticulos() {
        Scanner read = new Scanner(System.in);
        boolean continuar = true;
        List<Articulo> articulos = new ArrayList<>();

        while(continuar) {
            System.out.print("Descripcion: ");
            String material = read.next();
            read.nextLine();
            System.out.print("Cantidad/Precio: ");
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

    public String getTurno() { return turno; }

    public Integer getId() {
        return this.id;
    }

    @Override
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
