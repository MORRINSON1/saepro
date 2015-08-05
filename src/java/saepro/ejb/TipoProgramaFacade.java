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
import saepro.modelo.TipoPrograma;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class TipoProgramaFacade extends AbstractFacade<TipoPrograma> implements TipoProgramaFacadeLocal {
    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoProgramaFacade() {
        super(TipoPrograma.class);
    }

    @Override
    public List<TipoPrograma> getAllTipoProgramasActivos() {
        String sql = "FROM TipoPrograma t WHERE t.estado.idEstado = 1";
        
        TypedQuery<TipoPrograma> query = em.createQuery(sql, TipoPrograma.class);
        return query.getResultList();
    }
    
}
