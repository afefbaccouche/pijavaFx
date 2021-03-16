/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author esprit
 */
public class Article {

    private int id;
    private String title;
    private String description;
    private String body;
    private String image;
    private Date createdAt;
    private int idUser;

    public Article(int id, String title, String description, String body, String image, int idUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.body = body;
        this.image = image;
        this.idUser = idUser;
    }

    public Article(String title, String description, String body, String image, int idUser) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.image = image;
        this.idUser = idUser;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title=" + title + ", description=" + description + ", body=" + body + ", image=" + image + ", createdAt=" + createdAt + ", idUser=" + idUser + '}';
    }

   

}
