/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Services;

import entity.User;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author amani
 */
public class RendezVous {
    private int id ;
    private String description ;
    private Time time ;
    private Date date;
    private String reponseAvocat;
    private User age ;
    private User avocat ;
    private int etat;

    public RendezVous(int id, String description, Time time, Date date, String reponseAvocat, User age, User avocat) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.date = date;
        this.reponseAvocat = reponseAvocat;
        this.age = age;
        this.avocat = avocat;
    }

    public RendezVous() {

    }

    public RendezVous(int id, String description, Time time, Date date, String reponseAvocat, User avocat) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.date = date;
        this.reponseAvocat = reponseAvocat;
        this.avocat = avocat;
    }

    public RendezVous(int id, String description, Time time, Date date, String reponseAvocat, User avocat, int etat) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.date = date;
        this.reponseAvocat = reponseAvocat;
        this.avocat = avocat;
        this.etat = etat;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReponseAvocat() {
        return reponseAvocat;
    }

    public void setReponseAvocat(String reponseAvocat) {
        this.reponseAvocat = reponseAvocat;
    }

    public User getAge() {
        return age;
    }

    public void setAge(User age) {
        this.age = age;
    }

    public User getAvocat() {
        return avocat;
    }

    public void setAvocat(User avocat) {
        this.avocat = avocat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", description=" + description + ", time=" + time + ", date=" + date + ", reponseAvocat=" + reponseAvocat + ", age=" + age + ", avocat=" + avocat + ", etat=" + etat + '}';
    }

   

    

 

    
    
}
