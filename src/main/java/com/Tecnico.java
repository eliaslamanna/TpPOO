package com;

import com.exception.HorarioReservadoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tecnico extends Rol {

    private Seniority seniority;
    private String turno;
    private Agenda agenda = new Agenda();
    private List<Visita> visitas = new ArrayList<>();

    
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

    public void ejecutarServicios() {
        if (this.visitas.size() == 0) {
            System.out.println("No hay visitas registradas");
        }
        this.visitas.forEach(visita -> revisarVisita(visita));
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

    public void listarServicios() {
        this.visitas.forEach(visita -> visita.obtenerDatosVisita());
    }

    public List<Visita> getVisitas() { return this.visitas; }

    public Seniority getSeniority() { return seniority; }

    public String getTurno() { return turno; }

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
