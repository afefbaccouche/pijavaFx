/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Services;

import java.sql.Date;

/**
 *
 * @author amani
 */
public class Service {
    
    int id ;
    String  nom ;
    String Description ;
  
   // List<Societe> societes ;

    
    public Service(int id, String nom, String Description) {
        this.id = id;
        this.nom = nom;
        this.Description = Description;
     
        
    }
       public Service() {
        
          }
   
      public Service( String nom, String Description) {
       
        this.nom = nom;
        this.Description = Description;
    } 

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom=" + nom + ", Description=" + Description + '}';
    }

    public Service(int Id, String description, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

  /*  public List<Societe> getSocietes() {
        return societes;
    }

    public void setSocietes(List<Societe> societes) {
        this.societes = societes;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom=" + nom + ", Description=" + Description + ", societes=" + societes + '}';
    }

    */

  
    
    
    
    
    
    
    
}
