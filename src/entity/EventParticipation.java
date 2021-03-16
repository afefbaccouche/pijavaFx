/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;



/**
 *
 * @author Baklouti
 */
public class EventParticipation {
    private int id;
    private User user;
    private Evenement evenement;
    private Date dateParticipation;

    public EventParticipation() {
    }

    public EventParticipation(int id, User user, Evenement evenement, Date dateParticipation) {
        this.id = id;
        this.user = user;
        this.evenement = evenement;
        this.dateParticipation = dateParticipation;
    }

    public EventParticipation(User user, Evenement evenement, Date dateParticipation) {
        this.user = user;
        this.evenement = evenement;
        this.dateParticipation = dateParticipation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Date getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(Date dateParticipation) {
        this.dateParticipation = dateParticipation;
    }

    @Override
    public String toString() {
        return "EventParticipation{" + "id=" + id + ", user=" + user + ", evenement=" + evenement + ", dateParticipation=" + dateParticipation + '}';
    }
    
    
    
    
}
