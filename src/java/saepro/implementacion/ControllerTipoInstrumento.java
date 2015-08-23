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
import saepro.ejb.TipoInstrumentoFacadeLocal;
import saepro.modelo.Estado;
import saepro.modelo.TipoInstrumento;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerTipoInstrumento implements Serializable{
    
    @EJB
    EstadoFacadeLocal ejbEstado;
    
    @EJB
    TipoInstrumentoFacadeLocal ejbTipoInstrumento;

    private TipoInstrumento tipoInstrumento;
    private TipoInstrumento tipoInstrumentoEdit;
    private Estado estado;
    private List<Estado> listaEstados;
    private List<TipoInstrumento> listaTipoInstrumento;
    private static final Integer ESTADO_DESACTIVADO = 2;
    
    @PostConstruct
    public void init(){
        this.setTipoInstrumento(new TipoInstrumento());
        this.setTipoInstrumentoEdit(new TipoInstrumento());
        this.setEstado(new Estado());
        this.listaTipoInstrumento = new LinkedList();
        this.listaEstados = new LinkedList ();
        cargarEstados();
        cargarTipoInstrumento();
    }
    
    private void cargarEstados () {
        try {
            setListaEstados(ejbEstado.findAll());
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", ex.getMessage()));  
        }
    }
    
    private void cargarTipoInstrumento(){
        try {
            setListaTipoInstrumento(ejbTipoInstrumento.findAll());
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", ex.getMessage()));  
        }
    }
    
    public void guardarTipoInstrumento(){
        try {
            if(!tipoInstrumento.getDescripcion().trim().equals("")){
            tipoInstrumento.setEstado(new Estado(1));
            ejbTipoInstrumento.create(tipoInstrumento);
            cargarTipoInstrumento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_INFO, "Información", "Registro Exitoso"));
            }
            
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", ex.getMessage()));
        }
    }
    
    public void cambiarEstadoTipoInstrumento(Integer id){
        try{
            setTipoInstrumento(ejbTipoInstrumento.find(id));
            if(getTipoInstrumento() != null){
                getTipoInstrumento().setEstado(new Estado(ESTADO_DESACTIVADO));
                ejbTipoInstrumento.edit(getTipoInstrumento());
            }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", ex.getMessage()));
        }
    }
    
    public void abrirDialog(Integer id){
        try {
            setTipoInstrumento(ejbTipoInstrumento.find(id));
            if(getTipoInstrumento() != null){
                setTipoInstrumentoEdit(getTipoInstrumento());
                RequestContext.getCurrentInstance().update("frmInstrumentos");
                RequestContext.getCurrentInstance().execute("PF('dialogoEditar').show()");
            }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", ex.getMessage()));
        }
    }
    
    public void editarTipoInstrumento(){
        try {
            ejbTipoInstrumento.edit(getTipoInstrumentoEdit());
            cargarTipoInstrumento();
            RequestContext.getCurrentInstance().update("frmInstrumentos");
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "error", ex.getMessage()));
        }
    }

    /**
     * @return the tipoInstrumento
     */
    public TipoInstrumento getTipoInstrumento() {
        return tipoInstrumento;
    }

    /**
     * @param tipoInstrumento the tipoInstrumento to set
     */
    public void setTipoInstrumento(TipoInstrumento tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
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

    /**
     * @return the listaTipoInstrumento
     */
    public List<TipoInstrumento> getListaTipoInstrumento() {
        return listaTipoInstrumento;
    }

    /**
     * @param listaTipoInstrumento the listaTipoInstrumento to set
     */
    public void setListaTipoInstrumento(List<TipoInstrumento> listaTipoInstrumento) {
        this.listaTipoInstrumento = listaTipoInstrumento;
    }

    /**
     * @return the tipoInstrumentoEdit
     */
    public TipoInstrumento getTipoInstrumentoEdit() {
        return tipoInstrumentoEdit;
    }

    /**
     * @param tipoInstrumentoEdit the tipoInstrumentoEdit to set
     */
    public void setTipoInstrumentoEdit(TipoInstrumento tipoInstrumentoEdit) {
        this.tipoInstrumentoEdit = tipoInstrumentoEdit;
    }
    
}
