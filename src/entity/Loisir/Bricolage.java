/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Loisir;

import java.util.Objects;


/**
 *
 * @author Pirateos
 */

public class Bricolage{
    private int id;
    private int client_bri_id;
    private String url_bri;
    private String tire_bri;
    private String description_bri;

    public Bricolage(int client_bri_id, String url_bri, String tire_bri, String description_bri) {
        this.client_bri_id = client_bri_id;
        this.url_bri = url_bri;
        this.tire_bri = tire_bri;
        this.description_bri = description_bri;
    }
      public Bricolage(String url_bri, String tire_bri, String description_bri) {
        this.url_bri = url_bri;
        this.tire_bri = tire_bri;
        this.description_bri = description_bri;
    }
  public Bricolage() {
        
       
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_bri_id() {
        return client_bri_id;
    }

    public void setClient_bri_id(int client_bri_id) {
        this.client_bri_id = client_bri_id;
    }

    public String getUrl_bri() {
        return url_bri;
    }

    public void setUrl_bri(String url_bri) {
        this.url_bri = url_bri;
    }

    public String getTire_bri() {
        return tire_bri;
    }

    public void setTire_bri(String tire_bri) {
        this.tire_bri = tire_bri;
    }

    public String getDescription_bri() {
        return description_bri;
    }

    public void setDescription_bri(String description_bri) {
        this.description_bri = description_bri;
    }

    @Override
    public String toString() {
        return "Bricolage{" + "id=" + id + ", client_bri_id=" + client_bri_id + ", url_bri=" + url_bri + ", tire_bri=" + tire_bri + ", description_bri=" + description_bri + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bricolage other = (Bricolage) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.client_bri_id != other.client_bri_id) {
            return false;
        }
        if (!Objects.equals(this.url_bri, other.url_bri)) {
            return false;
        }
        if (!Objects.equals(this.tire_bri, other.tire_bri)) {
            return false;
        }
        if (!Objects.equals(this.description_bri, other.description_bri)) {
            return false;
        }
        return true;
    }
    
           
              
          
}
