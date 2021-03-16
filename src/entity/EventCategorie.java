/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Baklouti
 */
public class EventCategorie {
    private int id;
    private String nomCategorie;
    private User user;
    

    public EventCategorie() {
    }

    public EventCategorie(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        
    }

    public EventCategorie(String nomCategorie, User user) {
        this.nomCategorie = nomCategorie;
        this.user = user;
    }
    
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nomCategorie=" + nomCategorie + '}';
    }
    
    
    
}
