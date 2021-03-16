/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Baklouti
 */
public class EventInvitation {
    private int idInvitation;
    private User user;
    private User userInvite;
    private Evenement evenement;
    private boolean reponse;
    private Date date;

    public EventInvitation() {
    
    }

    public EventInvitation(int idInvitation, User user, User userInvite, Evenement evenement, boolean reponse, Date date) {
        this.idInvitation = idInvitation;
        this.user = user;
        this.userInvite = userInvite;
        this.evenement = evenement;
        this.reponse = reponse;
        this.date = date;
    }

    public EventInvitation(User user, User userInvite, Evenement evenement, boolean reponse, Date date) {
        this.user = user;
        this.userInvite = userInvite;
        this.evenement = evenement;
        this.reponse = reponse;
        this.date = date;
    }

    public int getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(int idInvitation) {
        this.idInvitation = idInvitation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserInvite() {
        return userInvite;
    }

    public void setUserInvite(User userInvite) {
        this.userInvite = userInvite;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public boolean isReponse() {
        return reponse;
    }

    public void setReponse(boolean reponse) {
        this.reponse = reponse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EventInvitation{" + "idInvitation=" + idInvitation + ", user=" + user + ", userInvite=" + userInvite + ", evenement=" + evenement + ", reponse=" + reponse + ", date=" + date + '}';
    }
    
    
    
    
}
