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
@Table(name = "tipoprograma")
public class TipoPrograma implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoprograma")
    private Integer idTipoPrograma;
    
    @Column(name = "codigo")
    private char codigo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn (name = "id_estado")
    private Estado estado;

    public TipoPrograma() {
    }

    public TipoPrograma(Integer idTipoPrograma, char codigo, String descripcion) {
        this.idTipoPrograma = idTipoPrograma;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    

    /**
     * @return the idTipoPrograma
     */
    public Integer getIdTipoPrograma() {
        return idTipoPrograma;
    }

    /**
     * @param idTipoPrograma the idTipoPrograma to set
     */
    public void setIdTipoPrograma(Integer idTipoPrograma) {
        this.idTipoPrograma = idTipoPrograma;
    }

    /**
     * @return the codigo
     */
    public char getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(char codigo) {
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

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
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
    
    
    
}
