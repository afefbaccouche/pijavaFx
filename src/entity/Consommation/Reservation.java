/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Consommation;

import entity.User;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author soumaya ch
 */
public class Reservation {

    private int idReserv;
    private String aunomdeReserv;
    private int nombreReserv;
    private String descriptionReserv;
    private Timestamp dateReserv;

    ////clé etranger restaurant
    private Restaurants idrestaurant;

    /// clé etranger user
    private User iduser;

    public Reservation() {
    }

    public Reservation(int idReserv, String aunomdeReserv, int nombreReserv, String descriptionReserv, Timestamp dateReserv, Restaurants idrestaurant, User iduser) {
        this.idReserv = idReserv;
        this.aunomdeReserv = aunomdeReserv;
        this.nombreReserv = nombreReserv;
        this.descriptionReserv = descriptionReserv;
        this.dateReserv = dateReserv;
        this.idrestaurant = idrestaurant;
        this.iduser = iduser;
    }
    
    

    public int getIdReserv() {
        return idReserv;
    }

    public void setIdReserv(int idReserv) {
        this.idReserv = idReserv;
    }

    public String getAunomdeReserv() {
        return aunomdeReserv;
    }

    public void setAunomdeReserv(String aunomdeReserv) {
        this.aunomdeReserv = aunomdeReserv;
    }

    public int getNombreReserv() {
        return nombreReserv;
    }

    public void setNombreReserv(int nombreReserv) {
        this.nombreReserv = nombreReserv;
    }

    public String getDescriptionReserv() {
        return descriptionReserv;
    }

    public void setDescriptionReserv(String descriptionReserv) {
        this.descriptionReserv = descriptionReserv;
    }

    public Timestamp getDateReserv() {
        return dateReserv;
    }

    public void setDateReserv(Timestamp dateReserv) {
        this.dateReserv = dateReserv;
    }

    public Restaurants getIdrestaurant() {
        return idrestaurant;
    }

    public void setIdrestaurant(Restaurants idrestaurant) {
        this.idrestaurant = idrestaurant;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    
}
