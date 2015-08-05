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
@Table(name = "indicadores")
public class Indicador implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn (name = "id_responsable")
    private Fuente idResponsable;
    
    @ManyToOne
    @JoinColumn (name = "id_estado")
    private Estado idEstado;

    public Indicador() {
    }

    public Indicador(Integer id, String codigo, String descripcion, Fuente idResponsable, Estado idEstado) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.idResponsable = idResponsable;
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
     * @return the idResponsable
     */
    public Fuente getIdResponsable() {
        return idResponsable;
    }

    /**
     * @param idResponsable the idResponsable to set
     */
    public void setIdResponsable(Fuente idResponsable) {
        this.idResponsable = idResponsable;
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
