/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Local;
import saepro.modelo.AlternativaRespuesta;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Local
public interface AlternativaRespuestaFacadeLocal {

    void create(AlternativaRespuesta alternativaRespuesta);

    void edit(AlternativaRespuesta alternativaRespuesta);

    void remove(AlternativaRespuesta alternativaRespuesta);

    AlternativaRespuesta find(Object id);

    List<AlternativaRespuesta> findAll();

    List<AlternativaRespuesta> findRange(int[] range);

    int count();
    
    List<AlternativaRespuesta> getAlternativas();
    
    List<AlternativaRespuesta> getAlternativasByIdTipoPregunta(Integer idTipoPregunta);
}
