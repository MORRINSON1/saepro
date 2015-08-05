/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.implementacion;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import saepro.ejb.AlternativaRespuestaFacadeLocal;
import saepro.modelo.AlternativaRespuesta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@ManagedBean
@ViewScoped
public class ControllerAlternativaRespuesta implements Serializable {

    
    @EJB
    AlternativaRespuestaFacadeLocal alternativaRespuestaEjb;
    
    private AlternativaRespuesta alternativaRespuesta;
    
    @Init
    public void init(){
        alternativaRespuesta = new AlternativaRespuesta();
    }
   
   public void probarStoredProcedure() {
       List<AlternativaRespuesta> listaa = null;
       listaa = alternativaRespuestaEjb.getAlternativas();
       for(AlternativaRespuesta ar : listaa){
           System.out.println("Alternativa "+ar.getDescripcion());
       }
   }
    
    
}
