/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author esprit
 */
public class Centre {

    private int id;
    private String nom;
    private String description;
    private String image;
    private User manager;
    private float prix;
    private int nbrPlace;
    private String address;
    private int telephone;

    private String enPromotion = "non";

    public Centre() {
    }

    public Centre(int id, String nom, String description, String image, User manager) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.manager = manager;
    }

    public Centre(String nom, String description, String image, User manager) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Centre(String nom, String description, String image, User manager, float prix, int nbrPlace, String address, int telephone) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.manager = manager;
        this.prix = prix;
        this.nbrPlace = nbrPlace;
        this.address = address;
        this.telephone = telephone;
    }

    public String getEnPromotion() {
        return enPromotion;
    }

    public void setEnPromotion(String enPromotion) {
        this.enPromotion = enPromotion;
    }

    @Override
    public String toString() {
        return nom;
    }

}
