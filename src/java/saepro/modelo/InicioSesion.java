/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.modelo;

import recursos.EncriptarPassword;

/**
 *
 * @author r_mor_000
 */
public class InicioSesion {
    
    private String identificacion;
    private String contrasena;

    public InicioSesion() {
    }

    public InicioSesion(String identificacion, String contrasena) {
        this.identificacion = identificacion;
        this.contrasena = contrasena;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
