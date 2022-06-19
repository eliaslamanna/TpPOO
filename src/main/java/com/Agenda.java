package com;

import java.util.HashMap;

public class Agenda {

    private HashMap<String,Tecnico> horarios;

    public Agenda() {
        horarios.put("8:00",null);
        horarios.put("8:30",null);
        horarios.put("9:00",null);
        horarios.put("9:30",null);
        horarios.put("10:00",null);
        horarios.put("10:30",null);
        horarios.put("11:00",null);
        horarios.put("11:30",null);
        horarios.put("12:00",null);
        horarios.put("12:30",null);
        horarios.put("13:00",null);
        horarios.put("13:30",null);
        horarios.put("14:00",null);
        horarios.put("14:30",null);
        horarios.put("15:00",null);
        horarios.put("15:30",null);
        horarios.put("16:00",null);
        horarios.put("16:30",null);
        horarios.put("17:00",null);
        horarios.put("17:30",null);
        horarios.put("18:00",null);
        horarios.put("18:30",null);
        horarios.put("19:00",null);
        horarios.put("19:30",null);
        horarios.put("20:00",null);
    }

    public HashMap<String, Tecnico> getHorarios() {
        return horarios;
    }

    public void agendarTecnico(String horario, Tecnico tecnico) {
        this.horarios.put(horario, tecnico);
    }

}
