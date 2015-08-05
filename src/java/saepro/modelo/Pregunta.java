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
@Table (name = "preguntas")
public class Pregunta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idPregunta;
    
    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "enunciado")
    private String enunciado;
    
    @ManyToOne
    @JoinColumn (name = "tipo")
    private TipoPregunta tipoPregunta;
    
    @ManyToOne
    @JoinColumn (name = "id_estado")
    private Estado estado;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta, String codigo, String enunciado, TipoPregunta tipoPregunta, Estado estado) {
        this.idPregunta = idPregunta;
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.tipoPregunta = tipoPregunta;
        this.estado = estado;
    }
    
    

    /**
     * @return the idPregunta
     */
    public Integer getIdPregunta() {
        return idPregunta;
    }

    /**
     * @param idPregunta the idPregunta to set
     */
    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the enunciado
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * @param enunciado the enunciado to set
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
