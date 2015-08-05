/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import saepro.ejb.FuenteFacadeLocal;
import saepro.ejb.IndicadorFacadeLocal;
import saepro.modelo.Estado;
import saepro.modelo.Fuente;
import saepro.modelo.Indicador;
import saepro.recursos.Recurso;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerIndicador implements Serializable {
    
    @EJB
    IndicadorFacadeLocal indicadorEjb;
    
    @EJB
    FuenteFacadeLocal fuenteEjb;
    
    private List<Fuente> listaFuentes;
    private Indicador indicador;
    private Indicador indicadorEdit;
    private Fuente fuente;
    private List<Indicador> listaIndicadores;
    
    @PostConstruct
    public void init() {
        this.indicador = new Indicador();
        this.indicadorEdit = new Indicador();
        this.fuente = new Fuente();
        this.listaFuentes = new ArrayList<>();
        this.listaIndicadores = new ArrayList<>();
        cargarFuentes();
        cargarIndicadores();
    }
    
    private void cargarFuentes() {
        try {
            listaFuentes = fuenteEjb.findAll();
        } catch (Exception ex) {
            
        }
    }
    
    private void cargarIndicadores() {
        listaIndicadores = indicadorEjb.getAllIndicadoresActivos();
    }
    
    public void registrarIndicador() {
        
        try {
            if (Recurso.validarCodigoDescripcionIndicador(listaIndicadores, indicador.getCodigo())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Indicador Ya Existe"));
                
            } else {
                Estado estado = new Estado(1);
                Fuente objFuente = null;
                
                objFuente = fuenteEjb.getFuenteByCodigo(fuente.getCodigo());
                
                if (objFuente != null) {
                    
                    indicador.setIdEstado(estado);
                    indicador.setIdResponsable(objFuente);
                    indicadorEjb.create(indicador);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Indicador Registrado"));
                    cargarIndicadores();
                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Indicador No pudo ser Registrado"));
        }
    }
    
    public void abrirDialog(Indicador indicadoor) {
        
        indicadorEdit = indicadoor;
        RequestContext.getCurrentInstance().update("frmFactores");
        RequestContext.getCurrentInstance().execute("PF('dialogoEditarIndicador').show()");
    }
    
    public void desactivarIndicador(Indicador indicador) {
        
        try {
            
            indicador.setIdEstado(new Estado(2));
            indicadorEjb.edit(indicador);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Indicador Desactivado"));
            cargarIndicadores();
            RequestContext.getCurrentInstance().update("frmFactores:growl");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", " Indicador No pudo ser Desactivado " + ex.getMessage()));
        }
    }
    
    public void editarIndicador() {
        
        try {
            
            if (Recurso.validarCodigoDescripcionIndicador(listaIndicadores, indicadorEdit)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Indicador Ya Existe"));
            } else {
                
                
                if (null != fuente.getId()) {
                    indicadorEdit.setIdResponsable(fuenteEjb.find(fuente.getId()));
                }
                indicadorEjb.edit(indicadorEdit);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Indicador Editado"));
                cargarIndicadores();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarIndicador').hide()");
                RequestContext.getCurrentInstance().update("frmFactores:tableIndicadores");
            }
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", " Indicador No pudo ser Modificado " + ex.getMessage()));            
        }
    }
    
    public void cancelar() {
        indicadorEdit = new Indicador();
        RequestContext.getCurrentInstance().update("frmFactores:dialogoEditarIndicador");
    }

    /**
     * @return the listaFuentes
     */
    public List<Fuente> getListaFuentes() {
        return listaFuentes;
    }

    /**
     * @param listaFuentes the listaFuentes to set
     */
    public void setListaFuentes(List<Fuente> listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    /**
     * @return the indicador
     */
    public Indicador getIndicador() {
        return indicador;
    }

    /**
     * @param indicador the indicador to set
     */
    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }
    
    public Fuente getFuente() {
        return fuente;
    }
    
    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    /**
     * @return the listaIndicadores
     */
    public List<Indicador> getListaIndicadores() {
        return listaIndicadores;
    }

    /**
     * @param listaIndicadores the listaIndicadores to set
     */
    public void setListaIndicadores(List<Indicador> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    /**
     * @return the indicadorEdit
     */
    public Indicador getIndicadorEdit() {
        return indicadorEdit;
    }

    /**
     * @param indicadorEdit the indicadorEdit to set
     */
    public void setIndicadorEdit(Indicador indicadorEdit) {
        this.indicadorEdit = indicadorEdit;
    }
    
}
