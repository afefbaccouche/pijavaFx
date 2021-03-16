/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;

import entity.Services.Societe;
import entity.Services.like__societe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import session.Session;


public class CrudLikeSociete {
    private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public void add(like__societe like) {
        
        //System.out.println(soc+"ajout");
         String req="insert into like__societe values("+null+","+
                 like.getUser().getIdUser()+","+
                 like.getSociete().getId()+")";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
    }

    public void desLike(Societe s) {
        try {
            Statement ps=connection.createStatement();
            ps.executeUpdate("delete from like__societe where id_user ="+Session.getConnectedUser().getIdUser()+" and id_Societe ="+s.getId());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erreur de suppression\n"+ex.getMessage());
        }
        
    }
        
    public int nblike(Societe s) {
        int nbr=0;
        try {
            Statement ps=connection.createStatement(); 
            ResultSet res;
            res=ps.executeQuery("select * from 	like__societe where id_Societe="+s.getId());
            while(res.next()){
                nbr++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbr;
    }    
        
    public boolean islike(Societe s) {
        try {
            Statement ps=connection.createStatement(); 
            ResultSet res;
            res=ps.executeQuery("select *  from like__societe where id_user ="+Session.getConnectedUser().getIdUser()+" and id_Societe ="+s.getId());
            while(res.next()){
                return true ;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }    
        
    
}
