/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Local;
import saepro.modelo.Factor;


@Local
public interface FactorFacadeLocal {

    void create(Factor factor);

    void edit(Factor factor);

    void remove(Factor factor);

    Factor find(Object id);

    List<Factor> findAll();

    List<Factor> findRange(int[] range);

    int count();
    
    List<Factor> listarFactores();
}
