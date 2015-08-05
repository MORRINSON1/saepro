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
import saepro.ejb.AlternativaRespuestaFacadeLocal;
import saepro.ejb.EstadoFacadeLocal;
import saepro.ejb.TipoPreguntaFacadeLocal;
import saepro.modelo.AlternativaRespuesta;
import saepro.modelo.Estado;
import saepro.modelo.TipoPregunta;
import saepro.recursos.Recurso;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerTipoPregunta implements Serializable {

    @EJB
    TipoPreguntaFacadeLocal tipoPreguntaEjb;

    @EJB
    AlternativaRespuestaFacadeLocal alternativaRespuestaEjb;

    private TipoPregunta tipoPregunta;
    private TipoPregunta tipoPreguntaEdit;
    private AlternativaRespuesta alternativaRespuesta;
    private AlternativaRespuesta alternativaRespuestaEdit;
    private List<AlternativaRespuesta> listaAlternativasRespuesta;
    private List<TipoPregunta> listaTipoPregunta;
    private boolean isAbierta;
    private boolean disable;
    private boolean isAbiertaEdit;

    @PostConstruct
    public void init() {
        this.tipoPregunta = new TipoPregunta();
        this.tipoPreguntaEdit = new TipoPregunta();
        this.alternativaRespuesta = new AlternativaRespuesta();
        this.listaTipoPregunta = new ArrayList<>();
        this.listaAlternativasRespuesta = new ArrayList<>();
        this.alternativaRespuestaEdit = new AlternativaRespuesta();
        this.disable = false;
        cargarTipoPregunta();
    }

    public void registrarTipoPregunta() {

        try {

            if (Recurso.validarTipoPregunta(listaTipoPregunta, tipoPregunta.getDescripcion(), tipoPregunta.getCodigo())) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje", "Ya Existe ese Tipo de Pregunta"));

            } else {

                Estado estado = new Estado();
                estado.setIdEstado(1);

                tipoPregunta.setAbierta(isAbierta ? 1 : 0);

                tipoPregunta.setEstado(estado);

                if (!isAbierta) {
                    if (!listaAlternativasRespuesta.isEmpty()) {

                        tipoPreguntaEjb.create(tipoPregunta);
                        TipoPregunta tipoPreguntaRecienInsert = new TipoPregunta();
                        tipoPreguntaRecienInsert = tipoPreguntaEjb.getTipoPreguntaByCodigo(tipoPregunta.getCodigo());

                        if (null != tipoPreguntaRecienInsert) {

                            // Mejorar esto, no hacer de esta forma
                            for (AlternativaRespuesta alternativaRespuesta : listaAlternativasRespuesta) {
                                alternativaRespuesta.setIdTipoPregunta(tipoPreguntaRecienInsert);
                                alternativaRespuesta.setIdEstado(estado);
                                alternativaRespuestaEjb.create(alternativaRespuesta);
                            }
                            listaAlternativasRespuesta.clear();
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Debe registrar por lo menos una Alternativa"));
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Tipo de Pregunta Guardada"));
                cargarTipoPregunta();
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Guardar el Tipo de Pregunta " + ex.getMessage()));
        }
    }

    public void agregarAlternativaRespuesta() {
        try {
            String mensajeValidacion = Recurso.validarCamposVaciosAlternativa(alternativaRespuesta);
            if (mensajeValidacion.equals("true")) {
                String estado = Recurso.validarAlternativa(listaAlternativasRespuesta, alternativaRespuesta.getAlternativa(), alternativaRespuesta.getValor());

                if (estado.equals("true")) {
                    this.listaAlternativasRespuesta.add(alternativaRespuesta);
                    alternativaRespuesta = new AlternativaRespuesta();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Ya Existe una Alternativa de Respuesta con esa " + estado));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", mensajeValidacion));
            }

        } catch (Exception ex) {
            System.out.println("error en metodo agregarAlternativaRespuesta " + ex.getMessage());
        }
    }

    public void eliminarAlternativaRespuesta(AlternativaRespuesta alternativa) {
        try {

            if (!listaAlternativasRespuesta.isEmpty()) {
                listaAlternativasRespuesta.remove(alternativa);
            }

        } catch (Exception ex) {
            System.out.println("Error en metodo eliminarAlternativaRespuesta" + ex.getMessage());
        }
    }

    public void desactivar() {

        this.disable = this.isAbierta ? true : false;
        if (!listaAlternativasRespuesta.isEmpty() && this.disable) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "No seran Grabadas las Alternativas "));
        }
    }
    
    public void desactivarEdit(){
        this.disable = this.isAbiertaEdit ? true : false;
        if (!listaAlternativasRespuesta.isEmpty() && this.disable) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "No seran Grabadas las Alternativas "));
        }
    }

    private void cargarTipoPregunta() {
        try {
            listaTipoPregunta = tipoPreguntaEjb.listarTipoPreguntasActivas();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Cargar los Tipos de Preguntas " + ex.getMessage()));
        }
    }

    private void verAlternativas(Integer idTipoPregunta) {
        try {

            listaAlternativasRespuesta.clear();
            listaAlternativasRespuesta = alternativaRespuestaEjb.getAlternativasByIdTipoPregunta(idTipoPregunta);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Cargar las Alternativas " + ex.getMessage()));
        }
    }

    public void abrirDialogEditarTipoPregunta(Integer id) {
        TipoPregunta tipoPregunta = null;
        try {
            tipoPregunta = tipoPreguntaEjb.find(id);
            if (null != tipoPregunta) {
                tipoPreguntaEdit = tipoPregunta;
                if (tipoPreguntaEdit.getAbierta() == 1) {
                    this.isAbiertaEdit = true;
                    this.disable = true;
                } else {
                    this.isAbiertaEdit = false;
                    this.disable = false;
                }
                verAlternativas(id);
                RequestContext.getCurrentInstance().update("frmFactores");
                RequestContext.getCurrentInstance().execute("PF('dialogTipoPreguntaEdit').show()");
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Abrir Editar " + ex.getMessage()));
        }

    }

    public void editarTipoPregunta() {

        try {

            tipoPreguntaEjb.edit(tipoPreguntaEdit);
            cargarTipoPregunta();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Editar el tipo de preguntas " + ex.getMessage()));
        }
    }

    public void desactivarTipoPregunta(TipoPregunta tipoPregunta) {

        try {

            tipoPregunta.setEstado(new Estado(2));

            tipoPreguntaEjb.edit(tipoPregunta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", "Tipo de Pregunta Desactivada"));
            cargarTipoPregunta();

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Error al Desactivar tipo de Pregunta " + ex.getMessage()));
        }
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
     * @return the isAbierta
     */
    public boolean isIsAbierta() {
        return isAbierta;
    }

    /**
     * @param isAbierta the isAbierta to set
     */
    public void setIsAbierta(boolean isAbierta) {
        this.isAbierta = isAbierta;
    }

    /**
     * @return the disable
     */
    public boolean isDisable() {
        return disable;
    }

    /**
     * @return the alternativaRespuesta
     */
    public AlternativaRespuesta getAlternativaRespuesta() {
        return alternativaRespuesta;
    }

    /**
     * @param alternativaRespuesta the alternativaRespuesta to set
     */
    public void setAlternativaRespuesta(AlternativaRespuesta alternativaRespuesta) {
        this.alternativaRespuesta = alternativaRespuesta;
    }

    /**
     * @return the listaAlternativasRespuesta
     */
    public List<AlternativaRespuesta> getListaAlternativasRespuesta() {
        return listaAlternativasRespuesta;
    }

    /**
     * @param listaAlternativasRespuesta the listaAlternativasRespuesta to set
     */
    public void setListaAlternativasRespuesta(List<AlternativaRespuesta> listaAlternativasRespuesta) {
        this.listaAlternativasRespuesta = listaAlternativasRespuesta;
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
     * @return the tipoPreguntaEdit
     */
    public TipoPregunta getTipoPreguntaEdit() {
        return tipoPreguntaEdit;
    }

    /**
     * @param tipoPreguntaEdit the tipoPreguntaEdit to set
     */
    public void setTipoPreguntaEdit(TipoPregunta tipoPreguntaEdit) {
        this.tipoPreguntaEdit = tipoPreguntaEdit;
    }

    /**
     * @return the isAbiertaEdit
     */
    public boolean isIsAbiertaEdit() {
        return isAbiertaEdit;
    }

    /**
     * @param isAbiertaEdit the isAbiertaEdit to set
     */
    public void setIsAbiertaEdit(boolean isAbiertaEdit) {
        this.isAbiertaEdit = isAbiertaEdit;
    }

    /**
     * @return the alternativaRespuestaEdit
     */
    public AlternativaRespuesta getAlternativaRespuestaEdit() {
        return alternativaRespuestaEdit;
    }

    /**
     * @param alternativaRespuestaEdit the alternativaRespuestaEdit to set
     */
    public void setAlternativaRespuestaEdit(AlternativaRespuesta alternativaRespuestaEdit) {
        this.alternativaRespuestaEdit = alternativaRespuestaEdit;
    }

}
