/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Consommation;

import java.util.*;

/**
 *
 * @author ASUS
 */
public class Panier {
    
   private List <Produit>   produits=new ArrayList<>()  ;
   private Float totale;

    public Panier(List<Produit> produits, Float totale) {
        this.produits = produits;
        this.totale = totale;
    }

    public Panier() {
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Float getTotale() {
        return totale;
    }

    public void setTotale(Float totale) {
        this.totale = totale;
    }

    @Override
    public String toString() {
        return "Panier{" + "produits=" + produits + ", totale=" + totale + '}';
    }

 
    
    
}
