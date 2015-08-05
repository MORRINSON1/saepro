/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saepro.ejb;

import java.util.List;
import javax.ejb.Local;
import saepro.modelo.Caracteristica;

/**
 *
 * @author Carlos Jose Mendoza
 */
@Local
public interface CaracteristicaFacadeLocal {

    void create(Caracteristica caracteristica);

    void edit(Caracteristica caracteristica);

    void remove(Caracteristica caracteristica);

    Caracteristica find(Object id);

    List<Caracteristica> findAll();

    List<Caracteristica> findRange(int[] range);

    int count();
    
    List<Caracteristica> listarCaracteristicas();
}
