/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.entities;

import java.sql.Date;

/**
 *
 * @author jacem
 */
public class ParticipationCompagneDon {
    
    private int id;
    private int idCompagne;
    private int idUser;
    private Date date;

    public ParticipationCompagneDon() {
    }

    public ParticipationCompagneDon(int id, int idCompagne, int idUser, Date date) {
        this.id = id;
        this.idCompagne = idCompagne;
        this.idUser = idUser;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompagne() {
        return idCompagne;
    }

    public void setIdCompagne(int idCompagne) {
        this.idCompagne = idCompagne;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ParticipationCompagneDon{" + "id=" + id + ", idCompagne=" + idCompagne + ", idUser=" + idUser + ", date=" + date + '}';
    }
    
    
}
