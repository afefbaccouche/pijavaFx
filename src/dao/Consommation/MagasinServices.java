/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import ConnectionBaseDonn.connect;
import entity.Consommation.Magasins;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author soumaya ch
 */
public class MagasinServices implements IService<Magasins>{
    
     private Connection connection;
    private static MagasinServices instance;
    private Statement ste;
    private ResultSet rs;
    private PreparedStatement pst;
    
      
    public MagasinServices() {
       connection =connect.getInstance().getCnx(); 
       
    }
    
    
      public static MagasinServices getInstance(){
        if(instance==null) 
            instance=new MagasinServices();
        return instance;
    }

    @Override
    public void add(Magasins t) {
        String req="insert into magasin"
          + " (nomMagasin,adressMagasin,numMagasin,emailMagasin,imageMagasin)"
          + " values(?,?,?,?,?)";
        try{
              
           
     pst=connection.prepareStatement(req); 
       
        pst.setString(1,t.getNomMagasin());
        pst.setString(2,t.getAdressMagasin());
        pst.setString(3,t.getNumMagasin());
        pst.setString(4,t.getEmailMagasin());
       pst.setString(5,t.getImageMagasin());
        pst.executeUpdate();
          
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public void delete(Magasins t) {
         String req="delete from magasin WHERE idMagasin =?";
  
  try{
              
           
     pst=connection.prepareStatement(req); 
       
        pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdMagasin());
     
        pst.executeUpdate();
          
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
  
    }

    @Override
    public void update(Magasins t) {
         String req="update magasin SET idMagasin = ?,  nomMagasin = ?, adressMagasin = ?,numMagasin = ? , "
          + "emailMagasin = ? WHERE idMagasin = '" +t.getIdMagasin()+"'";
//          String req="UPDATE Restaurant"
//                  + " (idRestaurant,nomRestaurant,adressRestaurant,numRestaurant,emailRestaurant)"
//                  + " values(?,?,?,?,?)"
//                  + " WHERE idRestaurant=?  ";

         try{
                  
     pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdMagasin());
        pst.setString(2,t.getNomMagasin());
        pst.setString(3,t.getAdressMagasin());
        pst.setString(4,t.getNumMagasin());
        pst.setString(5,t.getEmailMagasin());
     //   pst.setInt(5,t.getIdRestaurant());

        pst.executeUpdate();
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
     public ObservableList<Magasins> getAll() {
  String req="select * from magasin"; 
        ObservableList<Magasins> list=FXCollections.observableArrayList(); 
        try{
        
          ste =connection.createStatement();
          rs=ste.executeQuery(req);
       
             while(rs.next()){
                Magasins M=new Magasins();
                M.setIdMagasin(rs.getInt(1));
                M.setNomMagasin(rs.getString(2));
                M.setAdressMagasin(rs.getString(3));
                M.setNumMagasin(rs.getString(4));
                M.setEmailMagasin(rs.getString(5));
                M.setImageMagasin(rs.getString(6));
                list.add(M);
            }
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return  list;
    }

    @Override
    public boolean Chercher(Magasins t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Magasins rechercherMagasinByNom(String nom) {
        Magasins magasin = new Magasins();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	magasin where nomMagasin like '%"+nom+"%' ");
            while(res.next())
            {
               int id = res.getInt("idMagasin");
                String Nom =res.getString("nomMagasin");
             
               magasin.setIdMagasin(id);
                magasin.setNomMagasin(Nom);
               
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        System.out.println(magasin);
        return magasin;
 
    }
    
    public Magasins rechercherMagasinByID(int idMagasin) {
        
        Magasins magasin = new Magasins();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	magasin where idMagasin="+idMagasin);
            while(res.next())
            {
                 int id = res.getInt("idMagasin");
                String Nom =res.getString("nomMagasin");
             
               magasin.setIdMagasin(id);
                magasin.setNomMagasin(Nom);
                  
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        return magasin;
}
}
