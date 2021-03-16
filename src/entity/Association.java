/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author fahdj
 */
public class Association {
    private int id;
    private String name;
    private int nbr;
    private String description;
    private String type;

    public Association(String name, int nbr, String description, String type) {
        
        this.name = name;
        this.nbr = nbr;
        this.description = description;
        this.type = type;
    }

    public Association(int id, String name, int nbr, String desciption, String type) {
        this.id = id;
        this.name = name;
        this.nbr = nbr;
        this.description = desciption;
        this.type = type;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getNbr() {
        return nbr;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Association{" + "id=" + id +", name=" + name + ", nbr=" + nbr + ", desciption=" + description + ", type=" + type + '}';
    }
    
    
    
}
