/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author esprit
 */
public class Promotion {

    private int id;
    private Centre centre;
    private float nouveauPrix;
    private int reduction;
    private Date dateDebut;
    private Date dateFin;
    private User manager;

    public Promotion(int id, Centre centre, float nouveauPrix, int reduction, Date dateDebut, Date dateFin, User manager) {
        this.id = id;
        this.centre = centre;
        this.nouveauPrix = nouveauPrix;
        this.reduction = reduction;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.manager = manager;
    }

    public Promotion() {
    }

    public Promotion(Centre centre, float nouveauPrix, int reduction, Date dateDebut, Date dateFin, User manager) {
        this.centre = centre;
        this.nouveauPrix = nouveauPrix;
        this.reduction = reduction;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public float getNouveauPrix() {
        return nouveauPrix;
    }

    public void setNouveauPrix(float nouveauPrix) {
        this.nouveauPrix = nouveauPrix;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", centre=" + centre + ", nouveauPrix=" + nouveauPrix + ", reduction=" + reduction + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", manager=" + manager + '}';
    }
    
    

}
