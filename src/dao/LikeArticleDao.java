/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Article;
import entity.LikeArticle;
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
public class LikeArticleDao {

    private Statement st;
    private ResultSet rs;
    private static LikeArticleDao instance;

    private LikeArticleDao() {

    }

    public static LikeArticleDao getInstance() {
        if (instance == null) {
            return new LikeArticleDao();
        }
        return instance;
    }

    public ArrayList<LikeArticle> getByUserArticle(int userId, int articleId) {
        ArrayList<LikeArticle> listLikes = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM like_article where User_id=" + userId + " && Article_id=" + articleId);

            while (rs.next()) {
                LikeArticle like = new LikeArticle();
                like.setId(rs.getInt("id"));
                like.setUser(UserDao.getInstance().findById(rs.getInt("User_id")));
                like.setArticle(ArticleDao.getInstance().findById(rs.getInt("Article_id")));

                listLikes.add(like);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLikes;
    }

    public boolean add(int userId, int articleId) {
        boolean isAdded = false;
        try {

            String req = "insert into like_article (User_id, Article_id) values(?,?)";

            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setInt(1, userId);
            pst.setInt(2, articleId);

            int result = pst.executeUpdate();
            if (result > 0) {
                isAdded = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }
    
     public ArrayList<LikeArticle> getTopArticles() {
        ArrayList<LikeArticle> listLikes = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT *, count(*) FROM like_article GROUP BY Article_id ASC LIMIT 3");

            while (rs.next()) {
                LikeArticle like = new LikeArticle();
                like.setId(rs.getInt("id"));
                like.setUser(UserDao.getInstance().findById(rs.getInt("User_id")));
                like.setArticle(ArticleDao.getInstance().findById(rs.getInt("Article_id")));
                 like.setNbLikesPerArticle(rs.getInt("count(*)"));

                listLikes.add(like);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLikes;
    }

}
