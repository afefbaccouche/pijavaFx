/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author esprit
 */
public class LikeArticle {
    
    private int id;
    private User user;
    private Article article;
    
    private int nbLikesPerArticle;

    public LikeArticle() {
    }

    public LikeArticle(int id, User user, Article article) {
        this.id = id;
        this.user = user;
        this.article = article;
    }

    public LikeArticle(User user, Article article) {
        this.user = user;
        this.article = article;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getNbLikesPerArticle() {
        return nbLikesPerArticle;
    }

    public void setNbLikesPerArticle(int nbLikesPerArticle) {
        this.nbLikesPerArticle = nbLikesPerArticle;
    }

    @Override
    public String toString() {
        return "LikeArticle{" + "id=" + id + ", user=" + user + ", article=" + article + ", nbLikesPerArticle=" + nbLikesPerArticle + '}';
    }
    
    
    
    
    
    
    
}
