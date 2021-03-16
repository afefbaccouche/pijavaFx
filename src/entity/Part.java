/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Pirateos
 */
public class Part {
    private int id;
    private int id_cli;
    private int id_ass;

    public Part(int id_cli, int id_ass) {
        this.id_cli = id_cli;
        this.id_ass = id_ass;
    }

    public Part() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public int getId_ass() {
        return id_ass;
    }

    public void setId_ass(int id_ass) {
        this.id_ass = id_ass;
    }

    @Override
    public String toString() {
        return "participation{" + "id=" + id + ", id_cli=" + id_cli + ", id_ass=" + id_ass + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Part other = (Part) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_cli != other.id_cli) {
            return false;
        }
        if (this.id_ass != other.id_ass) {
            return false;
        }
        return true;
    }
    
    
}
