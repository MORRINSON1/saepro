/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Local;
import saepro.modelo.Fuente;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Local
public interface FuenteFacadeLocal {

    void create(Fuente fuente);

    void edit(Fuente fuente);

    void remove(Fuente fuente);

    Fuente find(Object id);

    List<Fuente> findAll();

    List<Fuente> findRange(int[] range);

    int count();
    
    Fuente getFuenteByCodigo (String codigo);
}
