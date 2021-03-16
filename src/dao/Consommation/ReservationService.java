/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import ConnectionBaseDonn.connect;
import dao.UserDao;
import entity.Consommation.Reservation;
import entity.Consommation.Restaurants;
import entity.User;
import java.io.IOException;
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


public class ReservationService implements IService<Reservation>{
  
      private Connection connection;
    private static ReservationService instance;
    private Statement ste;
    private ResultSet rs;
    private PreparedStatement pst;
    
      
    public ReservationService() {
       connection =connect.getInstance().getCnx(); 
       
    }
    
    
      public static ReservationService getInstance(){
        if(instance==null) 
            instance=new ReservationService();
        return instance;
    }
    

    
    
    @Override
    public void add(Reservation t) {
 String pattern = "yy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
//
   try{
        String req =  "INSERT INTO reservation (aunomdeReserv,nombreReserv,descriptionReserv,"
                    + " dateReserv,idrestaurant,iduser) "
                    + "VALUES (?,?,?,?,?,?)";
           pst=connection.prepareStatement(req); 
          //  st.setInt(1,reservation.getIdReserv());
            pst.setString(1, t.getAunomdeReserv());
            pst.setInt(2,t.getNombreReserv());
            pst.setString(3, t.getDescriptionReserv());
            pst.setTimestamp(4, t.getDateReserv());
             pst.setInt(5, t.getIdrestaurant().getIdRestaurant());
              pst.setInt(6, t.getIduser().getIdUser());
           pst.executeUpdate();
            System.out.println("Reservation ajoutée");
  
          } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("erreuuuur");
          }        
    }

    @Override
    public void delete(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Reservation> getAll() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

// String req="select * from Restaurant"; 
//        ObservableList<Reservation> list=FXCollections.observableArrayList(); 
//        try{
//        
//          ste =connection.createStatement();
//          rs=ste.executeQuery(req);
//           
//                
//                int idMag=rs.getInt("idrestaurant");
//              
//                RestaurentServices crudtableRes = new RestaurentServices();
//               Restaurants retaurant = crudtableRes.rechercherRestauByID(idMag);
//
////               UserDao userdao = new UserDao();
////                User user=userdao.displayById(iduser);
//             while(rs.next()){
//                Reservation p=new Reservation();
//              p.setIdReserv(rs.getInt(1));
//                p.setAunomdeReserv(rs.getString(2));
//                p.setDescriptionReserv(rs.getString(3));
//                p.setDateReserv(rs.getTimestamp(4));
//                p.setNombreReserv(rs.getInt(5));
//              
//                p.setIdrestaurant(retaurant);
//             // p.setIduser(rs.getInt(7));
//                list.add(p);
//            }
        //}
//        catch(SQLException ex){
//           Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);   
//        }
        
//        return  list;   
    }

    @Override
    public boolean Chercher(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
