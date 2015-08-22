/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.recursos;

import saepro.modelo.Usuario;

/**
 *
 * @author r_mor_000
 */
public class ValidarPassword {
    
    public static boolean validarContraseña(Usuario usuario, String validaPass){
        
        System.out.println("Validando Contraseña...");
        
        return usuario.getContrasena().equals(validaPass);
          
    }
    
}
