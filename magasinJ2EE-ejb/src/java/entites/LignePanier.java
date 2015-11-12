package entites;

import java.io.Serializable;

public class LignePanier implements Serializable{
    private Produit produit;
    private int qte;

    public LignePanier(Produit produit) {
        this.produit = produit;
        qte = 1;
    }

    public LignePanier(Produit produit, int qte) {
        this.produit = produit;
        this.qte = qte;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return produit.getReference()+"/"+produit.getLabel() + ", qte=" + qte;
    }
    
    public String getReference(){
        return produit.getReference();
    }
    
    public double getPrixHT(){
        return qte * produit.getPrixHT();
    }
    
}
