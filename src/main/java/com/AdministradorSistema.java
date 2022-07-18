package com;

import com.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdministradorSistema extends Rol {

    private final Scanner read = new Scanner(System.in);

    public AdministradorSistema() {
        this.rol = "AdministradorSist";
    }

    public void guardarUsuario(String usuario, String password, String rol, String seniority, String turno) throws RolNoExisteException, UsuarioYaExisteException, SeniorityNoExisteException, TurnoNoExisteException {
        if(Empresa.getInstancia().getUsuarios().containsKey(usuario)) {
            throw new UsuarioYaExisteException(usuario);
        } else {
            Usuario nuevoUsuario = UsuarioFactory.getInstancia().crearUsuario(rol,usuario,password, seniority, turno);
            Empresa.getInstancia().guardarUsuario(nuevoUsuario);

            if("Tecnico".equals(rol)) {
                Empresa.getInstancia().agregarTecnico(nuevoUsuario, ((Tecnico) nuevoUsuario.getRol()).getId());
            }

            System.out.println("\nEl usuario " + usuario + " se dio de alta exitosamente.");
        }
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(Empresa.getInstancia().getClientes().values());
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(Empresa.getInstancia().getUsuarios().values());
    }

    public List<Usuario> listarTecnicos() {
        return new ArrayList<>(Empresa.getInstancia().getTecnicos().values());
    }

    public List<Articulo> listarStock() {
        return new ArrayList<>(Empresa.getInstancia().getStock().values());
    }

    public void crearArticulo(String nombre, float cantidad, float precio) throws PrecioNegativoException, CantidadNegativaException, ArticuloYaExisteException {
        if(cantidad < 0) {
            throw new CantidadNegativaException();
        }
        else if(precio < 0) {
            throw new PrecioNegativoException();
        }
        else if(Empresa.getInstancia().getStock().containsKey(nombre)) {
            throw new ArticuloYaExisteException(nombre);
        }
        Empresa.getInstancia().getStock().put(nombre, new Articulo(nombre, cantidad, precio));
    }

    public void eliminarArticulo() {
        System.out.println("Ingrese el nombre del articulo a eliminar: ");
        String nombreArticulo = read.nextLine();

        if(!Empresa.getInstancia().getStock().containsKey(nombreArticulo)) {
            System.out.println("\nEl articulo " + nombreArticulo + " no existe en el stock.");
        } else {
            Empresa.getInstancia().getStock().remove(nombreArticulo);
            System.out.println("Se removio correctamente el articulo: " + nombreArticulo);
        }
    }

    public void agregarStock(String nombreArticulo, float cantidad) {
        if(!Empresa.getInstancia().getStock().containsKey(nombreArticulo)) {
            System.out.println("\nEl articulo " + nombreArticulo + " no existe en el stock.");
        } else {
            Empresa.getInstancia().getStock().get(nombreArticulo).setCantidad(Empresa.getInstancia().getStock().get(nombreArticulo).getCantidad() + cantidad);
            System.out.println("Se actualizo correctamente el stock de " + nombreArticulo + " , ahora hay un stock de " + Empresa.getInstancia().getStock().get(nombreArticulo).getCantidad());
        }
    }

    public void modificarArticulo(Articulo articulo) throws ArticuloNoExisteException, CantidadNegativaException, PrecioNegativoException {
        if(!Empresa.getInstancia().getStock().containsKey(articulo.getNombre()) || articulo.getNombre() == null || articulo.getNombre().isBlank()) {
            throw new ArticuloNoExisteException();
        }
        else if(articulo.getCantidad() != null && articulo.getCantidad() < 0) {
            throw new CantidadNegativaException();
        }
        else if(articulo.getPrecioUnidad() != null && articulo.getPrecioUnidad() < 0) {
            throw new PrecioNegativoException();
        }

        Empresa.getInstancia().getStock().put(articulo.getNombre(), articulo);
    }

    public void restarStock() {
        System.out.println("\nIngrese el articulo que desea modificar: ");
        String articulo = read.nextLine();

        if(!Empresa.getInstancia().getStock().containsKey(articulo)) {
            System.out.println("El articulo " + articulo + " no existe en el stock.");
        }

        System.out.println("\nExiste un stock de: " + Empresa.getInstancia().getStock().get(articulo).getCantidad());

        System.out.println("\nIngrese la cantidad que desea restar: ");
        float cantidad = read.nextFloat();
        read.nextLine();

        if(Empresa.getInstancia().getStock().get(articulo).getCantidad() - cantidad < 0) {
            System.out.println("\nNo se puede restar la cantidad deseada de stock");
        } else {
            Empresa.getInstancia().getStock().get(articulo).setCantidad(Empresa.getInstancia().getStock().get(articulo).getCantidad() - cantidad);

            System.out.println("\nSe actualizo correctamente el stock de: " + articulo);
            System.out.println("Hay un stock de: " + Empresa.getInstancia().getStock().get(articulo).getCantidad());
        }
    }

    public void configurarCostoHora(Seniority seniority, Float costo) {
        Empresa.getInstancia().setCostoHora(seniority, costo);
        System.out.println("Se actualizo el valor de la hora de trabajo del Seniority " + seniority + " a: " + costo + "\n");
    }

    public void cambiarSeniority(Integer idTecnico, Seniority seniority) throws TecnicoNoExisteException {
        List<Usuario> tecnicos = new ArrayList<>(Empresa.getInstancia().getTecnicos().values());

        Tecnico tec = (Tecnico) tecnicos.stream().filter(tecnico -> ((Tecnico) tecnico.getRol()).getId().intValue() == idTecnico.intValue()).findFirst().orElseThrow(() -> new TecnicoNoExisteException(idTecnico)).getRol();

        tec.setSeniority(seniority);
    }

}
