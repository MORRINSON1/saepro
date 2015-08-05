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
import saepro.modelo.AlternativaRespuesta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class AlternativaRespuestaFacade extends AbstractFacade<AlternativaRespuesta> implements AlternativaRespuestaFacadeLocal {

    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlternativaRespuestaFacade() {
        super(AlternativaRespuesta.class);
    }

    @Override
    public List<AlternativaRespuesta> getAlternativas() {
        List<AlternativaRespuesta> lista = null;

        Query q = em.createQuery("call saeprotest.getAlternativasRespuestas()");
        lista = q.getResultList();

        return lista;
    }

    @Override
    public List<AlternativaRespuesta> getAlternativasByIdTipoPregunta(Integer idTipoPregunta) {
        List<AlternativaRespuesta> listaAlternativaRespuesta = null;
        try {
            String sql = "SELECT a FROM AlternativaRespuesta a WHERE a.idTipoPregunta.id = " + idTipoPregunta + "";
            Query query = em.createQuery(sql, AlternativaRespuesta.class);
            listaAlternativaRespuesta = query.getResultList();
        } catch (Exception ex) {
            System.out.println("Error en el metodo getAlternativasById " + ex.getMessage());
        }
        return listaAlternativaRespuesta;
    }

}
