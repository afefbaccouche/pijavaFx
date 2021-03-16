/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author esprit
 */
public class Maladie {

    private int id;
    private String nom;
    private User medecin;

    public Maladie(int id, String nom, User medecin) {
        this.id = id;
        this.nom = nom;
        this.medecin = medecin;
    }

    public Maladie(String nom, User medecin) {
        this.nom = nom;
        this.medecin = medecin;
    }
    
    

    public Maladie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public User getMedecin() {
        return medecin;
    }

    public void setMedecin(User medecin) {
        this.medecin = medecin;
    }

}
