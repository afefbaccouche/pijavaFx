/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;

import entity.Association;
import entity.Services.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author fahdj
 */
public class ServiceAssociation implements IService<Association> {
private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    @Override
    public void add(Association t) {
        String req="INSERT INTO `association`(`name_assoc`, `nbre_membre`, `description_assoc`, `type_assoc`) VALUES ('"+t.getName()+"', '"+t.getNbr()+"', '"+t.getDescription()+"', '"+t.getType()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Association t) {
        String req="delete from Association where id = '"+t.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer une Association\n"+ex.getMessage());
        }  
    }

    @Override
    public void update(Association t) {
         String req = "UPDATE `association` SET `name_assoc`=?,`nbre_membre`=?,`description_assoc`=?,`type_assoc`=? WHERE id = '"+t.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
        
        ps.setString(1, t.getName());
        ps.setInt(2, t.getNbr());
        ps.setString(3, t.getDescription());
        ps.setString(4, t.getType());
        

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("Le Service a été modifie avec succès");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }    
    }
    
     public void decrementd(Association t) {
         String req = "UPDATE `association` SET `nbre_membre`=? WHERE id = '"+t.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
       
        ps.setInt(1, t.getNbr());
   
        

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("participe!!!");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }    
    }
    
    
     public ObservableList<Association> listerRecherche(String recherche) {
       ObservableList <Association> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM Association WHERE id like '%" + recherche + "%' or name_assoc  like '%" + recherche + "%' or nbre_membre  like '%" + recherche + "%'or description_assoc  like '%" + recherche + "%'or type_assoc  like '%" + recherche + "%'";
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String name =rs.getString("name_assoc");
               int nbr= rs.getInt("nbre_membre");
               String description = rs.getString("description_assoc");
               String type = rs.getString("type_assoc");
               
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Association A = new Association( Id,name,nbr,description,type);
             
               list.add(A);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list;   
    }  
    

   



@Override
    public ObservableList<Association> getAll() {
       ObservableList <Association> list = FXCollections.observableArrayList();
        String req="select * from Association";
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String name =rs.getString("name_assoc");
               int nbr= rs.getInt("nbre_membre");
               String description = rs.getString("description_assoc");
               String type = rs.getString("type_assoc");
               
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Association A = new Association( Id,name,nbr,description,type);
             
               list.add(A);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list;   
    }

    
    
    
    
   
    
    
    public String afficher(Association C ,int idc,int client)
    {  
        String msg="";
        try {
            Statement ste=connection.createStatement();
            String requete ="select * from association where id='"+ idc +"'" ;
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next())
            {
                msg = "\r\n votre id est id : "+" :  " +client
                        +"\r\n vous avez participer aux association de " +"name_association :  "+rs.getString(3)
                        +"\r\n" +"avec :  "+rs.getString(4)
                        +"\r\n" +"vous avez comme besoin :  "+rs.getString(5)
                       
                        +"\r\n"+"Remerciement : "+"\r\n";
            }    
        }  catch (SQLException ex) {   
             System.out.println("Pas de pdf" + ex.getMessage());
        } 
        return msg ;
    } 
    
    
    
    
    
    @Override
    public Association getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
