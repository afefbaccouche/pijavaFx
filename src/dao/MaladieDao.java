/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Article;
import entity.Centre;
import entity.Maladie;
import entity.Question;
import java.sql.Connection;
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
public class MaladieDao {

    private Statement st;
    private ResultSet rs;
    private static MaladieDao instance;

    private MaladieDao() {

    }

    public static MaladieDao getInstance() {
        if (instance == null) {
            return new MaladieDao();
        }
        return instance;
    }

    public ArrayList<Maladie> getBuDoctorId(int doctorId) {
        ArrayList<Maladie> listMaladies = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM maladie where User_id=" + doctorId);

            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setMedecin(UserDao.getInstance().findById(rs.getInt("User_id")));
                listMaladies.add(maladie);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("dao maladies " + listMaladies);
        return listMaladies;
    }

    public boolean delete(Maladie maladie) {
        boolean isDeleted = false;
        try {

            ArrayList<Question> listQuestions = QuestionDao.getInstance().getByMaladieId(maladie.getId());
            for (int i = 0; i < listQuestions.size(); i++) {
                QuestionDao.getInstance().delete(listQuestions.get(i));
            }

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("DELETE FROM maladie\n"
                    + "WHERE id=" + maladie.getId());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }

    /*public boolean update(Maladie maladie) {
        boolean isUpdated = false;
        try {

            String req = "update maladie set nom=?";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setString(1, maladie.getNom());
           
            int result = pst.executeUpdate();
            System.out.println("DAO maladie: " + maladie);
            System.out.println("result update DAO: " + result);
            if (result > 0) {
                isUpdated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaladieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }*/
    public int add(Maladie maladie) {
        int generatedId = -1;
        try {

            String req = "insert into maladie (nom,User_id) values(?,?)";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, maladie.getNom());

            pst.setInt(2, maladie.getMedecin().getIdUser());
            int result = pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaladieDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return generatedId;
    }

    public Maladie findById(int id) {
        Maladie maladie = null;
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM maladie WHERE id=" + id);

            while (rs.next()) {
                maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setNom(rs.getString("nom"));
                int idMedecin = rs.getInt("User_id");
                maladie.setMedecin(UserDao.getInstance().findById(idMedecin));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maladie;
    }

    public ArrayList<Maladie> getAll() {
        ArrayList<Maladie> listMaladies = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM maladie");

            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setMedecin(UserDao.getInstance().findById(rs.getInt("User_id")));
                listMaladies.add(maladie);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMaladies;
    }

    public ArrayList<Maladie> searchMaladieByDoctor(String key, int doctorId) {
        ArrayList<Maladie> listMaladies = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM maladie where nom LIKE '%" + key + "%' AND User_id=" + doctorId);

            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setMedecin(UserDao.getInstance().findById(rs.getInt("User_id")));
                listMaladies.add(maladie);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMaladies;
    }

    public ArrayList<Maladie> searchMaladie(String key) {
        ArrayList<Maladie> listMaladies = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM maladie where nom LIKE '%" + key + "%'");

            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setMedecin(UserDao.getInstance().findById(rs.getInt("User_id")));
                listMaladies.add(maladie);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMaladies;
    }

}
