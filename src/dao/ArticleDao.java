/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Article;
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
public class ArticleDao {

    private Statement st;
    private ResultSet rs;
    private static ArticleDao instance;

    private ArticleDao() {

    }

    public static ArticleDao getInstance() {
        if (instance == null) {
            return new ArticleDao();
        }
        return instance;
    }

    public ArrayList<Article> getAll() {
        ArrayList<Article> listArticles = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM article");

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setBody(rs.getString("body"));
                article.setImage(rs.getString("nom_image"));
                article.setIdUser(rs.getInt("User_id"));
                article.setCreatedAt(rs.getDate("created_at"));

                listArticles.add(article);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticles;
    }

    public ArrayList<Article> getBuDoctorId(int doctorId) {
        ArrayList<Article> listArticles = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM article where User_id=" + doctorId);

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setBody(rs.getString("body"));
                article.setImage(rs.getString("nom_image"));
                article.setIdUser(rs.getInt("User_id"));
                article.setCreatedAt(rs.getDate("created_at"));

                listArticles.add(article);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticles;
    }

    public boolean delete(Article article) {
        boolean isDeleted = false;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("DELETE FROM article\n"
                    + "WHERE id=" + article.getId());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }

    public boolean update(Article article) {
        boolean isUpdated = false;
        try {

            String req = "update article set title=?, description=?, body=?, nom_image=? where id=?";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setString(1, article.getTitle());
            pst.setString(2, article.getDescription());
            pst.setString(3, article.getBody());
            pst.setString(4, article.getImage());
            pst.setInt(5, article.getId());
            int result = pst.executeUpdate();
            System.out.println("DAO articel: " + article);
            System.out.println("result update DAO: " + result);
            if (result > 0) {
                isUpdated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }

    public boolean add(Article article) {
        boolean isAdded = false;
        try {

            String req = "insert into article (title,description,body,nom_image,User_id) values(?,?,?,?,?)";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setString(1, article.getTitle());
            pst.setString(2, article.getDescription());
            pst.setString(3, article.getBody());
            pst.setString(4, article.getImage());
            pst.setInt(5, article.getIdUser());
            int result = pst.executeUpdate();
            if (result > 0) {
                isAdded = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

    public Article findById(int id) {
        Article article = null;
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM article WHERE id=" + id);

            while (rs.next()) {

                article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setBody(rs.getString("body"));
                article.setImage(rs.getString("nom_image"));
                article.setIdUser(rs.getInt("User_id"));
                article.setCreatedAt(rs.getDate("created_at"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return article;
    }

    public ArrayList<Article> searchArticle(String key) {
        ArrayList<Article> listArticles = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM article WHERE title LIKE '%" + key + "%' OR description LIKE '%" + key + "%' OR body LIKE '%" + key + "%'");

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setBody(rs.getString("body"));
                article.setImage(rs.getString("nom_image"));
                article.setIdUser(rs.getInt("User_id"));
                article.setCreatedAt(rs.getDate("created_at"));

                listArticles.add(article);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticles;
    }

}
