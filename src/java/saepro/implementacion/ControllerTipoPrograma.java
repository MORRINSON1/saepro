/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.implementacion;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import saepro.ejb.EstadoFacadeLocal;
import saepro.ejb.TipoProgramaFacadeLocal;
import saepro.modelo.Estado;
import saepro.modelo.TipoPrograma;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerTipoPrograma {

    @EJB
    EstadoFacadeLocal estadoEjb;

    @EJB
    TipoProgramaFacadeLocal tipoProgramaEjb;

    private TipoPrograma tipoPrograma;
    private TipoPrograma tipoProgramaEdit;
    private Estado estado;
    private List<Estado> listaEstados;
    private List<TipoPrograma> listaTipoPrograma;

    @PostConstruct
    public void init() {
        this.estado = new Estado();
        this.tipoPrograma = new TipoPrograma();
        this.tipoProgramaEdit = new TipoPrograma();
        cargarEstados();
        cargarTipoPrograma();
    }

    public void guardarTipoPrograma() {
        try {
            tipoPrograma.setEstado(estado);
            System.out.println("codigo: "+tipoPrograma.getCodigo()+" descripcion "+tipoPrograma.getDescripcion());
            tipoProgramaEjb.create(tipoPrograma);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Tipo de Programa guardado"));
            cargarTipoPrograma();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Guardar el Tipo de Progrma " + ex.getMessage()));
        }
    }
    
    public void editarTipoPrograma(){
        try {
            tipoProgramaEjb.edit(tipoProgramaEdit);
            RequestContext.getCurrentInstance().execute("PF('dialogoEditarTipoPrograma').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Tipo de Programa editado"));
            cargarTipoPrograma();
            RequestContext.getCurrentInstance().update("frmFactores:tableTipoPrograma");
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al editar Tipo de Progrma " + ex.getMessage()));
        }
    }
    
    public void abrirDialog(Integer idTipoPrograma) {
        tipoProgramaEdit = null;
        tipoProgramaEdit = tipoProgramaEjb.find(idTipoPrograma);
        RequestContext.getCurrentInstance().update("frmFactores");
        RequestContext.getCurrentInstance().execute("PF('dialogoEditarTipoPrograma').show()");
      
    }
    
    public void desactivarTipoPrograma(Integer idTipoPrograma){
        try {
            TipoPrograma tipoProgramaDesactivar = tipoProgramaEjb.find(idTipoPrograma);
            if(null != tipoProgramaDesactivar){
                Estado estadoo = new Estado(2);
                tipoProgramaDesactivar.setEstado(estadoo);
                tipoProgramaEjb.edit(tipoProgramaDesactivar);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Tipo de Programa Desactivado"));
                cargarTipoPrograma();
            }
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al desactivar Tipo de Progrma " + ex.getMessage() +"Contactese con el Administrador"));
        }
    }

    private void cargarTipoPrograma() {
        try {
            this.listaTipoPrograma = tipoProgramaEjb.getAllTipoProgramasActivos();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Cargar los Tipos de Progrmas " + ex.getMessage()));
        }
    }

    private void cargarEstados() {
        try {
            this.listaEstados = estadoEjb.findAll();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Cargar los Estados " + ex.getMessage()));
        }
    }

    /**
     * @return the tipoPrograma
     */
    public TipoPrograma getTipoPrograma() {
        return tipoPrograma;
    }

    /**
     * @param tipoPrograma the tipoPrograma to set
     */
    public void setTipoPrograma(TipoPrograma tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
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
     * @return the listaTipoPrograma
     */
    public List<TipoPrograma> getListaTipoPrograma() {
        return listaTipoPrograma;
    }

    /**
     * @param listaTipoPrograma the listaTipoPregunta to set
     */
    public void setListaTipoPregunta(List<TipoPrograma> listaTipoPrograma) {
        this.listaTipoPrograma = listaTipoPrograma;
    }

    /**
     * @return the tipoProgramaEdit
     */
    public TipoPrograma getTipoProgramaEdit() {
        return tipoProgramaEdit;
    }

    /**
     * @param tipoProgramaEdit the tipoProgramaEdit to set
     */
    public void setTipoProgramaEdit(TipoPrograma tipoProgramaEdit) {
        this.tipoProgramaEdit = tipoProgramaEdit;
    }

}
