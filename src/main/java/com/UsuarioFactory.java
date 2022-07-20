package com;

import com.exception.RolNoExisteException;
import com.exception.SeniorityNoExisteException;
import com.exception.TurnoNoExisteException;

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

    public Usuario crearUsuario(String rol, String usuario, String password, String seniority, String turno) throws RolNoExisteException, SeniorityNoExisteException, TurnoNoExisteException {
        switch (rol) {
            case "Administrativo" :
                return new Usuario(new Administrativo(),usuario,password);
            case "AdministradorSist" :
                return new Usuario(new AdministradorSistema(),usuario,password);
            case "Call Center" :
                return new Usuario(new CallCenter(),usuario,password);
            case "Tecnico" :
                if("tarde".equalsIgnoreCase(turno) && "mañana".equalsIgnoreCase(turno)) {
                    throw new TurnoNoExisteException();
                }
                try{
                    Seniority seniorityTecnico = Seniority.valueOf(seniority);
                    return new Usuario(new Tecnico(seniorityTecnico,turno),usuario,password);
                } catch (Exception e) {
                    throw new SeniorityNoExisteException();
                }
            default:
                throw new RolNoExisteException(rol);
        }
    }
}
