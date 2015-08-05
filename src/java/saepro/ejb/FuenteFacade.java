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
import javax.persistence.TypedQuery;
import saepro.modelo.Fuente;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class FuenteFacade extends AbstractFacade<Fuente> implements FuenteFacadeLocal {

    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuenteFacade() {
        super(Fuente.class);
    }

    @Override
    public Fuente getFuenteByCodigo(String codigo) {
        String sql = "SELECT f FROM Fuente f WHERE f.codigo = '" + codigo + "'";
        TypedQuery<Fuente> query = em.createQuery(sql, Fuente.class);
        return query.getSingleResult();
    }

}
