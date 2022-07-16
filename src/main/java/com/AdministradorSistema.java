package com;

import com.exception.RolNoExisteException;

import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class AdministradorSistema extends Rol {

    private Scanner read = new Scanner(System.in);

    public AdministradorSistema() {
        this.rol = "AdministradorSist";
    }

    public void guardarCliente() {
        System.out.println("Ingrese el DNI del cliente: ");
        String dniCliente = read.nextLine();

        if(Empresa.getInstancia().getClientes().containsKey(dniCliente)) {
            System.out.println("\nEl cliente ya existe: ");
        } else {
            System.out.println("\nIngrese el nombre del cliente: ");
            String nombreCliente = read.nextLine();
            System.out.println("\nIngrese el apellido del cliente: ");
            String apellidoCliente = read.nextLine();
            System.out.println("\nIngrese la direccion del cliente: \n");
            String direccionCliente = read.nextLine();

            Empresa.getInstancia().agregarCliente(dniCliente,nombreCliente,apellidoCliente,direccionCliente);
            System.out.println("Se guardo al cliente con dni: " + dniCliente + " existosamente.\n");
        }
    }

    public void guardarUsuario() throws RolNoExisteException {
        System.out.println("Ingrese el usuario nuevo: ");
        String usuario = read.nextLine();

        if(Empresa.getInstancia().getUsuarios().containsKey(usuario)) {
            System.out.println("El usuario ya existe en la base de datos.");
        } else {
            System.out.println("\nIngrese la contraseña:");
            String password = read.nextLine();
            System.out.println("\nIngrese el Rol del usuario: ");
            String rol = read.nextLine();

            Usuario nuevoUsuario = UsuarioFactory.getInstancia().crearUsuario(rol,usuario,password);
            Empresa.getInstancia().singUp(nuevoUsuario);

            if("Tecnico".equals(rol)) {
                Empresa.getInstancia().agregarTecnico(nuevoUsuario, ((Tecnico) nuevoUsuario.getRol()).getId());
            }

            System.out.println("\nEl usuario " + usuario + " se dio de alta exitosamente.");
        }
    }

    public List<Cliente> listarClientes() {
        return Empresa.getInstancia().getClientes().values().stream().collect(toList());
    }

    public List<Usuario> listarUsuarios() {
        return Empresa.getInstancia().getUsuarios().values().stream().collect(toList());
    }

    public List<Usuario> listarTecnicos() {
        return Empresa.getInstancia().getTecnicos().values().stream().collect(toList());
    }

    public List<Articulo> listarStock() {
        return Empresa.getInstancia().getStock().values().stream().collect(toList());
    }

    public void agregarArticulo() {
        Articulo articulo = cargarArticulo();

        if(Empresa.getInstancia().getStock().containsKey(articulo.getNombre())) {
            Articulo articuloExistente = Empresa.getInstancia().getStock().get(articulo.getNombre());
            articuloExistente.setCantidad(articuloExistente.getCantidad() + articulo.getCantidad());
            Empresa.getInstancia().getStock().put(articulo.getNombre(),articuloExistente);
        }

        Empresa.getInstancia().getStock().put(articulo.getNombre(), articulo);
    }

    public Articulo cargarArticulo(){
        System.out.println("Ingrese el nombre del nuevo articulo: ");
        String nombre = read.next();
        read.nextLine();

        System.out.println("Ingrese el stock inicial: ");
        float stock = read.nextFloat();
        read.nextLine();

        System.out.println("Ingrese la cantidad: ");
        float cantidad = read.nextFloat();
        read.nextLine();

        System.out.println("Ingrese el precio por unidad: ");
        float precio = read.nextFloat();
        read.nextLine();

        return new Articulo(nombre, stock, cantidad, precio);
    }

    public void eliminarArticulo() {
        System.out.println("Ingrese el nombre del articulo a eliminar: ");
        String nombreArticulo = read.next();
        read.nextLine();

        Empresa.getInstancia().getStock().remove(nombreArticulo);
    }

    public void actualizarStock(Articulo articulo, float cantidad) {
        articulo.setStock(articulo.getStock() - cantidad);
    }

    public void agregarStock(String nombreArticulo, float cantidad) {
        Articulo articulo = Empresa.getInstancia().getStock().get(nombreArticulo);

        if(articulo == null) {
            System.out.println("Ingrese el precio del nuevo articulo: ");
            float precio = read.nextFloat();
            read.nextLine();

            Empresa.getInstancia().getStock().put(nombreArticulo, new Articulo(nombreArticulo, cantidad, precio));
        } else {
            articulo.setStock(articulo.getStock() + cantidad);
        }
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

    public void configurarCostoHoras() {
        System.out.println("Ingrese el nuevo costo para las horas de tecnicos JR: ");
        float costoJr = read.nextFloat();
        read.nextLine();
        Seniority.JR(costoJr);

        System.out.println("Ingrese el nuevo costo para las horas de tecnicos SSR: ");
        float costoSsr = read.nextFloat();
        read.nextLine();
        Seniority.SSR(costoSsr);

        System.out.println("Ingrese el nuevo costo para las horas de tecnicos SR: ");
        float costoSr = read.nextFloat();
        read.nextLine();
        Seniority.SR(costoSr);
    }


    @Override
    public Integer mostrarMenu() {

        System.out.println("\n---------------------------------------------");
        System.out.println("*****\t\tAdministrador de Sistema\t\t*****\n\n");
        System.out.println("1) Agregar articulo a stock");
        System.out.println("2) Agregar stock de articulo");
        System.out.println("3) Eliminar stock de articulo");
        System.out.println("4) Salir");

        int opcion = read.nextInt();
        read.nextLine();

        return opcion;
    }
}
