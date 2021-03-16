/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author esprit
 */
public class UserDao {

    private Statement st;
    private ResultSet rs;
    private static UserDao instance;

    public UserDao() {

    }

    public static UserDao getInstance() {
        if (instance == null) {
            return new UserDao();
        }
        return instance;
    }

    public ArrayList<User> getNotEnabledUsers() {
        ArrayList users = new ArrayList<User>();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM User WHERE enabled = false");

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id"));
                user.setNomUser(rs.getString("nom"));
                user.setPrenomUser(rs.getString("prenom"));
                user.setEmailUser(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public boolean enableUser(User user) {
        boolean isEnabled = false;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("UPDATE user\n"
                    + "SET enabled = true\n"
                    + "WHERE id=" + user.getIdUser());

            if (result > 0) {
                isEnabled = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isEnabled;
    }

    public boolean deleteUser(User user) {
        boolean isDeleted = false;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("DELETE FROM user\n"
                    + "WHERE id=" + user.getIdUser());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isDeleted;
    }
    
    
    
    public boolean update(User u) {
        
       try {
       
        String qry = "UPDATE user SET nom = '"+u.getNomUser()
                +"', prenom = '"+u.getPrenomUser()
                +"', username = '"+u.getUserName()
                +"', username_canonical = '"+u.getUserName()
                +"', email = '"+u.getEmailUser()
                +"', email_canonical = '"+u.getEmailCanonical()
                +"', adress = '"+u.getAdressUser()
                +"' WHERE id = "+u.getIdUser();
        
        
         Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
         Statement ste = connection.createStatement();
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    /*public User displayById(int id) {
        String req="select * from user where id="+id;
           User u=new User();
        try {
            rs=st.executeQuery(req);
          
            rs.next();
            
                u.setIdUser(rs.getInt("id"));
                u.setEmailUser(rs.getString("email"));
                u.setPasswordUser(rs.getString("password_plain"));
           
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return u;
    }*/
    public User findById(int id) {
        User user = null;
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM User WHERE id=" + id);

            while (rs.next()) {

                user = new User();
                user.setIdUser(rs.getInt("id"));
                user.setNomUser(rs.getString("nom"));
                user.setPrenomUser(rs.getString("prenom"));
                user.setEmailUser(rs.getString("email"));
                user.setRole(rs.getString("role"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    
    public User displayById(int id) {
        User u=new User();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("select * from user where id="+id);

            rs.next();
            
                u.setIdUser(rs.getInt("id"));
                u.setEmailUser(rs.getString("email"));
                u.setPasswordUser(rs.getString("password_plain"));
                u.setAdressUser(rs.getString("adress"));
                u.setNomUser(rs.getString("nom"));
                u.setPrenomUser(rs.getString("prenom"));
                u.setUserName(rs.getString("username"));
                

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }
    
    
 


    


    public List<User> displayAll() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("select * from user");

            while (rs.next()) {
                User u = new User();
                u.setIdUser(rs.getInt("id"));
                u.setEmailUser(rs.getString("email"));
                u.setPasswordUser(rs.getString("password_plain"));
                u.setAdressUser(rs.getString("adress"));
                u.setNomUser(rs.getString("nom"));
                u.setPrenomUser(rs.getString("prenom"));
                u.setUserName(rs.getString("username"));

                list.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<User> displayAllByRole(User user) {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("select * from user where role='Client' and id != "+user.getIdUser());

            while (rs.next()) {
                User u = new User();
                u.setIdUser(rs.getInt("id"));
                u.setEmailUser(rs.getString("email"));
                u.setPasswordUser(rs.getString("password_plain"));
                u.setAdressUser(rs.getString("adress"));
                u.setNomUser(rs.getString("nom"));
                u.setPrenomUser(rs.getString("prenom"));
                u.setUserName(rs.getString("username"));

                list.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<User> displayAllByPrenom(String prenom) {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("select * from user where prenom= '"+prenom+"'");

            while (rs.next()) {
                User u = new User();
                u.setIdUser(rs.getInt("id"));
                u.setEmailUser(rs.getString("email"));
                u.setPasswordUser(rs.getString("password_plain"));
                u.setAdressUser(rs.getString("adress"));
                u.setNomUser(rs.getString("nom"));
                u.setPrenomUser(rs.getString("prenom"));
                u.setUserName(rs.getString("username"));

                list.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


}    

