package com;

import java.util.HashMap;

public class Empresa {
    //Mapa de stock de articulos de la empresa (clave nombre de articulo, valor instancia de Articulo, con contidad y sus atributos)
    public static HashMap<String,Articulo> stock;

    //Agenda con los horarios de los tecnicos, los clientes les dicen sus horarios disponibles y se fija aca si se puede
    public static Agenda agenda;

    //Mapa de los empleados de la empresa (clave legajo de empleado, valor instancia de Empleado)
    private HashMap<Integer,Empleado> empleado;

    private Integer proxLegajoEmpleado = 1;
    private Integer proxCliente = 1;
    private Integer proxIdVisita = 1;
    private Integer proxNroFactura = 1;
}
