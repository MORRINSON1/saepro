/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.implementacion;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import recursos.EncriptarPassword;
import saepro.ejb.UsuarioFacadeLocal;
import saepro.modelo.InicioSesion;
import saepro.modelo.Usuario;
import saepro.recursos.ValidarPassword;

/**
 *
 * @author r_mor_000
 */
@ManagedBean
@RequestScoped
public class ControllerUsuario {

    /**
     * Creates a new instance of ControllerUsuario
     */
    
    @EJB
    UsuarioFacadeLocal ejbUsuario;
    
    private Usuario usuario;
    private Usuario iniciarSesion;
    private InicioSesion sesion;
    private String validaPassword;
    
    @PostConstruct
    public void init() {
        this.setUsuario(new Usuario()); 
        this.setSesion(new InicioSesion());
        this.setIniciarSesion(new Usuario());
    }   

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the validaPassword
     */
    public String getValidaPassword() {
        return validaPassword;
    }

    /**
     * @param validaPassword the validaPassword to set
     */
    public void setValidaPassword(String validaPassword) {
        this.validaPassword = validaPassword;
    }
    
    
    /**
     * @return the sesion
     */
    public InicioSesion getSesion() {
        return sesion;
    }

    /**
     * @param sesion the sesion to set
     */
    public void setSesion(InicioSesion sesion) {
        this.sesion = sesion;
    }
    
    /**
     * @return the iniciarSesion
     */
    public Usuario getIniciarSesion() {
        return iniciarSesion;
    }

    /**
     * @param iniciarSesion the iniciarSesion to set
     */
    public void setIniciarSesion(Usuario iniciarSesion) {
        this.iniciarSesion = iniciarSesion;
    }
    
    public void insertarUsuario(){
        
        try{
            
            if(ValidarPassword.validarContraseña(getUsuario(), getValidaPassword())){//VALIDA CONTRASEÑAS IGUALES EN EL FORMULARIO
               this.getUsuario().setContrasena(EncriptarPassword.sha512(getUsuario().getContrasena()));//ENCRIPTAR CONTRASEÑA 
                ejbUsuario.create(getUsuario());

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO,"Información: Registro exitoso",""));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_ERROR,"Error: Las contraseñas deben ser iguales",""));
            }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
            (FacesMessage.SEVERITY_ERROR,"Error: No se pudo realizar registro",""));
        } 
    }

    public String inicioSesion(){

        setIniciarSesion(ejbUsuario.find(getSesion().getIdentificacion()));
        System.out.println("Informacion del objeto" + getIniciarSesion());
        try{
        if(getIniciarSesion() != null){
            if(!EncriptarPassword.sha512(getSesion().getContrasena()).equals(getIniciarSesion().getContrasena())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, "Error: Contraseña Incorrecta", ""));
            }else{
                return "/Templates/TemplatesPrincipal";
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "Error: usuario no encontrado", ""));
            return "welcomePrimefaces";
        }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "Error: No se puedo realizar la consulta", ""));
        }
        return null;
    } 
}
