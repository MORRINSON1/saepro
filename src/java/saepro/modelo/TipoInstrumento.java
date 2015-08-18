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
 * @author Tecnologico
 */
@Entity
@Table(name = "tipoinstrumento")
public class TipoInstrumento implements Serializable {
   
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="codigo")
    private String codigo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "preguntas")
    private boolean isPregunta;
    
    @ManyToOne
    @JoinColumn (name = "id_estado")
    private Estado id_estado;

    public TipoInstrumento(){
    }

    public TipoInstrumento(Integer id, String codigo, String descripcion, boolean isPregunta, Estado estado) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.isPregunta = isPregunta;
        this.id_estado = estado;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return id_estado;
    }

    public void setEstado(Estado estado) {
        this.id_estado = estado;
    }

    /**
     * @return the isPregunta
     */
    public boolean isIsPregunta() {
        return isPregunta;
    }

    /**
     * @param isPregunta the isPregunta to set
     */
    public void setIsPregunta(boolean isPregunta) {
        this.isPregunta = isPregunta;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
