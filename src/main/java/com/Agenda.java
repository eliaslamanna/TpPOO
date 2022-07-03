package com;

import com.exception.HorarioReservadoException;

import java.util.HashMap;
import java.util.Map;

public class Agenda {

    private HashMap<String,HashMap<Integer,Boolean>> horarios = new HashMap<>();

    public Agenda() {
    	
    	horarios = new HashMap<String, HashMap<Integer,Boolean>>();
        HashMap<Integer,Boolean> horasLunes = new HashMap<>();
        horasLunes.put(800,false);
        horasLunes.put(830,false);
        horasLunes.put(900,false);
        horasLunes.put(930,false);
        horasLunes.put(1000,false);
        horasLunes.put(1030,false);
        horasLunes.put(1100,false);
        horasLunes.put(1130,false);
        horasLunes.put(1200,false);
        horasLunes.put(1230,false);
        horasLunes.put(1300,false);
        horasLunes.put(1330,false);
        horasLunes.put(1400,false);
        horasLunes.put(1430,false);
        horasLunes.put(1500,false);
        horasLunes.put(1530,false);
        horasLunes.put(1600,false);
        horasLunes.put(1630,false);
        horasLunes.put(1700,false);
        horasLunes.put(1730,false);
        horasLunes.put(1800,false);
        horasLunes.put(1830,false);
        horasLunes.put(1900,false);
        horasLunes.put(1930,false);
        horasLunes.put(2000,false);

        HashMap<Integer,Boolean> horasMartes = new HashMap<>();
        horasMartes.put(800,false);
        horasMartes.put(830,false);
        horasMartes.put(900,false);
        horasMartes.put(930,false);
        horasMartes.put(1000,false);
        horasMartes.put(1030,false);
        horasMartes.put(1100,false);
        horasMartes.put(1130,false);
        horasMartes.put(1200,false);
        horasMartes.put(1230,false);
        horasMartes.put(1300,false);
        horasMartes.put(1330,false);
        horasMartes.put(1400,false);
        horasMartes.put(1430,false);
        horasMartes.put(1500,false);
        horasMartes.put(1530,false);
        horasMartes.put(1600,false);
        horasMartes.put(1630,false);
        horasMartes.put(1700,false);
        horasMartes.put(1730,false);
        horasMartes.put(1800,false);
        horasMartes.put(1830,false);
        horasMartes.put(1900,false);
        horasMartes.put(1930,false);
        horasMartes.put(2000,false);

        HashMap<Integer,Boolean> horasMiercoles = new HashMap<>();
        horasMiercoles.put(800,false);
        horasMiercoles.put(830,false);
        horasMiercoles.put(900,false);
        horasMiercoles.put(930,false);
        horasMiercoles.put(1000,false);
        horasMiercoles.put(1030,false);
        horasMiercoles.put(1100,false);
        horasMiercoles.put(1130,false);
        horasMiercoles.put(1200,false);
        horasMiercoles.put(1230,false);
        horasMiercoles.put(1300,false);
        horasMiercoles.put(1330,false);
        horasMiercoles.put(1400,false);
        horasMiercoles.put(1430,false);
        horasMiercoles.put(1500,false);
        horasMiercoles.put(1530,false);
        horasMiercoles.put(1600,false);
        horasMiercoles.put(1630,false);
        horasMiercoles.put(1700,false);
        horasMiercoles.put(1730,false);
        horasMiercoles.put(1800,false);
        horasMiercoles.put(1830,false);
        horasMiercoles.put(1900,false);
        horasMiercoles.put(1930,false);
        horasMiercoles.put(2000,false);

        HashMap<Integer,Boolean> horasJueves = new HashMap<>();
        horasJueves.put(800,false);
        horasJueves.put(830,false);
        horasJueves.put(900,false);
        horasJueves.put(930,false);
        horasJueves.put(1000,false);
        horasJueves.put(1030,false);
        horasJueves.put(1100,false);
        horasJueves.put(1130,false);
        horasJueves.put(1200,false);
        horasJueves.put(1230,false);
        horasJueves.put(1300,false);
        horasJueves.put(1330,false);
        horasJueves.put(1400,false);
        horasJueves.put(1430,false);
        horasJueves.put(1500,false);
        horasJueves.put(1530,false);
        horasJueves.put(1600,false);
        horasJueves.put(1630,false);
        horasJueves.put(1700,false);
        horasJueves.put(1730,false);
        horasJueves.put(1800,false);
        horasJueves.put(1830,false);
        horasJueves.put(1900,false);
        horasJueves.put(1930,false);
        horasJueves.put(2000,false);

        HashMap<Integer,Boolean> horasViernes = new HashMap<>();
        horasViernes.put(800,false);
        horasViernes.put(830,false);
        horasViernes.put(900,false);
        horasViernes.put(930,false);
        horasViernes.put(1000,false);
        horasViernes.put(1030,false);
        horasViernes.put(1100,false);
        horasViernes.put(1130,false);
        horasViernes.put(1200,false);
        horasViernes.put(1230,false);
        horasViernes.put(1300,false);
        horasViernes.put(1330,false);
        horasViernes.put(1400,false);
        horasViernes.put(1430,false);
        horasViernes.put(1500,false);
        horasViernes.put(1530,false);
        horasViernes.put(1600,false);
        horasViernes.put(1630,false);
        horasViernes.put(1700,false);
        horasViernes.put(1730,false);
        horasViernes.put(1800,false);
        horasViernes.put(1830,false);
        horasViernes.put(1900,false);
        horasViernes.put(1930,false);
        horasViernes.put(2000,false);

        HashMap<Integer,Boolean> horasSabado = new HashMap<>();
        horasSabado.put(800,false);
        horasSabado.put(830,false);
        horasSabado.put(900,false);
        horasSabado.put(930,false);
        horasSabado.put(1000,false);
        horasSabado.put(1030,false);
        horasSabado.put(1100,false);
        horasSabado.put(1130,false);
        horasSabado.put(1200,false);
        horasSabado.put(1230,false);
        horasSabado.put(1300,false);
        horasSabado.put(1330,false);
        horasSabado.put(1400,false);

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

    public void agendarVisita(Integer horario, String dia) throws HorarioReservadoException {
        if(horarios.get(dia).get(horario) == null) {
            this.horarios.get(dia).put(horario, true);
        }
        else {
            throw new HorarioReservadoException(dia, horario);
        }
    }

    public void setTurno(String turno) {
        for (Map.Entry<String, HashMap<Integer,Boolean>> dia : horarios.entrySet()) {
            for (Map.Entry<Integer, Boolean> horario : dia.getValue().entrySet()) {
                if (turno.equals("Tarde")) {
                    while(horario.getKey() >= 1430 && horario.getKey() <= 2000 ) {
                        horario.setValue(true);
                    }
                } else {
                    while(horario.getKey() >= 800 && horario.getKey() <= 1400 ) {
                        horario.setValue(true);
                    }
                }

            }
        }
    }
}
