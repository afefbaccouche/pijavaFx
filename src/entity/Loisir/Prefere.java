/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Loisir;

/**
 *
 * @author Pirateos
 */

public class Prefere{
private int id;
private int client_pref_id;
private int bri_pref_id;

    public Prefere(int client_pref_id, int bri_pref_id) {
        this.client_pref_id = client_pref_id;
        this.bri_pref_id = bri_pref_id;
    }

    public Prefere() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_pref_id() {
        return client_pref_id;
    }

    public void setClient_pref_id(int client_pref_id) {
        this.client_pref_id = client_pref_id;
    }

    public int getBri_pref_id() {
        return bri_pref_id;
    }

    public void setBri_pref_id(int bri_pref_id) {
        this.bri_pref_id = bri_pref_id;
    }

   
    
}
