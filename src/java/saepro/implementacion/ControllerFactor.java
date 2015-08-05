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
import saepro.ejb.EstadoFacadeLocal;
import saepro.ejb.FactorFacadeLocal;
import saepro.modelo.Estado;
import saepro.modelo.Factor;
import saepro.recursos.Recurso;

@ManagedBean
@ViewScoped
public class ControllerFactor implements Serializable {

    @EJB
    FactorFacadeLocal factorEjb;

    @EJB
    EstadoFacadeLocal estadoEjb;

    private Factor factor;
    private Factor factorEdit;
    private Estado estado;
    private List<Factor> listaFactor;
    private List<Estado> listaEstados;

    @PostConstruct
    public void init() {
        this.estado = new Estado();
        this.factor = new Factor();
        this.factorEdit = new Factor();
        this.listaFactor = new ArrayList();
        this.listaEstados = estadoEjb.findAll();
        listarTodo();
    }
    
    public void pruebaa() {
        System.out.println("LLego a Prueba");
    }

    public void registrarFactor() {

        try {

            if (Recurso.validarCodigoDescripcionFactor(listaFactor, factor.getCodigo(), factor.getDescripcion())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Factor Ya Existe"));

            } else {
                
               
                factor.setId_estado(estado);
                factorEjb.create(factor);
                factor = new Factor();
                estado = new Estado();
                listarTodo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Factor Registrado"));
            }
        } catch (Exception ex) {
            System.out.println("Error en registrar " + ex.getMessage());
        }
    }
    
    

    

    
    public void listarTodo() {
        setListaFactor(factorEjb.listarFactores());
    }

    
    public void abrirDialog(Integer idFactor) {
        factorEdit = null;
        factorEdit = factorEjb.find(idFactor);
        System.out.println("factor "+factorEdit.getDescripcion());
        RequestContext.getCurrentInstance().update("frmFactores");
        RequestContext.getCurrentInstance().execute("PF('dialogoEditar').show()");
      
    }
    
    public void cancelar(){
        factorEdit = new Factor();
        RequestContext.getCurrentInstance().update("frmFactores:dialogoEditar");
    }

    
    public void editarFactor() {
        try {

            if (Recurso.validarCodigoDescripcionFactor(listaFactor, factorEdit)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Factor Ya Existe"));

            } else {

                factorEjb.edit(factorEdit);

                listarTodo();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditar').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Factor Modificado"));
                RequestContext.getCurrentInstance().update("frmFactores:tableFactores");
                factorEdit = new Factor();

            }
        } catch (Exception exception) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo Modificar Factor"+exception));
        }
    }
    

    public void desactivarFactor(Factor faactorr) {
        try {

            estado = new Estado();
            estado.setIdEstado(new Integer(2));
            faactorr.setId_estado(estado);
            factorEjb.edit(faactorr);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Factor Desactivado"));
            listarTodo();
        } catch (Exception exception) {
            //mostar el error
        }
    }
   
    
    
    /**
     * @return the factor
     */
    public Factor getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    /**
     * @return the listaFactor
     */
    public List<Factor> getListaFactor() {
        return listaFactor;
    }

    /**
     * @param listaFactor the listaFactor to set
     */
    public void setListaFactor(List<Factor> listaFactor) {
        this.listaFactor = listaFactor;
    }

    /**
     * @return the listaEstados
     */
    public List<Estado> getListaEstados() {
        return listaEstados;
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
     * @return the factorEdit
     */
    public Factor getFactorEdit() {
        return factorEdit;
    }

    /**
     * @param factorEdit the factorEdit to set
     */
    public void setFactorEdit(Factor factorEdit) {
        this.factorEdit = factorEdit;
    }

}
