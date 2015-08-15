/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import saepro.modelo.TipoInstrumento;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Stateless
public class TipoInstrumentoFacade extends AbstractFacade<TipoInstrumento> implements TipoInstrumentoFacadeLocal {
    @PersistenceContext(unitName = "saeproPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoInstrumentoFacade() {
        super(TipoInstrumento.class);
    }
    
}
