/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Maladie;
import entity.Question;
import entity.User;
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
public class QuestionDao {

    private Statement st;
    private ResultSet rs;
    private static QuestionDao instance;

    private QuestionDao() {

    }

    public static QuestionDao getInstance() {
        if (instance == null) {
            return new QuestionDao();
        }
        return instance;
    }

    public boolean add(Question question) {

        boolean isAdded = false;
        try {

            String req = "insert into question (question,User_id,Maladie_id) values(?,?,?)";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);

            pst.setString(1, question.getQuestion());
            pst.setInt(2, question.getMedecin().getIdUser());
            pst.setInt(3, question.getMaladie().getId());
            int result = pst.executeUpdate();

            if (result > 0) {
                isAdded = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

    public ArrayList<Question> getByMaladieId(int maladieId) {
        ArrayList<Question> listQuestion = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM question where Maladie_id=" + maladieId);

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                User medecin = UserDao.getInstance().findById(rs.getInt("User_id"));
                Maladie maladie = MaladieDao.getInstance().findById(rs.getInt("Maladie_id"));
                question.setMedecin(medecin);
                question.setMaladie(maladie);
                listQuestion.add(question);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listQuestion;
    }
    
     public boolean delete(Question question) {
        boolean isDeleted = false;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("DELETE FROM question\n"
                    + "WHERE id=" + question.getId());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }

}
