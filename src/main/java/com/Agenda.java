package com;

import com.exception.HorarioReservadoException;

import java.util.HashMap;
import java.util.Map;

public class Agenda {

    private HashMap<String,HashMap<Integer,Boolean>> horarios = new HashMap<>();

    public Agenda() {
    	
    	horarios = new HashMap<String, HashMap<Integer,Boolean>>();
        HashMap<Integer,Boolean> horasLunes = new HashMap<>();
        horasLunes.put(800,true);
        horasLunes.put(830,true);
        horasLunes.put(900,true);
        horasLunes.put(930,true);
        horasLunes.put(1000,true);
        horasLunes.put(1030,true);
        horasLunes.put(1100,true);
        horasLunes.put(1130,true);
        horasLunes.put(1200,true);
        horasLunes.put(1230,true);
        horasLunes.put(1300,true);
        horasLunes.put(1330,true);
        horasLunes.put(1400,true);
        horasLunes.put(1430,true);
        horasLunes.put(1500,true);
        horasLunes.put(1530,true);
        horasLunes.put(1600,true);
        horasLunes.put(1630,true);
        horasLunes.put(1700,true);
        horasLunes.put(1730,true);
        horasLunes.put(1800,true);
        horasLunes.put(1830,true);
        horasLunes.put(1900,true);
        horasLunes.put(1930,true);
        horasLunes.put(2000,true);

        HashMap<Integer,Boolean> horasMartes = new HashMap<>();
        horasMartes.put(800,true);
        horasMartes.put(830,true);
        horasMartes.put(900,true);
        horasMartes.put(930,true);
        horasMartes.put(1000,true);
        horasMartes.put(1030,true);
        horasMartes.put(1100,true);
        horasMartes.put(1130,true);
        horasMartes.put(1200,true);
        horasMartes.put(1230,true);
        horasMartes.put(1300,true);
        horasMartes.put(1330,true);
        horasMartes.put(1400,true);
        horasMartes.put(1430,true);
        horasMartes.put(1500,true);
        horasMartes.put(1530,true);
        horasMartes.put(1600,true);
        horasMartes.put(1630,true);
        horasMartes.put(1700,true);
        horasMartes.put(1730,true);
        horasMartes.put(1800,true);
        horasMartes.put(1830,true);
        horasMartes.put(1900,true);
        horasMartes.put(1930,true);
        horasMartes.put(2000,true);

        HashMap<Integer,Boolean> horasMiercoles = new HashMap<>();
        horasMiercoles.put(800,true);
        horasMiercoles.put(830,true);
        horasMiercoles.put(900,true);
        horasMiercoles.put(930,true);
        horasMiercoles.put(1000,true);
        horasMiercoles.put(1030,true);
        horasMiercoles.put(1100,true);
        horasMiercoles.put(1130,true);
        horasMiercoles.put(1200,true);
        horasMiercoles.put(1230,true);
        horasMiercoles.put(1300,true);
        horasMiercoles.put(1330,true);
        horasMiercoles.put(1400,true);
        horasMiercoles.put(1430,true);
        horasMiercoles.put(1500,true);
        horasMiercoles.put(1530,true);
        horasMiercoles.put(1600,true);
        horasMiercoles.put(1630,true);
        horasMiercoles.put(1700,true);
        horasMiercoles.put(1730,true);
        horasMiercoles.put(1800,true);
        horasMiercoles.put(1830,true);
        horasMiercoles.put(1900,true);
        horasMiercoles.put(1930,true);
        horasMiercoles.put(2000,true);

        HashMap<Integer,Boolean> horasJueves = new HashMap<>();
        horasJueves.put(800,true);
        horasJueves.put(830,true);
        horasJueves.put(900,true);
        horasJueves.put(930,true);
        horasJueves.put(1000,true);
        horasJueves.put(1030,true);
        horasJueves.put(1100,true);
        horasJueves.put(1130,true);
        horasJueves.put(1200,true);
        horasJueves.put(1230,true);
        horasJueves.put(1300,true);
        horasJueves.put(1330,true);
        horasJueves.put(1400,true);
        horasJueves.put(1430,true);
        horasJueves.put(1500,true);
        horasJueves.put(1530,true);
        horasJueves.put(1600,true);
        horasJueves.put(1630,true);
        horasJueves.put(1700,true);
        horasJueves.put(1730,true);
        horasJueves.put(1800,true);
        horasJueves.put(1830,true);
        horasJueves.put(1900,true);
        horasJueves.put(1930,true);
        horasJueves.put(2000,true);

        HashMap<Integer,Boolean> horasViernes = new HashMap<>();
        horasViernes.put(800,true);
        horasViernes.put(830,true);
        horasViernes.put(900,true);
        horasViernes.put(930,true);
        horasViernes.put(1000,true);
        horasViernes.put(1030,true);
        horasViernes.put(1100,true);
        horasViernes.put(1130,true);
        horasViernes.put(1200,true);
        horasViernes.put(1230,true);
        horasViernes.put(1300,true);
        horasViernes.put(1330,true);
        horasViernes.put(1400,true);
        horasViernes.put(1430,true);
        horasViernes.put(1500,true);
        horasViernes.put(1530,true);
        horasViernes.put(1600,true);
        horasViernes.put(1630,true);
        horasViernes.put(1700,true);
        horasViernes.put(1730,true);
        horasViernes.put(1800,true);
        horasViernes.put(1830,true);
        horasViernes.put(1900,true);
        horasViernes.put(1930,true);
        horasViernes.put(2000,true);

        HashMap<Integer,Boolean> horasSabado = new HashMap<>();
        horasSabado.put(800,true);
        horasSabado.put(830,true);
        horasSabado.put(900,true);
        horasSabado.put(930,true);
        horasSabado.put(1000,true);
        horasSabado.put(1030,true);
        horasSabado.put(1100,true);
        horasSabado.put(1130,true);
        horasSabado.put(1200,true);
        horasSabado.put(1230,true);
        horasSabado.put(1300,true);
        horasSabado.put(1330,true);
        horasSabado.put(1400,true);

        horarios.put("Lunes", horasLunes);
        horarios.put("Martes", horasLunes);
        horarios.put("Miercoles", horasLunes);
        horarios.put("Jueves", horasLunes);
        horarios.put("Viernes", horasLunes);
        horarios.put("Sabado", horasLunes);

    }

    public HashMap<String, HashMap<Integer,Boolean>> getHorarios() {
        return horarios;
    }

    public void agendarVisita(String dia, Integer horarioInicio, Integer horarioFin) {
        Integer horario = horarioInicio;

        while(horario <= horarioFin + 30 && horario != 2000) {
            this.horarios.get(dia).put(horario, false);
            horario += 30;
        }
    }

    public void setTurno(String turno) {
        for (Map.Entry<String, HashMap<Integer,Boolean>> dia : horarios.entrySet()) {
            for (Map.Entry<Integer, Boolean> horario : dia.getValue().entrySet()) {
                if (turno.equalsIgnoreCase("Tarde")) {
                    if ( horario.getKey() >= 800 && horario.getKey() <= 1400) {
                        horario.setValue(false);
                    }
                } else {
                    if (horario.getKey() >= 1430 && horario.getKey() <= 2000) {
                        horario.setValue(false);
                    }
                }

            }
        }
    }

}
