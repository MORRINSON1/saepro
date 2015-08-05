/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.implementacion;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import saepro.ejb.ProgramaFacadeLocal;
import saepro.modelo.Programa;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerEvaluador {
    
    @EJB
    ProgramaFacadeLocal programaEjb;

    private List<Programa> listaProgramas;
    
    @PostConstruct
    public void init(){
        cargarProgramas();
    }
    
    
    private void cargarProgramas () {
        listaProgramas = programaEjb.findAll();
        for(Programa p: listaProgramas){
            System.out.println("p --> "+p.getNombre());
        }
    }

    /**
     * @return the listaProgramas
     */
    public List<Programa> getListaProgramas() {
        return listaProgramas;
    }

    /**
     * @param listaProgramas the listaProgramas to set
     */
    public void setListaProgramas(List<Programa> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }
    
}
