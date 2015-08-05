/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saepro.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import saepro.modelo.Factor;

@Stateless
public class FactorFacade extends AbstractFacade<Factor> implements FactorFacadeLocal {

    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactorFacade() {
        super(Factor.class);
    }

    @Override
    public List<Factor> listarFactores() {
        List<Factor> listaFactor = null;
        
        Query q = em.createQuery("FROM Factor f WHERE f.id_estado.idEstado = 1 ORDER BY f.idFactor DESC");
        listaFactor = q.getResultList();
        return listaFactor;
        
    }
}
