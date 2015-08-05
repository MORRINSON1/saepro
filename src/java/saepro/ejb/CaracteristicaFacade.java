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
import saepro.modelo.Caracteristica;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class CaracteristicaFacade extends AbstractFacade<Caracteristica> implements CaracteristicaFacadeLocal {
    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracteristicaFacade() {
        super(Caracteristica.class);
    }
    
    @Override
    public List<Caracteristica> listarCaracteristicas(){
        String sql = "FROM Caracteristica c WHERE c.estado.idEstado = 1 ORDER BY c.id DESC";
        List<Caracteristica> listaCaracteristica = null;
        try {
            Query query = em.createQuery(sql);
            listaCaracteristica = query.getResultList();
        }catch(Exception ex){
            
        } return listaCaracteristica;
    }
    
}
