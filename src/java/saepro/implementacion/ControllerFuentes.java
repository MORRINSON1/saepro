/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.implementacion;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import saepro.ejb.EstadoFacadeLocal;
import saepro.ejb.FuenteFacadeLocal;
import saepro.modelo.Estado;
import saepro.modelo.Fuente;

/**
 *
 * @author r_mor_000
 */
@ManagedBean
@ViewScoped
public class ControllerFuentes implements Serializable{

    @EJB
    private FuenteFacadeLocal ejbFuenteFacadeLocal;
    
    @EJB
    private EstadoFacadeLocal ejbEstadoFacadeLocal;
    
    /**
     * Creates a new instance of ControllerFuentes
     */
    
    private Fuente fuente;
    private Fuente editarFuente;
    private Estado estado;
    private List<Fuente> listaFuente;
    private List<Estado> listaEstados;
    private static final Integer ESTADO_DESACTIVADO = 2;

    
    @PostConstruct
    public void init(){
        editarFuente = new Fuente();
        fuente = new Fuente();
        estado = new Estado();
        listaFuente = new LinkedList();
        listaEstados = new LinkedList();
        listarFuentes();
        listarEstados();
    }
    
    
    public void insertarFuentes(){
        try{
            System.out.println("Insertando Datos");
            if(!fuente.getDescripcion().trim().equals("")){
            estado = new Estado(1);
            fuente.setId_estado(estado); 
            ejbFuenteFacadeLocal.create(fuente);
            listarFuentes();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_INFO, "Información", "Registro Exitoso"));
            } 
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", "No se Pudo Realizar Registro"));
        }
    }

    private void listarEstados () {
        try {
            System.out.println("Listando Estados");
            listaEstados = ejbEstadoFacadeLocal.findAll();
            setListaEstados(listaEstados);
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", "Error al Listar los Datos"));  
        }
    }
    
    private void listarFuentes(){
        try {
            System.out.println("Listando Fuentes");
            listaFuente = ejbFuenteFacadeLocal.findAll();
            setListaFuente(listaFuente);
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", "Error al Listar las Fuentes")); 
        }
    }
    
    public void cambiarEstadoFuente(Integer id){
        try{
            fuente = ejbFuenteFacadeLocal.find(id);
            if(fuente != null){
                fuente.setId_estado(new Estado(ESTADO_DESACTIVADO));
                ejbFuenteFacadeLocal.edit(fuente);
            }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", "Error al Cambiar Estado de la Fuente"));
        }
    }
    
    public void abrirDialog(Integer id){
        try {
            fuente = ejbFuenteFacadeLocal.find(id);
            if(fuente!= null){
                editarFuente = fuente;
                setEditarFuente(editarFuente);
                RequestContext.getCurrentInstance().update("frmFuentes");
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarFuente').show()");
            }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", "Error al Abrir Dialogo"));
        }
    }
    
    public void editarFuentes(){
        try {
            ejbFuenteFacadeLocal.edit(editarFuente);
            listarFuentes();
            RequestContext.getCurrentInstance().update("frmFuentes");
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", "Error al Editar Fuente"));
        }
    }
    
    /**
     * @return the fuente
     */
    public Fuente getFuente() {
        return fuente;
    }

    /**
     * @param fuente the fuente to set
     */
    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    /**
     * @return the editarFuente
     */
    public Fuente getEditarFuente() {
        return editarFuente;
    }

    /**
     * @param editarFuente the editarFuente to set
     */
    public void setEditarFuente(Fuente editarFuente) {
        this.editarFuente = editarFuente;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the listaFuente
     */
    public List<Fuente> getListaFuente() {
        return listaFuente;
    }

    /**
     * @param listaFuente the listaFuente to set
     */
    public void setListaFuente(List<Fuente> listaFuente) {
        this.listaFuente = listaFuente;
    }

    /**
     * @return the listaEstados
     */
    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
