package com;

import java.util.HashMap;
import java.util.List;

public class Empresa {

    private static Empresa instancia;

    //Mapa de stock de articulos de la empresa (clave nombre de articulo, valor instancia de Articulo, con contidad y sus atributos)
    private HashMap<String,Articulo> stock;

    //Mapa de los empleados de la empresa (clave legajo de empleado, valor instancia de Empleado)
    private HashMap<Integer, Usuario> usuarios;

    //Mapa de clientes
    private HashMap<Integer, Cliente> clientes;

    //Mapa tecnicos
    private List<Tecnico> tecnicos;

    private Empresa() {
        stock.put("Cable Coaxil", new Articulo("Cable Coaxil", 100F, 50F));
        stock.put("Decodificador de TV", new Articulo("Decodificador de TV", 15F, 100.3F));
        stock.put("Modem de Internet", new Articulo("Modem de Internet", 20F, 120.5F));
        stock.put("Divisor Coaxial", new Articulo("Divisor Coaxial", 12F, 120F));
        stock.put("Conectores RG6", new Articulo("Conectores RG6", 25F, 100F));
        stock.put("Repuestos", new Articulo("Repuestos", 100F, 15F));

    }

    public static Empresa getInstancia() {
        if(instancia == null) {
            instancia = new Empresa();
        }

        return instancia;
    }

    public HashMap<String, Articulo> getStock() {
        return stock;
    }

    public HashMap<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    public HashMap<Integer, Cliente> getClientes() {
        return clientes;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    private Integer proxLegajoEmpleado = 1;
    private Integer proxCliente = 1;
    private Integer proxIdVisita = 1;
    private Integer proxNroFactura = 1;
}
