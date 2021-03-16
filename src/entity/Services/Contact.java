/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Services;

import entity.User;

/**
 *
 * @author amani
 */
public class Contact {
    
    int id ;
    User Demandeur ;
    User Receveur ;
    String ReponseDemandeur ;
    String ReponseReceveur ;

    public Contact(int id, User Demandeur, User Receveur, String ReponseDemandeur, String ReponseReceveur) {
        this.id = id;
        this.Demandeur = Demandeur;
        this.Receveur = Receveur;
        this.ReponseDemandeur = ReponseDemandeur;
        this.ReponseReceveur = ReponseReceveur;
    }

  

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDemandeur() {
        return Demandeur;
    }

    public void setDemandeur(User Demandeur) {
        this.Demandeur = Demandeur;
    }

    public User getReceveur() {
        return Receveur;
    }

    public void setReceveur(User Receveur) {
        this.Receveur = Receveur;
    }

   

    public String getReponseDemandeur() {
        return ReponseDemandeur;
    }

    public void setReponseDemandeur(String ReponseDemandeur) {
        this.ReponseDemandeur = ReponseDemandeur;
    }

    public String getReponseReceveur() {
        return ReponseReceveur;
    }

    public void setReponseReceveur(String ReponseReceveur) {
        this.ReponseReceveur = ReponseReceveur;
    }
    
    
    
    
}
