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
public class like__societe {
    private int id ; 
    private User user ; 
    private Societe societe;

    public like__societe() {
    }

    public like__societe(User user, Societe societe) {
        this.user = user;
        this.societe = societe;
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

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    
    
    
    
    
    
    
    
}
