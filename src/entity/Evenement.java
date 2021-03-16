/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import entity.User;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Baklouti
 */
public class Evenement {
    private int id;
    private EventCategorie eventCategorie;
    private User user;
    private SimpleStringProperty nomEvent;
    private SimpleStringProperty description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private SimpleStringProperty adresse;
    private double prix;
    private SimpleStringProperty image;

    public Evenement() {
    }

    public Evenement(EventCategorie eventCategorie, User user, String nomEvent, String description, LocalDate dateDebut, LocalDate dateFin, LocalTime heureDebut, LocalTime heureFin, String adresse, double prix, String image) {
        this.eventCategorie = eventCategorie;
        this.user = user;
        this.nomEvent = new SimpleStringProperty(nomEvent);
        this.description = new SimpleStringProperty(description);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.adresse = new SimpleStringProperty(adresse);
        this.prix = prix;
        this.image = new SimpleStringProperty(image);
    }

    public Evenement(int id, EventCategorie eventCategorie, User user, String nomEvent, String description, LocalDate dateDebut, LocalDate dateFin, LocalTime heureDebut, LocalTime heureFin, String adresse, double prix, String image) {
        this.id = id;
        this.eventCategorie = eventCategorie;
        this.user = user;
        this.nomEvent = new SimpleStringProperty(nomEvent);
        this.description = new SimpleStringProperty(description);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.adresse = new SimpleStringProperty(adresse);
        this.prix = prix;
        this.image = new SimpleStringProperty(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventCategorie getEventCategorie() {
        return eventCategorie;
    }

    public void setEventCategorie(EventCategorie eventCategorie) {
        this.eventCategorie = eventCategorie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNomEvent() {
        return nomEvent.get();
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = new SimpleStringProperty(nomEvent);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse = new SimpleStringProperty(adresse);
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        this.image = new SimpleStringProperty(image);
    }
    
    public SimpleStringProperty getNomEventProperty(){
        return nomEvent;
    }
    public SimpleStringProperty getAdresseProperty(){
        return adresse;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getImageProperty(){
        return image;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", eventCategorie=" + eventCategorie + ", user=" + user + ", nomEvent=" + nomEvent + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", adresse=" + adresse + ", prix=" + prix + ", image=" + image + '}';
    }
    
    
    
    
}
