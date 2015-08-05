/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Local;
import saepro.modelo.TipoPregunta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Local
public interface TipoPreguntaFacadeLocal {

    void create(TipoPregunta tipoPregunta);

    void edit(TipoPregunta tipoPregunta);

    void remove(TipoPregunta tipoPregunta);

    TipoPregunta find(Object id);

    List<TipoPregunta> findAll();

    List<TipoPregunta> findRange(int[] range);

    int count();
    
    List<TipoPregunta> listarTipoPreguntasActivas();
    
    TipoPregunta getTipoPreguntaByCodigo (String codigo);
}
