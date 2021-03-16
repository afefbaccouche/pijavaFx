/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esprit
 */
public class LoginDao {

    private Statement st;
    private ResultSet rs;
    private static LoginDao instance;

    public LoginDao() {

    }

    public static LoginDao getInstance() {
        if (instance == null) {
            return new LoginDao();
        }
        return instance;
    }

    public User login(String email, String password) {
        User connectedUser = null;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            ResultSet rs = ste.executeQuery("SELECT * FROM User WHERE email= " + "'" + email + "'"
                    + " AND password_plain= " + "'" + password + "'");

            while (rs.next()) {
                if (rs.getString("email") != null && rs.getString("password_plain") != null) {
                    System.out.println("login User " + rs);
                    connectedUser = new User();
                    connectedUser.setIdUser(rs.getInt("id"));
                    connectedUser.setNomUser(rs.getString("nom"));
                    connectedUser.setPrenomUser(rs.getString("prenom"));
                    connectedUser.setEmailUser(rs.getString("email"));
                    connectedUser.setRole(rs.getString("role"));
                 connectedUser.setEnabled(rs.getBoolean("enabled"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connectedUser;
    }
    public boolean findbynum(int num) {
        PreparedStatement pst;
          Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
        
        String req = "select * from user where telephone =?";
        boolean u = false;
        

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, num);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                            if(rs.getInt(20)==num)
                                u=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
        public String findbypwd(int num) {
        PreparedStatement pst;
          Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
        
        String req = "select * from user where telephone = ?";
        String u = "";
        

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, num);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                            if(rs.getInt(20)==num)
                                u=rs.getString(18);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    public boolean signUp(User user, String role, boolean enabled) {
        boolean isCorrect = false;
        String req = "insert into User (username, username_canonical, email, email_canonical, enabled, password, roles, nom, prenom, type, adress, sold, password_plain, role) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = connect.getInstance().getCnx();
        PreparedStatement pst;

        try {

            pst = connection.prepareStatement(req);

            pst.setString(1, user.getNomUser());
            pst.setString(2, user.getNomUser());
            pst.setString(3, user.getEmailUser());
            pst.setString(4, user.getEmailUser());
            pst.setBoolean(5, enabled);
            pst.setString(6, user.getPasswordUser());
            pst.setString(7, "");
            pst.setString(8, user.getNomUser());
            pst.setString(9, user.getPrenomUser());
            pst.setString(10, "type");
            pst.setString(11, "address");
            pst.setDouble(12, 0.0);
            pst.setString(13, user.getPasswordUser());
            pst.setString(14, role);

            pst.executeUpdate();
            isCorrect = true;

        } catch (SQLException ex) {
            Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isCorrect;
    }

}
