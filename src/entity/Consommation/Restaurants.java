/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Consommation;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.scene.control.Button;

/**
 *
 * @author soumaya ch
 */
public class Restaurants {
    
    private int idRestaurant;
    private String nomRestaurant;
    private String adressRestaurant;
    private String numRestaurant;
    private String emailRestaurant;
    private String imagRestau;
    private String Type;

    
    

    public Restaurants() {
    }


    
    
    public Restaurants(int idRestaurant, String nomRestaurant, String adressRestaurant,
            String numRestaurant, String emailRestaurant) {
        this.idRestaurant = idRestaurant;
        this.nomRestaurant = nomRestaurant;
        this.adressRestaurant = adressRestaurant;
        this.numRestaurant = numRestaurant;
        this.emailRestaurant = emailRestaurant;
        
        
        
    }

    public Restaurants(int idRestaurant, String nomRestaurant, String adressRestaurant, String numRestaurant, String emailRestaurant, String imagRestau) {
        this.idRestaurant = idRestaurant;
        this.nomRestaurant = nomRestaurant;
        this.adressRestaurant = adressRestaurant;
        this.numRestaurant = numRestaurant;
        this.emailRestaurant = emailRestaurant;
        this.imagRestau = imagRestau;
    }

    public Restaurants(int idRestaurant, String nomRestaurant, String adressRestaurant, String numRestaurant, String emailRestaurant, String imagRestau, String Type) {
        this.idRestaurant = idRestaurant;
        this.nomRestaurant = nomRestaurant;
        this.adressRestaurant = adressRestaurant;
        this.numRestaurant = numRestaurant;
        this.emailRestaurant = emailRestaurant;
        this.imagRestau = imagRestau;
        this.Type = Type;
    }

    
    
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    
    
   
    
   
    
    
    
    public String getImagRestau() {
        return imagRestau;
    }

    public void setImagRestau(String imagRestau) {
        this.imagRestau = imagRestau;
    }

    
    
    
   
    
  

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public String getAdressRestaurant() {
        return adressRestaurant;
    }

    public void setAdressRestaurant(String adressRestaurant) {
        this.adressRestaurant = adressRestaurant;
    }

    public String getNumRestaurant() {
        return numRestaurant;
    }

    public void setNumRestaurant(String numRestaurant) {
        this.numRestaurant = numRestaurant;
    }

    public String getEmailRestaurant() {
        return emailRestaurant;
    }

    public void setEmailRestaurant(String emailRestaurant) {
        this.emailRestaurant = emailRestaurant;
    }
    
    
    
    private final StringProperty string = new SimpleStringProperty();

    public String getString() {
        return string.get();
    }

    public void setString(String value) {
        string.set(value);
    }

    public StringProperty stringProperty() {
        return string;
    }

    
    
    @Override
    public String toString() {
        return "Restaurants{" + "idRestaurant=" + idRestaurant + 
                ", nomRestaurant=" + nomRestaurant + ", adressRestaurant=" + adressRestaurant + 
                ", numRestaurant=" + numRestaurant + ", emailRestaurant=" + emailRestaurant + '}';
    }
    
    
    
    
    
    
}
