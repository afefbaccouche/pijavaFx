/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;


import entity.Services.Service;
import entity.Services.Societe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author amani
 */
public class CrudTableSociete  implements ISociete<Societe>{
    private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public void add(Societe soc) {
        
        System.out.println(soc+"ajout");
         String req="insert into Societe values("+null+",'"+soc.getNom()+"','"+
                 soc.getTel()+"','"+soc.getAdress()+"','"+soc.getImage()+"','"+soc.getService().getId()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
    }

    
    public void delete(Societe soc) {
      String req="delete from Societe where id = '"+soc.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer une Societe\n"+ex.getMessage());
        }  
    }
    

  
    public void update(Societe soc) {
        
        
       String req = "UPDATE Societe SET id=?, nom=?, tel=? ,adress=? ,image=?,Service_id=?  where id = '"+soc.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
        ps.setInt(1, soc.getId());
        ps.setString(2, soc.getNom());
        ps.setString(3, soc.getTel());
        ps.setString(4, soc.getAdress());
        ps.setString(5, soc.getImage());
        ps.setInt(6, soc.getService().getId());
        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("La Societe a été modifier avec succès");
          }
          } catch (SQLException e) {
              System.out.println("ya une erreur");
                     System.out.println("Erreur"+e.getMessage());
 
          }    
       
    }
    
    
   @Override
    public List<Societe> getAll() {

     List<Societe> list = new ArrayList<Societe>();
        String req="select * from societe";
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String nom =rs.getString("nom");
               String tel =rs.getString("tel");
               String adresse =rs.getString("adress");
               String image = rs.getString("image");
             
              int idSer=rs.getInt("Service_id");
              
                CrudTableService crudtableService = new CrudTableService();
               Service service = crudtableService.rechercherServiceByID(idSer);
               
               //Societe soc = new Societe( nom, tel ,adresse,image,service);
               Societe soc = new Societe( nom, tel ,adresse,service);
               soc.setImage(image);

               soc.setId(Id);
               list.add(soc);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list; 
    }

    
    
       @Override
    public List<Societe> getById(int id) {

         List<Societe> list = new ArrayList<Societe>();
        String req="select * from societe where Service_id="+id;
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String nom =rs.getString("nom");
               String tel =rs.getString("tel");
                String image = rs.getString("image");
               String adresse =rs.getString("adress");
              //  System.out.println(rs.getString("adress"));
              int idSer=rs.getInt("Service_id");
              
                CrudTableService crudtableService = new CrudTableService();
               Service service = crudtableService.rechercherServiceByID(idSer);
               Societe soc = new Societe( nom, tel ,adresse,service);
               soc.setImage(image);
               soc.setId(Id);
               list.add(soc);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list; 
        
        
        
    }    

    
    // ¨POUR DEMANDE SERVICE
    
    public Societe  rechercherSocieteByID(int ids){
 
        String req="select * from societe where id ="+ids;
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String nom =rs.getString("nom");
               String tel =rs.getString("tel");
               String adresse =rs.getString("adress");
               String image = rs.getString("image");

               Societe soc = new Societe( nom, tel ,adresse,null);
               soc.setImage(image);

               soc.setId(Id);
               return soc;          
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }

               return null;
    }
    
    }
   
    
    
