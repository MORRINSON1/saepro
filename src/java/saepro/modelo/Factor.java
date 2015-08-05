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

@Entity
@Table (name = "factores")
public class Factor implements Serializable {
  
  @Column (name = "descripcion")
  private String descripcion;
  
  @Column (name = "codigo")
  private String codigo;
  
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column (name = "id_factor")
  private int idFactor;
  
  @ManyToOne
  @JoinColumn (name = "id_estado")
  private Estado id_estado;

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
     * @return the idFactor
     */
    public int getIdFactor() {
        return idFactor;
    }

    /**
     * @param idFactor the idFactor to set
     */
    public void setIdFactor(int idFactor) {
        this.idFactor = idFactor;
    }

    /**
     * @return the id_estado
     */
    public Estado getId_estado() {
        return id_estado;
    }

    /**
     * @param id_estado the id_estado to set
     */
    public void setId_estado(Estado id_estado) {
        this.id_estado = id_estado;
    }
}
