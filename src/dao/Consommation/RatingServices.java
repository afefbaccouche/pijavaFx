/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import ConnectionBaseDonn.connect;
import dao.UserDao;

import entity.Consommation.RatingEntity;
import entity.Consommation.Restaurants;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author soumaya ch
 */
public class RatingServices implements IService<RatingEntity>{

    
      private Connection connection;
    private static RatingServices instance;
    private Statement ste;
    private ResultSet rs;
    private PreparedStatement pst;
     
//     byte [] photo=null;
//    String filename=null;
     
    public RatingServices() {
       connection =connect.getInstance().getCnx(); 
       
    }
    
    
      public static RatingServices getInstance(){
        if(instance==null) 
            instance=new RatingServices();
        return instance;
    }
    


    @Override
    public void add(RatingEntity t) {
String pattern = "yy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
//
        String req = "INSERT INTO rating (rating,dateRating,RestauIdR,UserIdR)"
                + " values(?,?,?,?)";
                
//        
//            
        System.out.println(req);
        System.out.println("rating inserted !!");
        try {
    
            pst=connection.prepareStatement(req); 
       
        pst.setDouble(1 , (double) t.getRating());
        pst.setString(2,date);
        pst.setInt(3,t.getRestauIdR().getIdRestaurant());
        pst.setInt(4,t.getUserIdR().getIdUser());
       
        pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public void delete(RatingEntity t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(RatingEntity t) {
        
        String pattern = "yy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        
String req="update rating SET idRating = ?,  rating = ?, dateRating = ?,RestauIdR = ? , "
          + "UserIdR = ? WHERE idRating = '" +t.getIdRating()+"'";
//         

         try{
                  
     pst=connection.prepareStatement(req); 
       pst.setInt(1,t.getIdRating());
        pst.setFloat(2, (float) t.getRating());
        pst.setString(3,date);
        pst.setInt(4,t.getRestauIdR().getIdRestaurant());
        pst.setInt(5,t.getUserIdR().getIdUser());
 

        pst.executeUpdate();
       
        }
        catch(SQLException ex){
           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
        }    }

    @Override
    public ObservableList<RatingEntity> getAll() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//String req="select * from commentaire"; 
//  // UserDao userdao = new UserDao();
//        ObservableList<RatingEntity> list=FXCollections.observableArrayList(); 
//        try{
//        
//          ste =connection.createStatement();
//          rs=ste.executeQuery(req);
//          
//            RestaurentServices crudtableProd= new RestaurentServices();
//                  UserDao userdao = new UserDao();
//                  
//                      int iduser=rs.getInt("UserId");
//           int idProd=rs.getInt("RestauId");
//           
//               Restaurants restau = crudtableProd.rechercherRestauByID(idProd);
//           
//               User user=userdao.displayById(iduser);
//
//             while(rs.next()){
//                RatingEntity p=new RatingEntity();
//                
//                p.setIdRating(rs.getInt(1));
//                p.setRating(Integer.parseInt(rs.getString(2)));
//                p.setDateRating(rs.getDate(3));
//                p.setRestauIdR(restau);
//                p.setUserIdR(user);
//             
//                list.add(p);
//            }
//        }
//        catch(SQLException ex){
//           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
//        }
//        
//        return  list;   
    }

    @Override
    public boolean Chercher(RatingEntity t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public boolean rechercherRatingByUser(int UserIdR) {
        
        RatingEntity ratingEntity = new RatingEntity();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            UserDao userdao = new UserDao();
            res=ps.executeQuery("select * from 	rating where UserIdR="+UserIdR);
            while(res.next())
            {
                 int id = res.getInt("UserIdR");
              //  String Nom =res.getString("nomMagasin");
             
               ratingEntity.setUserIdR(userdao.displayById(id));
                //ratingEntity.setNomMagasin(Nom);
                  
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        return true;
}
    
     public boolean rechercherRatingByRestau(int RestauIdR) {
        
        RatingEntity ratingEntity = new RatingEntity();
            try
        {
            Statement ps = connection.createStatement();
            ResultSet res;
            RestaurentServices resService = new RestaurentServices();
            res=ps.executeQuery("select * from 	rating where RestauIdR="+RestauIdR);
            while(res.next())
            {
                 int id = res.getInt("RestauIdR");
            
             
               ratingEntity.setRestauIdR(resService.rechercherRestauByID(id));
               
                  
            }
        }catch(Exception e)
        {
           System.out.println("Introuvable"+e.getMessage());
        } 
        return true;
}
}
