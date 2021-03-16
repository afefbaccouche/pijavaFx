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
public class DemandeService {
    
    int id ; 
    String description ;
    String datee ;
    Societe societes ;
    User users ;

    public DemandeService(int id, String description, String datee, Societe societes, User users) {
        this.id = id;
        this.description = description;
        this.datee = datee;
        this.societes = societes;
        this.users = users;
    }

     public DemandeService( String description, String datee, Societe societes, User users) {
      
        this.description = description;
        this.datee = datee;
        this.societes = societes;
        this.users = users;
    }
    public DemandeService() {
    }

    public DemandeService(int id, String description, String datee, Societe societes) {
        this.id = id;
        this.description = description;
        this.datee = datee;
        this.societes = societes;
    }

   

   

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public Societe getSocietes() {
        return societes;
    }

    public void setSocietes(Societe societes) {
        this.societes = societes;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DemandeService{" + "id=" + id + ", description=" + description + ", datee=" + datee + ", societes=" + societes + ", users=" + users + '}';
    }

  

   
    
   
}
