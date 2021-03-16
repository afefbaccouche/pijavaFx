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
public class BesoinArgent extends RecursiveTreeObject<BesoinArgent> {

    private int id;
    private int idMaisonRetraite;
    private String titreBesoinArgent;
    private String UserInNeed;
    private int disponibilite;
    private double montantArgent;

    public BesoinArgent() {
    }

    public BesoinArgent(int id, int idMaisonRetraite, String titreBesoinArgent, String UserInNeed, int disponibilite, double montantArgent) {
        this.id = id;
        this.idMaisonRetraite = idMaisonRetraite;
        this.titreBesoinArgent = titreBesoinArgent;
        this.UserInNeed = UserInNeed;
        this.disponibilite = disponibilite;
        this.montantArgent = montantArgent;
    }

    public BesoinArgent(int idMaisonRetraite, String titreBesoinArgent, String UserInNeed, int disponibilite, double montantArgent) {
        this.idMaisonRetraite = idMaisonRetraite;
        this.titreBesoinArgent = titreBesoinArgent;
        this.UserInNeed = UserInNeed;
        this.disponibilite = disponibilite;
        this.montantArgent = montantArgent;
    }

    public BesoinArgent(String titreBesoinArgent, String UserInNeed, int disponibilite, double montantArgent) {
        this.titreBesoinArgent = titreBesoinArgent;
        this.UserInNeed = UserInNeed;
        this.disponibilite = disponibilite;
        this.montantArgent = montantArgent;
    }

    public BesoinArgent(int idMaisonRetraite, String titreBesoinArgent, int disponibilite, double montantArgent) {
        this.idMaisonRetraite = idMaisonRetraite;
        this.titreBesoinArgent = titreBesoinArgent;
        this.disponibilite = disponibilite;
        this.montantArgent = montantArgent;
    }

    public String getTitreBesoinArgent() {
        return titreBesoinArgent;
    }

    public void setTitreBesoinArgent(String titreBesoinArgent) {
        this.titreBesoinArgent = titreBesoinArgent;
    }

    public String getUserInNeed() {
        return UserInNeed;
    }

    public void setUserInNeed(String UserInNeed) {
        this.UserInNeed = UserInNeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getMontantArgent() {
        return montantArgent;
    }

    public void setMontantArgent(double montantArgent) {
        this.montantArgent = montantArgent;
    }

    @Override
    public String toString() {
        return "BesoinArgent{" + "id=" + id + ", idMaisonRetraite=" + idMaisonRetraite + ", titreBesoinArgent=" + titreBesoinArgent + ", UserInNeed=" + UserInNeed + ", disponibilite=" + disponibilite + ", montantArgent=" + montantArgent + '}';
    }

}
