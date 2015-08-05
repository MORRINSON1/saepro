/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import saepro.modelo.Indicador;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class IndicadorFacade extends AbstractFacade<Indicador> implements IndicadorFacadeLocal {
    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicadorFacade() {
        super(Indicador.class);
    }
    
    @Override
    public List<Indicador> getAllIndicadoresActivos(){
        List<Indicador> listaIndicadores = null;
        try {
            Query query = em.createQuery("FROM Indicador i WHERE i.idEstado.idEstado = 1 ORDER BY i.id DESC");
            listaIndicadores = query.getResultList();
        }catch (Exception ex) {
            System.out.println("Error metodo getAllIndicadoresActivos "+ex.getMessage());
        } return listaIndicadores;
    } 
    
}
