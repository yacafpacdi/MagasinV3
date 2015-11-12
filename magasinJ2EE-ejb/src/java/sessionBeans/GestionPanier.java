package sessionBeans;

import entites.LignePanier;
import entites.Produit;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class GestionPanier implements GestionPanierLocal {
    @EJB
    private BeanMagasinLocal beanMagasin;
    
    private HashMap<String, LignePanier> panier;

    @PostConstruct
    public void init() {
        panier = new HashMap();
    }
    /**
     * le panier contient la ligne de panier
     * correspondant à ref
     * @param ref
     * @param delta 
     */
    private void update(String ref, int delta){
        // on suppose que ligne != null
        LignePanier ligne = panier.get(ref);
        int qte = ligne.getQte()+ delta;
        
        if(qte > ligne.getProduit().getQte()){
            qte = ligne.getProduit().getQte();
        }
        
        ligne.setQte(qte);
        
        if(qte <= 0){
            panier.remove(ref);
        }
       
    }
    
    public void add(String ref){
        if(panier.containsKey(ref)){
            update(ref, 1);
        }else {
            Produit p = beanMagasin.selectByRef(ref);
            LignePanier lg = new LignePanier(p);
            panier.put(lg.getReference(), lg);
        }
    }
    
    public void decrementer(String ref){
        if(panier.containsKey(ref)){
            update(ref, -1);
        }
    }
    
    public Double getTotalHT(){
        Double total =0.0;
        for( LignePanier l: getListe()){
            total += l.getPrixHT();
        }
        return total;
    }
    
    public Collection<LignePanier> getListe(){
        return panier.values();
    }
    
    public boolean isEmpty(){
        return panier.isEmpty();
    }
    
}
