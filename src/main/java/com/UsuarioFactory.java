package com;

import com.exception.RolNoExisteException;

import java.util.Scanner;

public class UsuarioFactory {

    private static UsuarioFactory instancia;

    private UsuarioFactory(){
    }

    public static UsuarioFactory getInstancia() {
        if(instancia == null) {
            instancia = new UsuarioFactory();
        }

        return instancia;
    }

    public Usuario crearUsuario(String rol, String usuario, String password) throws RolNoExisteException {
        switch (rol) {
            case "Administrativo" :
                return new Usuario(new Administrativo(),usuario,password);
            case "AdministradorSist" :
                return new Usuario(new AdministradorSist(),usuario,password);
            case "Call Center" :
                return new Usuario(new Callcenter(),usuario,password);
            case "Tecnico" :
                Scanner scanner = new Scanner(System.in);

                System.out.println("Ingrese su seniority");
                String seniority = scanner.nextLine();
                Seniority seniorityTecnico = Seniority.JR;

                if(seniority.equals("SSR")) {
                    seniorityTecnico = Seniority.SSR;
                }
                else if(seniority.equals("SR")) {
                    seniorityTecnico = Seniority.SR;
                }

                System.out.println("Ingrese su turno");
                String turno = scanner.nextLine();

                return new Usuario(new Tecnico(seniorityTecnico,turno),usuario,password);
            default:
                throw new RolNoExisteException(rol);
        }
    }
}
