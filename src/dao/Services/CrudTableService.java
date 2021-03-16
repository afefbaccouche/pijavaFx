/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;

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
import javax.swing.JOptionPane;

/**
 *
 * @author amani
 */
public class CrudTableService implements IService <Service> {
     private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public void add(Service s) {
        String req="insert into Service(nom,Description) values('"+s.getNom()+"','"+s.getDescription()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    
     
    public void delete(Service s) {
           String req="delete from Service where id = '"+s.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer une service\n"+ex.getMessage());
        }    
    
    }
  
    
    
    public void update(Service s) {
   
       String req = "UPDATE Service SET id=?, nom=?, Description=? where id = '"+s.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
        ps.setInt(1, s.getId());
        ps.setString(2, s.getNom());
        ps.setString(3, s.getDescription());
        

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("Le Service a été modifier avec succès");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }        }
    
    
    
   

    public List getAll() {
 List<Service> list = new ArrayList<Service>();
        String req="select * from Service";
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String nom =rs.getString("nomService");
               String desc =rs.getString("Description");
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Service s = new Service( Id, nom ,desc);
               s.setId(Id);
               list.add(s);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list;   
    
    }

    @Override
    public Service getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
    
    public Service rechercherServiceByDescription(String Description) {
        Service service = new Service();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	Service where Description like '%"+Description+"%' ");
            while(res.next())
            {
                int id = res.getInt("id");
                String Nom =res.getString("nomService");
                String description=res.getString("Description"); 
                service.setId(id);
                service.setNom(Nom);
                service.setDescription(description);   
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        System.out.println(service);
        return service;
 
    }

    
    public Service rechercherServiceByID(int idService) {
        
        Service ser = new Service();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from Service where id="+idService);
            while(res.next())
            {
                int id = res.getInt("id");
                String nom=res.getString("nomService");
                String description=res.getString("Description"); 
                ser.setId(id);
                ser.setNom(nom);
                ser.setDescription(description);   
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        return ser;
    }
       
    }
    
    
    
    
    
    
