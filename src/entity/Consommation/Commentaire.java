/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Consommation;

import entity.User;
import java.util.Date;

/**
 *
 * @author soumaya ch
 */
public class Commentaire {
    
    private int idCommentaire;
    private String body;
    private Date createdDate;
    private Restaurants RestauId;
    private User UserId;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, String body, Date createdDate, Restaurants RestauId, User UserId) {
        this.idCommentaire = idCommentaire;
        this.body = body;
        this.createdDate = createdDate;
        this.RestauId = RestauId;
        this.UserId = UserId;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Restaurants getRestauId() {
        return RestauId;
    }

    public void setRestauId(Restaurants RestauId) {
        this.RestauId = RestauId;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User UserId) {
        this.UserId = UserId;
    }

   
    
    
}
