/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 *
 * @author jacem
 */
public class BesoinObjet  extends RecursiveTreeObject<BesoinObjet> {

    private int id;
    private int idMaisonRetraite;
    private String titreBesoinObjet;
    private String UserInNeed;
    private int disponibilite;
    private String typeBesoinObjet;

    public BesoinObjet() {
    }

    public BesoinObjet(int id, int idMaisonRetraite, String titreBesoinObjet, String UserInNeed, int disponibilite, String typeBesoinObjet) {
        this.id = id;
        this.idMaisonRetraite = idMaisonRetraite;
        this.titreBesoinObjet = titreBesoinObjet;
        this.UserInNeed = UserInNeed;
        this.disponibilite = disponibilite;
        this.typeBesoinObjet = typeBesoinObjet;
    }

    public BesoinObjet(int idMaison, String titreB, String userB, int dispoB, String typeB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BesoinObjet(int idMaison, String titreB, int dispoB, String typeB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BesoinObjet(String titreB, String userB, int dispoB, String typeB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BesoinObjet(String titreB, String userB, String descB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUserInNeed() {
        return UserInNeed;
    }

    public void setUserInNeed(String UserInNeed) {
        this.UserInNeed = UserInNeed;
    }

    public int getIdMaisonRetraite() {
        return idMaisonRetraite;
    }

    public void setIdMaisonRetraite(int idMaisonRetraite) {
        this.idMaisonRetraite = idMaisonRetraite;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getTypeBesoinObjet() {
        return typeBesoinObjet;
    }

    public void setTypeBesoinObjet(String typeBesoinObjet) {
        this.typeBesoinObjet = typeBesoinObjet;
    }
    
    public String getTitreBesoinObjet() {
        return typeBesoinObjet;
    }

    public void setTitreBesoinObjet(String titreBesoinObjet) {
        this.titreBesoinObjet = titreBesoinObjet;
    }
    @Override
    public String toString() {
        return "BesoinArgent{" + "id=" + id + ", idMaisonRetraite=" + idMaisonRetraite + ", titreBesoinObjet=" + titreBesoinObjet + ", UserInNeed=" + UserInNeed + ", disponibilite=" + disponibilite + ", typeBesoinObjet=" + typeBesoinObjet + '}';
    }

  

}
