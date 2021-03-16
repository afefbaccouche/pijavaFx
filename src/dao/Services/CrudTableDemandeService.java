/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;

import entity.Services.DemandeService;
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
import dao.Services.CrudTableSociete;
import entity.Services.DemandeService;
import session.Session;


/**
 *
 * @author amani
 */
public class CrudTableDemandeService  implements IDemandeService<DemandeService>{

private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    static int  socId;
    public void add(DemandeService d ) {
         
       try {
            
         String req="insert into demandeservice (description,date_demande,Societe_id,User_id)values('"
                 +d.getDescription()+"','"
                 +d.getDatee()+"','"
                 +d.getSocietes().getId()+"','"
                 +d.getUsers().getIdUser()+"')";
        
               
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }

    @Override
    public void delete(DemandeService t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(DemandeService t) {
         
       String req = "UPDATE demandeservice SET  description=?, date_demande=?  where id = '"+t.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
       
        ps.setString(1, t.getDescription());
        ps.setString(2, t.getDatee());
       
        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("La demande a été modifier avec succès");
          }
          } catch (SQLException e) {
              System.out.println("ya une erreur");
                     System.out.println("Erreur"+e.getMessage());
 
          }    
    }

    @Override
    public List<DemandeService> getAll() {

        List<DemandeService> list = new ArrayList<DemandeService>();
        String req="select * from demandeservice where User_id="+Session.getConnectedUser().getIdUser();
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String desc =rs.getString("description");
               String date =rs.getString("date_demande");
               socId=rs.getInt("Societe_id");
                
               CrudTableSociete CTS = new CrudTableSociete();
               Societe societe = CTS.rechercherSocieteByID(socId);
                
               DemandeService demande = new DemandeService(Id, desc ,date,societe);
               demande.setId(Id);
               list.add(demande);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list; 


    }

    
    
    
    
    
    
    @Override
    public DemandeService getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
  


   
    
    
    
    
}
