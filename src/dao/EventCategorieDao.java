/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import ConnectionBaseDonn.connect;
import entity.EventCategorie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Baklouti
 */
public class EventCategorieDao implements IEventdao<EventCategorie>{
    private static EventCategorieDao instance;
    private Statement st;
    private ResultSet rs;
    
    public EventCategorieDao(){
         connect cs=connect.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static EventCategorieDao getInstance(){
        if(instance==null) 
            instance=new EventCategorieDao();
        return instance;
    }

    @Override
    public void add(EventCategorie t) {
        String req="insert into event_categorie (user_id,nom_event_categorie) values ("+t.getUser().getIdUser()+",'"+t.getNomCategorie()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       
    
    }

    @Override
    public List<EventCategorie> displayAll() {
        String req="select * from event_categorie";
        ObservableList<EventCategorie> list=FXCollections.observableArrayList();  
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                EventCategorie cat=new EventCategorie();
                cat.setId(rs.getInt("id_event_categorie"));                   
                cat.setNomCategorie(rs.getString("nom_event_categorie"));
                
                list.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public EventCategorie displayById(int id) {
        String req="select * from event_categorie where id_event_categorie="+id;
           EventCategorie cat=new EventCategorie();
        try {
            rs=st.executeQuery(req);
          
            rs.next();
                cat.setId(rs.getInt("id_event_categorie"));
                cat.setNomCategorie(rs.getString("nom_event_categorie"));

           
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return cat;
    }
    
    
    public EventCategorie displayByName(String nom) {
        String req="select * from event_categorie where nom_event_categorie = '"+nom+"';";
           EventCategorie cat=new EventCategorie();
        try {
            rs=st.executeQuery(req);
          
            rs.next();
                cat.setId(rs.getInt("id_event_categorie"));
                cat.setNomCategorie(rs.getString("nom_event_categorie"));

           
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return cat;
    }


    public boolean updateCat(EventCategorie t, String nomCat) {

        
        try {
            String qry = "UPDATE event_categorie SET nom_event_categorie = '"+nomCat
                    +"' WHERE id_event_categorie = "+t.getId();
            
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
     return false;
    }

    @Override
    public void delete(EventCategorie t) {
        try {
            String req="delete from event_categorie where id_event_categorie= "+t.getId();

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventCategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public boolean update(EventCategorie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
