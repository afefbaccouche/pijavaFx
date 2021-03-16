/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Question;
import entity.Reponse;
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
public class ReponseDao {

    private Statement st;
    private ResultSet rs;
    private static ReponseDao instance;

    private ReponseDao() {

    }

    public static ReponseDao getInstance() {
        if (instance == null) {
            return new ReponseDao();
        }
        return instance;
    }

    public boolean add(Reponse reponse) {

        boolean isAdded = false;
        try {

            String req = "insert into reponse (contenu,User_id,Question_id) values(?,?,?)";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);

            pst.setString(1, reponse.getContenu());
            pst.setInt(2, reponse.getClient().getIdUser());
            pst.setInt(3, reponse.getQuestion().getId());
            int result = pst.executeUpdate();

            if (result > 0) {
                isAdded = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

}
