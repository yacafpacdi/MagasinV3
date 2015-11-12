/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entites.LignePanier;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface GestionPanierLocal {

    public void add(String ref);

    public void decrementer(String ref);

    public Double getTotalHT();

    public Collection<LignePanier> getListe();

    public boolean isEmpty();
    
}
