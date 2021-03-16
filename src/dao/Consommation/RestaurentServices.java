/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;
import ConnectionBaseDonn.connect;
import entity.Consommation.Commentaire;
import entity.Consommation.Restaurants;

;
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
import javafx.scene.control.Button;

/**
 *
 * @author soumaya ch
 */
public class RestaurentServices implements IService<Restaurants> {
    
    private Connection connection;
    private static RestaurentServices instance;
    private Statement ste;
    private ResultSet rs;
    private PreparedStatement pst;
     
//     byte [] photo=null;
//    String filename=null;
     
    RestaurentServices() {
       connection =connect.getInstance().getCnx(); 
       
    }
    
    
      public static RestaurentServices getInstance(){
        if(instance==null) 
            instance=new RestaurentServices();
        return instance;
    }
    
    @Override
    public void add(Restaurants t) {

//         String req="insert into Restaurant (nomRestaurant,adressRestaurant,numRestaurant,emailRestaurant) values ('"+t.getNomRestaurant()+"','"+t.getAdressRestaurant()+"','"+t.getNumRestaurant()+"','"+t.getEmailRestaurant()+"')";
//       try {
//            ste=connection.createStatement();
//             ste.executeUpdate(req);
//        } catch (SQLException ex) {
//            Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
  String req="insert into Restaurant "
          + "(nomRestaurant,adressRestaurant,numRestaurant,emailRestaurant,Image,Type)"
          + " values(?,?,?,?,?,?)";
        try{
              
           
     pst=connection.prepareStatement(req); 
       
        pst.setString(1,t.getNomRestaurant());
        pst.setString(2,t.getAdressRestaurant());
        pst.setString(3,t.getNumRestaurant());
        pst.setString(4,t.getEmailRestaurant());
       pst.setString(5,t.getImagRestau());
        pst.setString(6,t.getType());
        pst.executeUpdate();
          
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public void delete(Restaurants t) {
  String req="delete from Restaurant WHERE idRestaurant =?";
  
  try{
              
           
     pst=connection.prepareStatement(req); 
       
        pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdRestaurant());
//     
//       
        pst.executeUpdate();
          
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
  
        }

    @Override
    public void update(Restaurants t) {
                
  String req="update Restaurant SET idRestaurant = ?,  nomRestaurant = ?, adressRestaurant = ?,numRestaurant = ? , "
          + "emailRestaurant = ? Type = ? WHERE idRestaurant = '" +t.getIdRestaurant()+"'";
//          String req="UPDATE Restaurant"
//                  + " (idRestaurant,nomRestaurant,adressRestaurant,numRestaurant,emailRestaurant)"
//                  + " values(?,?,?,?,?)"
//                  + " WHERE idRestaurant=?  ";

         try{
                  
     pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdRestaurant());
        pst.setString(2,t.getNomRestaurant());
        pst.setString(3,t.getAdressRestaurant());
        pst.setString(4,t.getNumRestaurant());
        pst.setString(5,t.getEmailRestaurant());
          pst.setString(6,t.getType());
     //   pst.setInt(5,t.getIdRestaurant());

        pst.executeUpdate();
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public ObservableList<Restaurants> getAll() {
  String req="select * from Restaurant"; 
        ObservableList<Restaurants> list=FXCollections.observableArrayList(); 
        try{
        
          ste =connection.createStatement();
          rs=ste.executeQuery(req);
          

             while(rs.next()){
                Restaurants p=new Restaurants();
              p.setIdRestaurant(rs.getInt(1));
                p.setNomRestaurant(rs.getString(2));
                p.setAdressRestaurant(rs.getString(3));
                p.setNumRestaurant(rs.getString(4));
                p.setEmailRestaurant(rs.getString(5));
                p.setImagRestau(rs.getString(6));
                p.setType(rs.getString(7));
                list.add(p);
            }
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return  list;
    }

    @Override
    public boolean Chercher(Restaurants t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
     public Restaurants rechercherRestauByNom(String nom) {
        Restaurants restaurant = new Restaurants();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	Restaurant where nomRestaurant like '%"+nom+"%' ");
            while(res.next())
            {
               int id = res.getInt("idRestaurant");
                String Nom =res.getString("nomRestaurant");
             
               restaurant.setIdRestaurant(id);
                restaurant.setNomRestaurant(Nom);
               
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        System.out.println(restaurant);
        return restaurant;
 
    }
     
     
     public Restaurants rechercherRestauByID(int idRestaurant) {
        
        Restaurants restaurant = new Restaurants();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res=ps.executeQuery("select * from 	Restaurant where idRestaurant="+idRestaurant);
            while(res.next())
            {
                 int id = res.getInt("idRestaurant");
                String Nom =res.getString("nomRestaurant");
             
                  restaurant.setIdRestaurant(id);
                restaurant.setNomRestaurant(Nom);
                  
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        return restaurant;
}
    
     
     
     
        
//    public ObservableList<Commentaire> getAllCom() {
//  String req="select * from commentaire"; 
//  // UserDao userdao = new UserDao();
//        ObservableList<Commentaire> list=FXCollections.observableArrayList(); 
//        try{
//        
//          ste =connection.createStatement();
//          rs=ste.executeQuery(req);
//            RestaurentServices crudtableProd= new RestaurentServices();
//            
//             int idProd=rs.getInt("RestauId");
//              
//               Restaurants restau = crudtableProd.rechercherRestauByID(idProd);
//                
//
//             while(rs.next()){
//                Commentaire p=new Commentaire();
//                
//                p.setIdCommentaire(rs.getInt(1));
//                p.setBody(rs.getString(2));
//                p.setCreatedDate(rs.getDate(3));
//                p.getRestauId(restau);
//                p.getUserId(rs.getInt(5));
//             
//                list.add(p);
//            }
//        }
//        catch(SQLException ex){
//           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
//        }
//        
//        return  list;
//    }
     
     
     
     
      public ObservableList<Restaurants> getAllByType(String type) {
        
    
  String req="select * from Restaurant where Type like '%"+type+"%'"; 
        ObservableList<Restaurants> list=FXCollections.observableArrayList(); 
        try{
        
          ste =connection.createStatement();
          rs=ste.executeQuery(req);
          

             while(rs.next()){
                Restaurants p=new Restaurants();
              p.setIdRestaurant(rs.getInt(1));
                p.setNomRestaurant(rs.getString(2));
                p.setAdressRestaurant(rs.getString(3));
                p.setNumRestaurant(rs.getString(4));
                p.setEmailRestaurant(rs.getString(5));
               p.setType(rs.getString(6));
                list.add(p);
            }
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return  list;
    }
}
