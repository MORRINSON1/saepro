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
import saepro.modelo.TipoPregunta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class TipoPreguntaFacade extends AbstractFacade<TipoPregunta> implements TipoPreguntaFacadeLocal {

    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPreguntaFacade() {
        super(TipoPregunta.class);
    }

    @Override
    public List<TipoPregunta> listarTipoPreguntasActivas() {
        String sql = "FROM TipoPregunta t WHERE t.estado.idEstado = 1 ORDER BY t.id DESC";
        List<TipoPregunta> listaTipoPreguntas = null;
        try {

            Query query = em.createQuery(sql);
            listaTipoPreguntas = query.getResultList();

        } catch (Exception ex) {

            System.out.println("Error metodo listarTipoPreguntasActivas " + ex.getMessage());

        }
        return listaTipoPreguntas;
    }

    @Override
    public TipoPregunta getTipoPreguntaByCodigo(String codigo) {

        String sql = "SELECT t FROM TipoPregunta t WHERE t.codigo = '" + codigo + "'";
        TypedQuery<TipoPregunta> query = em.createQuery(sql, TipoPregunta.class);
        return query.getSingleResult();
    }

}
