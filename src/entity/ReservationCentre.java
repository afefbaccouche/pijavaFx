/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;


/**
 *
 * @author esprit
 */
public class ReservationCentre extends RecursiveTreeObject<ReservationCentre> {

    private int id;
    private Centre centre;
    private int nombreJ = 0;
    private Date dateD;
    private Date dateF;
    private User client;

    public ReservationCentre(int id, Centre centre, int nombreJ, Date dateD, Date dateF, User client) {
        this.id = id;
        this.centre = centre;
        this.nombreJ = nombreJ;
        this.dateD = dateD;
        this.dateF = dateF;
        this.client = client;
    }

    public ReservationCentre(Centre centre, int nombreJ, Date dateD, Date dateF, User client) {
        this.centre = centre;
        this.nombreJ = nombreJ;
        this.dateD = dateD;
        this.dateF = dateF;
        this.client = client;
    }

    public ReservationCentre() {
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

    public int getNombreJ() {
        return nombreJ;
    }

    public void setNombreJ(int nombreJ) {
        this.nombreJ = nombreJ;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ReservationCentre{" + "id=" + id + ", centre=" + centre + ", nombreJ=" + nombreJ + ", dateD=" + dateD + ", dateF=" + dateF + ", client=" + client + '}';
    }
    
    

}
