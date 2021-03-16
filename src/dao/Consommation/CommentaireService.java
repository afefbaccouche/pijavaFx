/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import ConnectionBaseDonn.connect;
import dao.UserDao;
import entity.Consommation.Commentaire;
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
public class CommentaireService implements IService<Commentaire> {
    
    private Connection connection;
    private static CommentaireService instance;
    private Statement ste;
    private ResultSet rs;
    private PreparedStatement pst;
     
//     byte [] photo=null;
//    String filename=null;
     
    public CommentaireService() {
       connection =connect.getInstance().getCnx(); 
       
    }
    
    
      public static CommentaireService getInstance(){
        if(instance==null) 
            instance=new CommentaireService();
        return instance;
    }
    

    @Override
    public void add(Commentaire t) {
 String pattern = "yy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
//
        String req = "INSERT INTO commentaire (body,createdDate,RestauId,UserId)"
                + " values(?,?,?,?)";
                
//        
//            
        System.out.println(req);
        System.out.println("comment inserted !!");
        try {
    
            pst=connection.prepareStatement(req); 
       
        pst.setString(1,t.getBody());
        pst.setString(2,date);
        pst.setInt(3,t.getRestauId().getIdRestaurant());
        pst.setInt(4,t.getUserId().getIdUser());
       
        pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public ObservableList<Commentaire>  getAll() {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

// String req="select * from Rating"; 
//  // UserDao userdao = new UserDao();
//        ObservableList<Commentaire> list=FXCollections.observableArrayList(); 
//        try{
//        
//          ste =connection.createStatement();
//          rs=ste.executeQuery(req);
//          
//            RestaurentServices crudtablefeProd= new RestaurentServices();
//                  UserDao userdao = new UserDao();
//                  
////            int iduser=rs.getInt("UserId");
////           int idProd=rs.getInt("RestauId");
//           
//               Restaurants restau = crudtableProd.rechercherRestauByID(idProd);
//           
//               User user=userdao.displayById(iduser);
//
//             while(rs.next()){
//                Commentaire p=new Commentaire();
//                
//                p.setIdCommentaire(rs.getInt(1));
//                p.setBody(rs.getString(2));
//                p.setCreatedDate(rs.getDate(3));
//                p.setRestauId(restau);
//                p.setUserId(user);
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
    public boolean Chercher(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
