/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Entity
@Table(name = "alternativasrespuesta")
public class AlternativaRespuesta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "alternativa")
    private String alternativa;
    
    @Column(name = "valor")
    private Double valor;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn (name = "id_tipopregunta")
    private TipoPregunta idTipoPregunta;
    
    @ManyToOne
    @JoinColumn (name = "idestado")
    private Estado idEstado;

    public AlternativaRespuesta() {
    }

    public AlternativaRespuesta(Integer id, String alternativa, Double valor, String descripcion, TipoPregunta idTipoPregunta, Estado idEstado) {
        this.id = id;
        this.alternativa = alternativa;
        this.valor = valor;
        this.descripcion = descripcion;
        this.idTipoPregunta = idTipoPregunta;
        this.idEstado = idEstado;
    }
   

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the alternativa
     */
    public String getAlternativa() {
        return alternativa;
    }

    /**
     * @param alternativa the alternativa to set
     */
    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idTipoPregunta
     */
    public TipoPregunta getIdTipoPregunta() {
        return idTipoPregunta;
    }

    /**
     * @param idTipoPregunta the idTipoPregunta to set
     */
    public void setIdTipoPregunta(TipoPregunta idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    /**
     * @return the idEstado
     */
    public Estado getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
