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
public class Reponse {
    
    private int id;
    private String contenu;
    private User client;
    private Question question;

    public Reponse(int id, String contenu, User client, Question question) {
        this.id = id;
        this.contenu = contenu;
        this.client = client;
        this.question = question;
    }

    public Reponse() {
    }

    public Reponse(String contenu, User client, Question question) {
        this.contenu = contenu;
        this.client = client;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", contenu=" + contenu + ", client=" + client + ", question=" + question + '}';
    }
    
    
    
}
