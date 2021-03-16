/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import ConnectionBaseDonn.connect;
import entity.Consommation.Magasins;
import entity.Consommation.Produit;
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
public class ProduitServices implements IService<Produit> {

     private Connection connection;
    private static ProduitServices instance;
    private Statement ste;
    private ResultSet rs;
    private PreparedStatement pst;
    
      
    public ProduitServices() {
       connection =connect.getInstance().getCnx(); 
       
    }
    
    
      public static ProduitServices getInstance(){
        if(instance==null) 
            instance=new ProduitServices();
        return instance;
    }
    
    @Override
    public void add(Produit t) {
  String req="insert into Produit"
          + " (nomProduit,prixProduit,quantiteProduit,MagProduit,typeProduit,imageProduit)"
          + " values(?,?,?,?,?,?)";
        try{
              Magasins m= new Magasins();
           int y=m.getIdMagasin();
     pst=connection.prepareStatement(req); 
       
        pst.setString(1,t.getNomProduit());
        pst.setFloat(2,t.getPrixProduit());
         pst.setInt(3,t.getQuantiteProduit());
         
        pst.setInt(4,t.getMagProduit().getIdMagasin());
         pst.setString(5,t.getTypeProduit());
       
       pst.setString(6,t.getImageProduit());
        pst.executeUpdate();
          
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }    }

    @Override
    public void delete(Produit t) {
  String req="delete from Produit WHERE idProduit =?";
  
  try{
              
           
     pst=connection.prepareStatement(req); 
       
        pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdProduit());
     
        pst.executeUpdate();
          
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public void update(Produit t) {
String req="update Produit SET idProduit = ?,  nomProduit = ?, prixProduit = ?,MagProduit = ? , "
          + "quantiteProduit = ?,typeProduit = ?"
        + " WHERE idProduit = '" +t.getIdProduit()+"'";
//        

         try{
                  
     pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdProduit());
        pst.setString(2,t.getNomProduit());
        pst.setFloat(3,t.getPrixProduit());
        pst.setInt(4,t.getQuantiteProduit());
        pst.setInt(5,t.getMagProduit().getIdMagasin());
     pst.setString(6,t.getTypeProduit());

       pst.executeUpdate();
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public ObservableList<Produit> getAll() {
        String req="select * from produit"; 
        ObservableList<Produit> list=FXCollections.observableArrayList(); 
        try{
     
       // MagasinServices magS= new MagasinServices();
          ste =connection.createStatement();
          rs=ste.executeQuery(req);
    //    int idMag=rs.getInt("MagProduit");
             while(rs.next()){
                Produit M=new Produit();
                
                int idMag=rs.getInt("MagProduit");
              
                MagasinServices crudtableMag = new MagasinServices();
               Magasins magasin = crudtableMag.rechercherMagasinByID(idMag);
                
                M.setIdProduit(rs.getInt(1));
                M.setNomProduit(rs.getString(2));
                M.setPrixProduit(rs.getFloat(3));
                M.setQuantiteProduit(rs.getInt(4));
                M.setMagProduit(magasin);
                 M.setTypeProduit(rs.getString(6));
                  M.setImageProduit(rs.getString(7));
               
                 
                 
              
               
                list.add(M);
            }
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return  list;
    }

    @Override
    public boolean Chercher(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
     public ObservableList<Produit> getAllByMag(int id) {
         
        String req="select * from produit where MagProduit="+id; 
        ObservableList<Produit> list=FXCollections.observableArrayList(); 
        try{
     
       // MagasinServices magS= new MagasinServices();
          ste =connection.createStatement();
          rs=ste.executeQuery(req);
    //    int idMag=rs.getInt("MagProduit");
             while(rs.next()){
                Produit M=new Produit();
                
                int idMag=rs.getInt("MagProduit");
              
                MagasinServices crudtableMag = new MagasinServices();
               Magasins magasin = crudtableMag.rechercherMagasinByID(idMag);
                
                M.setIdProduit(rs.getInt(1));
                M.setNomProduit(rs.getString(2));
                M.setPrixProduit(rs.getFloat(3));
                M.setQuantiteProduit(rs.getInt(4));
                M.setMagProduit(magasin);
                 M.setTypeProduit(rs.getString(6));
               
                 
                 
              
               
                list.add(M);
            }
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return  list;
    }
    
    
}
