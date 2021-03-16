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
public class Magasins {
    private int idMagasin;
    private String nomMagasin;
    private String adressMagasin;
    private String numMagasin;
    private String emailMagasin;
    private String imageMagasin;

    public Magasins() {
    }

    public Magasins(int idMagasin, String nomMagasin, String adressMagasin, String numMagasin, String emailMagasin, String imageMagasin) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
        this.adressMagasin = adressMagasin;
        this.numMagasin = numMagasin;
        this.emailMagasin = emailMagasin;
        this.imageMagasin = imageMagasin;
    }

    public Magasins(int idMagasin, String nomMagasin, String adressMagasin, String numMagasin, String emailMagasin) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
        this.adressMagasin = adressMagasin;
        this.numMagasin = numMagasin;
        this.emailMagasin = emailMagasin;
    }

    public Magasins(String nomMagasin, String adressMagasin, String numMagasin, String emailMagasin) {
        this.nomMagasin = nomMagasin;
        this.adressMagasin = adressMagasin;
        this.numMagasin = numMagasin;
        this.emailMagasin = emailMagasin;
    }

    public String getImageMagasin() {
        return imageMagasin;
    }

    public void setImageMagasin(String imageMagasin) {
        this.imageMagasin = imageMagasin;
    }

    
    
    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getAdressMagasin() {
        return adressMagasin;
    }

    public void setAdressMagasin(String adressMagasin) {
        this.adressMagasin = adressMagasin;
    }

    public String getNumMagasin() {
        return numMagasin;
    }

    public void setNumMagasin(String numMagasin) {
        this.numMagasin = numMagasin;
    }

    public String getEmailMagasin() {
        return emailMagasin;
    }

    public void setEmailMagasin(String emailMagasin) {
        this.emailMagasin = emailMagasin;
    }

    @Override
    public String toString() {
        return "Magasins{" + "idMagasin=" + idMagasin + ", nomMagasin=" + nomMagasin + ", adressMagasin=" + adressMagasin + ", numMagasin=" + numMagasin + ", emailMagasin=" + emailMagasin + ", imageMagasin=" + imageMagasin + '}';
    }
    
    
}
 