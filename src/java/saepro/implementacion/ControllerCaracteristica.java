/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.implementacion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import saepro.ejb.CaracteristicaFacadeLocal;
import saepro.ejb.EstadoFacadeLocal;
import saepro.modelo.Caracteristica;
import saepro.modelo.Estado;
import saepro.modelo.Factor;
import saepro.recursos.Recurso;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerCaracteristica implements Serializable {

    @EJB
    CaracteristicaFacadeLocal caracteristicaEjb;

    @EJB
    EstadoFacadeLocal estadoEjb;

    private Caracteristica caracteristica;
    private Estado estado;
    private Caracteristica caracteristicaEdit;
    private List<Estado> listaEstados;
    private List<Caracteristica> listaCaracteristica;

    @PostConstruct
    public void init() {
        this.caracteristica = new Caracteristica();
        this.estado = new Estado();
        caracteristicaEdit = new Caracteristica();
        this.listaEstados = estadoEjb.findAll();
        this.listaCaracteristica = caracteristicaEjb.listarCaracteristicas();
    }

    public void registrarCaracteristica() {

        try {

            if (Recurso.validarCodigoDescripcionCaracteristica(listaCaracteristica, caracteristica.getCodigo(), caracteristica.getDescripcion())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Caracteristica Ya Existe"));
            } else {

                caracteristica.setEstado(estado);
                caracteristicaEjb.create(caracteristica);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Caracteristica Registrada"));
                listar();
            }

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Registrar  la Caracteristica " + ex.getMessage()));
        }
    }

    public void abrirDialogo(Caracteristica Objcaracteristica) {
        caracteristicaEdit = Objcaracteristica;
        RequestContext.getCurrentInstance().update("frmFactores");
        RequestContext.getCurrentInstance().execute("PF('dialogoEditarCaracteristica').show()");
    }

    public void desactivarCaracteristica(Caracteristica Objcaracteristica) {

        try {

            estado = new Estado();
            estado.setIdEstado(new Integer(2));
            Objcaracteristica.setEstado(estado);
            caracteristicaEjb.edit(Objcaracteristica);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Caracteristica Desactivada"));
            listar();

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Desactivar la Caracteristica " + ex.getMessage()));
        }
    }

    public void listar() {
        this.listaCaracteristica = caracteristicaEjb.listarCaracteristicas();
    }

    public void editarCaracteristica() {

        try {
            
            if (Recurso.validarCodigoDescripcionCaracteristica(listaCaracteristica, caracteristicaEdit)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Caracteristica Ya Existe"));
            } else {
                caracteristicaEjb.edit(caracteristicaEdit);
                listar();
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarCaracteristica').hide()");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Caracteristica Modificada"));
                RequestContext.getCurrentInstance().update("frmFactores:tableCaracteristicas");
                caracteristicaEdit = new Caracteristica();
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Editar la Caracteristica " + ex.getMessage()));
        }
    }

    public void cancelar() {
        caracteristicaEdit = new Caracteristica();
        RequestContext.getCurrentInstance().update("frmFactores:dialogoEditarCaracteristica");
    }

    /**
     * @return the caracteristica
     */
    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    /**
     * @param caracteristica the caracteristica to set
     */
    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
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
     * @return the listaCaracteristica
     */
    public List<Caracteristica> getListaCaracteristica() {
        return listaCaracteristica;
    }

    /**
     * @param listaCaracteristica the listaCaracteristica to set
     */
    public void setListaCaracteristica(List<Caracteristica> listaCaracteristica) {
        this.listaCaracteristica = listaCaracteristica;
    }

    /**
     * @return the caracteristicaEdit
     */
    public Caracteristica getCaracteristicaEdit() {
        return caracteristicaEdit;
    }

    /**
     * @param caracteristicaEdit the caracteristicaEdit to set
     */
    public void setCaracteristicaEdit(Caracteristica caracteristicaEdit) {
        this.caracteristicaEdit = caracteristicaEdit;
    }

}
