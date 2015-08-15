/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Local;
import saepro.modelo.TipoInstrumento;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Local
public interface TipoInstrumentoFacadeLocal {

    void create(TipoInstrumento tipoInstrumento);

    void edit(TipoInstrumento tipoInstrumento);

    void remove(TipoInstrumento tipoInstrumento);

    TipoInstrumento find(Object id);

    List<TipoInstrumento> findAll();

    List<TipoInstrumento> findRange(int[] range);

    int count();
    
}
