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
public class RatingEntity {
    
     private int idRating;
    private Number rating;
    private Date dateRating;
    private Restaurants RestauIdR;
    private User UserIdR;

    public RatingEntity() {
    }

    public RatingEntity(int idRating, Number rating, Date dateRating, Restaurants RestauIdR, User UserIdR) {
        this.idRating = idRating;
        this.rating = rating;
        this.dateRating = dateRating;
        this.RestauIdR = RestauIdR;
        this.UserIdR = UserIdR;
    }

    
    
    
    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public Date getDateRating() {
        return dateRating;
    }

    public void setDateRating(Date dateRating) {
        this.dateRating = dateRating;
    }

    public Restaurants getRestauIdR() {
        return RestauIdR;
    }

    public void setRestauIdR(Restaurants RestauIdR) {
        this.RestauIdR = RestauIdR;
    }

    public User getUserIdR() {
        return UserIdR;
    }

    public void setUserIdR(User UserIdR) {
        this.UserIdR = UserIdR;
    }

    
    
    
    
}
