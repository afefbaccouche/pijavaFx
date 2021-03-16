/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Services;

import java.util.List;
import javafx.beans.property.StringProperty;

/**
 *
 * @author amani
 */
public class Societe {
    private int id;
    private String nom;
    private String tel;
    private String adress;
    private String image;  
    private Service service;
    private StringProperty specialIndicator;
    //List <DemandeService> ds ;



    public Societe() {
    }
    
    public Societe(String nom ,String tel,String adress,String image,Service service) {
        this.nom=nom ;
        this.tel=tel;
        this.adress=adress;
        this.image=image;
        this.service= service ;
    }

     public Societe(int id ,String nom ,String tel,String addresse,String image,Service service) {
        this.nom=nom ;
        this.tel=tel;
        this.adress=addresse;
        this.image=image;
        this.service=service ;
    }

    public Societe(String nom, String tel, String adresse, Service service) {
       this.nom=nom ;
        this.tel=tel;
        this.adress=adresse;
        
        this.service=service ;    }

    public Societe(String nom, String tel, String adresse) {
       this.nom=nom ;
        this.tel=tel;
        this.adress=adress;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

  

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Societe{" + "id=" + id + ", nom=" + nom + ", tel=" + tel + ", adress=" + adress + ", image=" + image + ", service=" + service + '}';
    }

    public StringProperty getSpecialIndicator() {
        return specialIndicator;
    }

    public void setSpecialIndicator(StringProperty specialIndicator) {
        this.specialIndicator = specialIndicator;
    }

   
    

  

   

   
}
