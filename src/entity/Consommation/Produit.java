/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Consommation;

/**
 *
 * @author soumaya ch
 */
public class Produit {
    
    private int idProduit;
    private String nomProduit;
    private float prixProduit;
    private Magasins MagProduit;
    private int quantiteProduit;
    private String typeProduit;
    private String imageProduit;
   

    public Produit() {
    }

    public Produit(int idProduit, String nomProduit, float prixProduit, Magasins MagProduit, int quantiteProduit, String typeProduit, String imageProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.MagProduit = MagProduit;
        this.quantiteProduit = quantiteProduit;
        this.typeProduit = typeProduit;
        this.imageProduit = imageProduit;
    }

    public Produit(int idProduit, String nomProduit, float prixProduit, Magasins MagProduit, int quantiteProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.MagProduit = MagProduit;
        this.quantiteProduit = quantiteProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    
    
    
    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

   

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Magasins getMagProduit() {
        return MagProduit;
    }

    public void setMagProduit(Magasins MagProduit) {
        this.MagProduit = MagProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", nomProduit="
                + nomProduit + ", prixProduit=" + prixProduit 
                + ", MagProduit=" + MagProduit + ", quantiteProduit="
                + quantiteProduit + ", typeProduit=" + typeProduit + '}';
    }
    
    

}
