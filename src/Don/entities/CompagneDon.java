/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.entities;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jacem
 */
public class CompagneDon {

    private int id;
    private String titre;
    private Date dateFin;
    private String description;
    private int nbrParticipation;

    public CompagneDon() {
    }

    public CompagneDon(int id, String titre, Date dateFin, String description, int nbrParticipation) {
        this.id = id;
        this.titre = titre;
        this.dateFin = dateFin;
        this.description = description;
        this.nbrParticipation = nbrParticipation;
    }

    public CompagneDon(int id, int titre, String description, String nbr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CompagneDon(int id, String titre, String description, Date date, int nbr) {
        this.id = id;
        this.titre = titre;
        this.dateFin = dateFin;
        this.description = description;
        this.nbrParticipation = nbr; 
    }

    public CompagneDon(String text, String text0) {
    
        this.titre = text;
        this.dateFin = new Date();
        this.description = text0;
        this.nbrParticipation = 0; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrParticipation() {
        return nbrParticipation;
    }

    public void setNbrParticipation(int nbrParticipation) {
        this.nbrParticipation = nbrParticipation;
    }

    @Override
    public String toString() {
        return "CompagneDon{" + "id=" + id + ", titre=" + titre + ", dateFin=" + dateFin + ", description=" + description + ", nbrParticipation=" + nbrParticipation + '}';
    }



}

