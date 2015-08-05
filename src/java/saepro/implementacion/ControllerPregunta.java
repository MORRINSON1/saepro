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
import saepro.ejb.PreguntaFacadeLocal;
import saepro.ejb.TipoPreguntaFacadeLocal;
import saepro.modelo.Estado;
import saepro.modelo.Pregunta;
import saepro.modelo.TipoPregunta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerPregunta {
    
    @EJB
    EstadoFacadeLocal estadoEjb;
    
    @EJB
    TipoPreguntaFacadeLocal tipoPreguntaEjb;
    
    @EJB
    PreguntaFacadeLocal preguntaEjb;

   private List<Estado> listaEstado;
   private List<TipoPregunta> listaTipoPregunta;
   private List<Pregunta> listaPreguntas;
   private Estado estado;
   private Pregunta pregunta;
   private TipoPregunta tipoPregunta;
   private Pregunta preguntaEdit;
   
   @PostConstruct
   public void init(){
       cargarEstados();
       cargarTipoPregunta();
       cargarPreguntas();
       this.estado = new Estado();
       this.pregunta = new Pregunta();
       this.preguntaEdit = new Pregunta();
       this.tipoPregunta = new TipoPregunta();
   }
   
   public void registrarPregunta () {
       
       try {
           pregunta.setEstado(estado);
           TipoPregunta tiipoPregunta = tipoPreguntaEjb.find(this.tipoPregunta.getId());
           pregunta.setTipoPregunta(tiipoPregunta);
           preguntaEjb.create(pregunta);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Pregunta  Registrada"));
           cargarPreguntas();
       }catch(Exception ex){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo Registrar Pregunta "+ex.getMessage()));
       }
   }
   
   public void abrirDialog (Integer id) {
       try {
           
           preguntaEdit = preguntaEjb.find(id);
           RequestContext.getCurrentInstance().update("frmFactores");
           RequestContext.getCurrentInstance().execute("PF('dialogoPreguntaEdit').show()");
           
       }catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Cargar el dialogo "+ex.getMessage()));
       }
   }
   
   public void editarPregunta () {
       try {
           
           if(tipoPregunta.getId()!= null){
               TipoPregunta tipoPreguntaa = tipoPreguntaEjb.find(this.tipoPregunta.getId());
               preguntaEdit.setTipoPregunta(tipoPreguntaa);
               tipoPregunta.setId(null);
           }
           preguntaEjb.edit(preguntaEdit);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Pregunta  Modificada"));
           cargarPreguntas();
           RequestContext.getCurrentInstance().update("frmFactores:tablePreguntas");
            RequestContext.getCurrentInstance().execute("PF('dialogoPreguntaEdit').hide();");
       }catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Editar las Preguntas "+ex.getMessage()));
       }
   }
   
   public void cancelar () {
       try {
           tipoPregunta.setId(null);
           RequestContext.getCurrentInstance().execute("PF('dialogoPreguntaEdit').hide();");
           
           
       }catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error "+ex.getMessage()));
       }
   }
   
   public void desactivarPregunta(Integer id){
       try {
           
           Estado estado = new Estado (2);
           Pregunta pregunta = preguntaEjb.find(id);
           pregunta.setEstado(estado);
           
           preguntaEjb.edit(pregunta);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Pregunta  Modificada"));
           cargarPreguntas();
       }catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Desactivar la pregunta "+ex.getMessage()));
       }
       
   }
   
   
   private void cargarEstados(){
       try {
       this.listaEstado = estadoEjb.findAll();
       }catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo Cargar Estados "+ex.getMessage()));
       }
   }
   
   private void cargarTipoPregunta(){
       try {
       this.listaTipoPregunta = tipoPreguntaEjb.listarTipoPreguntasActivas();
       } catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo Cargar Tipo de Preguntas "+ex.getMessage()));
       }
   }
   
   private void cargarPreguntas(){
       try {
       this.listaPreguntas = preguntaEjb.getPreguntasActivas();
       }catch (Exception ex) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo Cargar Preguntas "+ex.getMessage()));
       }
   }

    /**
     * @return the listaEstado
     */
    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    /**
     * @param listaEstado the listaEstado to set
     */
    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
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
     * @return the pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the listaTipoPregunta
     */
    public List<TipoPregunta> getListaTipoPregunta() {
        return listaTipoPregunta;
    }

    /**
     * @param listaTipoPregunta the listaTipoPregunta to set
     */
    public void setListaTipoPregunta(List<TipoPregunta> listaTipoPregunta) {
        this.listaTipoPregunta = listaTipoPregunta;
    }

    /**
     * @return the tipoPregunta
     */
    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    /**
     * @param tipoPregunta the tipoPregunta to set
     */
    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    /**
     * @return the listaPreguntas
     */
    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * @param listaPreguntas the listaPreguntas to set
     */
    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    /**
     * @return the preguntaEdit
     */
    public Pregunta getPreguntaEdit() {
        return preguntaEdit;
    }

    /**
     * @param preguntaEdit the preguntaEdit to set
     */
    public void setPreguntaEdit(Pregunta preguntaEdit) {
        this.preguntaEdit = preguntaEdit;
    }
    
}
