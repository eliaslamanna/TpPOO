package com;

import java.util.List;

public class Instalacion extends Visita {

    public Instalacion(Cliente cliente, List<Tecnico> tecnicos, List<Articulo> otrosCostos, List<Articulo> gastosAdicionales) {
        super(cliente, tecnicos, otrosCostos, gastosAdicionales);
		/*
		 * this.getMateriales().add(new Articulo("Cable Coaxil", 4.5F));
		 * this.getMateriales().add(new Articulo("Decodificador de TV", 1F));
		 */
		/*
		 * this.getMateriales().add(new Articulo("Modem de Internet", 1F));
		 * this.getMateriales().add(new Articulo("Divisor Coaxial", 1F));
		 * this.getMateriales().add(new Articulo("Conectores RG6", 6F));
		 */

        setTiempoTrabajado(60);
    }

}
