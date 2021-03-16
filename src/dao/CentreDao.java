/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import controller.manager.FXMLAjouterCentrelController;
import entity.Article;
import entity.Centre;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esprit
 */
public class CentreDao {

    private Statement st;
    private ResultSet rs;
    private static CentreDao instance;

    private CentreDao() {

    }

    public static CentreDao getInstance() {
        if (instance == null) {
            return new CentreDao();
        }
        return instance;
    }

    public ArrayList<Centre> getAll() {
        ArrayList<Centre> listCentres = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM centre");

            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setNom(rs.getString("nom"));
                centre.setDescription(rs.getString("description"));

                centre.setImage(rs.getString("image"));
                centre.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                centre.setPrix(rs.getFloat("prix"));
                centre.setNbrPlace(rs.getInt("nbrPlace"));
                centre.setAddress(rs.getString("address"));
                centre.setTelephone(rs.getInt("telephone"));

                listCentres.add(centre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCentres;
    }

    public ArrayList<Centre> getByManagerId(int managerId) {
        ArrayList<Centre> listCentres = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM centre where User_id=" + managerId);

            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setNom(rs.getString("nom"));
                centre.setImage(rs.getString("image"));
                centre.setDescription(rs.getString("description"));
                centre.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                centre.setPrix(rs.getFloat("prix"));
                centre.setNbrPlace(rs.getInt("nbrPlace"));
                centre.setAddress(rs.getString("address"));
                centre.setTelephone(rs.getInt("telephone"));
                listCentres.add(centre);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("dao maladies " + listCentres);
        return listCentres;
    }

    public Centre findById(int id) {
        Centre centre = null;
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM centre WHERE id=" + id);

            while (rs.next()) {
                centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setNom(rs.getString("nom"));
                //int idManager = rs.getInt("User_id");
                centre.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                centre.setPrix(rs.getFloat("prix"));
                centre.setNbrPlace(rs.getInt("nbrPlace"));
                centre.setAddress(rs.getString("address"));
                centre.setTelephone(rs.getInt("telephone"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centre;
    }

    public boolean add(Centre centre) {
        boolean isAdded = false;
        try {

            String req = "insert into centre (nom,description,image,User_id,prix,nbrPlace,address,telephone) values(?,?,?,?,?,?,?,?)";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setString(1, centre.getNom());
            pst.setString(2, centre.getDescription());

            pst.setString(3, centre.getImage());

            pst.setInt(4, centre.getManager().getIdUser());
            pst.setFloat(5, centre.getPrix());
            pst.setInt(6, centre.getNbrPlace());
            pst.setString(7, centre.getAddress());
            pst.setInt(8, centre.getTelephone());
            int result = pst.executeUpdate();
            if (result > 0) {
                isAdded = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

    public boolean delete(Centre centre) {
        boolean isDeleted = false;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("DELETE FROM centre\n"
                    + "WHERE id=" + centre.getId());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }

    public boolean update(Centre centre) {
        boolean isUpdated = false;
        try {

            String req = "update centre set nom=?, description=?, image=?, prix=?, nbrPlace=?, address=?, telephone=? where id=?";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setString(1, centre.getNom());
            pst.setString(2, centre.getDescription());

            pst.setString(3, centre.getImage());
            pst.setFloat(4, centre.getPrix());
            pst.setInt(5, centre.getNbrPlace());
            pst.setString(6, centre.getAddress());
            pst.setInt(7, centre.getTelephone());
            pst.setInt(8, centre.getId());
            int result = pst.executeUpdate();
            System.out.println("DAO centre: " + centre);
            System.out.println("result update DAO: " + result);
            if (result > 0) {
                isUpdated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }

    public ArrayList<Centre> searchCentre(String key) {
        ArrayList<Centre> listCentres = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM centre where nom LIKE '%" + key + "%'" + " OR description LIKe '%" + key + "%' OR address LIKE '%" + key + "%'");

            while (rs.next()) {
                Centre centre = new Centre();
                centre.setId(rs.getInt("id"));
                centre.setNom(rs.getString("nom"));
                centre.setImage(rs.getString("image"));
                centre.setDescription(rs.getString("description"));
                centre.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                centre.setPrix(rs.getFloat("prix"));
                centre.setNbrPlace(rs.getInt("nbrPlace"));
                centre.setAddress(rs.getString("address"));
                centre.setTelephone(rs.getInt("telephone"));
                listCentres.add(centre);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("dao maladies " + listCentres);
        return listCentres;
    }

}
