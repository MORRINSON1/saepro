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
import javax.persistence.TypedQuery;
import saepro.modelo.Pregunta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class PreguntaFacade extends AbstractFacade<Pregunta> implements PreguntaFacadeLocal {
    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaFacade() {
        super(Pregunta.class);
    }

    @Override
    public List<Pregunta> getPreguntasActivas() {
        String sql = "FROM Pregunta p WHERE p.estado.idEstado = 1 ORDER BY p.idPregunta DESC";
        TypedQuery<Pregunta> query = em.createQuery(sql, Pregunta.class);
        return query.getResultList();
    }
    
}
