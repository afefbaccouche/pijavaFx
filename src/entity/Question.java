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
public class Question {
    
    private int id;
    private String question;
    private User medecin;
    private Maladie maladie;

    public Question() {
    }

    public Question(int id, String question, User medecin, Maladie maladie) {
        this.id = id;
        this.question = question;
        this.medecin = medecin;
        this.maladie = maladie;
    }

    public Question(String question, User medecin, Maladie maladie) {
        this.question = question;
        this.medecin = medecin;
        this.maladie = maladie;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getMedecin() {
        return medecin;
    }

    public void setMedecin(User medecin) {
        this.medecin = medecin;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }
    
    
    
    
}
