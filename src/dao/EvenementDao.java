/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import ConnectionBaseDonn.connect;
import entity.Evenement;
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
public class EvenementDao implements IEventdao<Evenement>{
    private static EvenementDao instance;
    private Statement st;
    private ResultSet rs;
    
    public EvenementDao(){
         connect cs=connect.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static EvenementDao getInstance(){
        if(instance==null) 
            instance=new EvenementDao();
        return instance;
    }

    @Override
    public void add(Evenement t) {
        
     String req="insert into evenement (event_categorie_id,user_id,nom_event,description_event,date_debut_event,date_fin_event,heure_debut_event,heure_fin_event,adresse_event,prix_event,image_event)values("+

             t.getEventCategorie().getId()+","
             +t.getUser().getIdUser()+",'"
             +t.getNomEvent()+"','"
             +t.getDescription()+"','"
             +t.getDateDebut()+"','"
             +t.getDateFin()+"','"
             +t.getHeureDebut()+"','"
             +t.getHeureFin()+"','"
             +t.getAdresse()+"',"
             +t.getPrix()+",'"
             +t.getImage()+"')";
        
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    @Override
    public boolean update(Evenement t) {
       
       String qry = "UPDATE evenement SET nom_event = '"+t.getNomEvent()
               +"', event_categorie_id = "+t.getEventCategorie().getId()
               +", description_event = '"+t.getDescription()
               +"', date_debut_event = '"+t.getDateDebut()
               +"', date_fin_event = '"+t.getDateFin()
               +"', heure_debut_event = '"+t.getHeureDebut()
               +"', heure_fin_event = '"+t.getHeureFin()
               +"', adresse_event = '"+t.getAdresse()
               +"', image_event = '"+t.getImage()               
               +"', prix_event = "+t.getPrix()
               +" WHERE id_event = "+t.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
    
    
    @Override
    public void delete(Evenement t) {
        try {
            String req="delete from evenement where id_event="+t.getId();
            Evenement e=displayById(t.getId());
            
            if(e!=null)
                st.executeUpdate(req);
            
            else System.out.println("n'existe pas");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }


    @Override
    public List<Evenement> displayAll() {
        String req="select * from evenement";
        ObservableList<Evenement> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Evenement e=new Evenement();
                e.setId(rs.getInt("id_event"));
                e.setEventCategorie(EventCategorieDao.getInstance().displayById(rs.getInt("event_categorie_id")));
                e.setUser(UserDao.getInstance().displayById(rs.getInt("user_id")));
                e.setNomEvent(rs.getString("nom_event"));
                e.setDescription(rs.getString("description_event"));
                e.setDateDebut(rs.getDate("date_debut_event").toLocalDate());
                e.setDateFin(rs.getDate("date_fin_event").toLocalDate());
                e.setHeureDebut(rs.getTime("heure_debut_event").toLocalTime());
                e.setHeureFin(rs.getTime("heure_fin_event").toLocalTime());
                e.setAdresse(rs.getString("adresse_event"));
                e.setImage(rs.getString("image_event"));
                e.setPrix(rs.getDouble("prix_event"));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }

    @Override
    public Evenement displayById(int id) {
        String req="select * from evenement where id_event ="+id;
           Evenement e=new Evenement();
        try {
            rs=st.executeQuery(req);
           
            rs.next();
                e.setId(rs.getInt("id_event"));
                e.setEventCategorie(EventCategorieDao.getInstance().displayById(rs.getInt("event_categorie_id")));
                e.setUser(UserDao.getInstance().displayById(rs.getInt("user_id")));
                e.setNomEvent(rs.getString("nom_event"));
                e.setDescription(rs.getString("description_event"));
                e.setDateDebut(rs.getDate("date_debut_event").toLocalDate());
                e.setDateFin(rs.getDate("date_fin_event").toLocalDate());
                e.setHeureDebut(rs.getTime("heure_debut_event").toLocalTime());
                e.setHeureFin(rs.getTime("heure_fin_event").toLocalTime());
                e.setAdresse(rs.getString("adresse_event"));
                e.setImage(rs.getString("image_event"));
                e.setPrix(rs.getDouble("prix_event"));
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return e;
        
    }
    
    
    public List<Evenement> displayAllByCategorie(int idCategorie) {
        String req="select * from evenement where event_categorie_id = "+idCategorie;
        ObservableList<Evenement> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Evenement e=new Evenement();
                e.setId(rs.getInt("id_event"));
                e.setEventCategorie(EventCategorieDao.getInstance().displayById(rs.getInt("event_categorie_id")));
                e.setUser(UserDao.getInstance().displayById(rs.getInt("user_id")));
                e.setNomEvent(rs.getString("nom_event"));
                e.setDescription(rs.getString("description_event"));
                e.setDateDebut(rs.getDate("date_debut_event").toLocalDate());
                e.setDateFin(rs.getDate("date_fin_event").toLocalDate());
                e.setHeureDebut(rs.getTime("heure_debut_event").toLocalTime());
                e.setHeureFin(rs.getTime("heure_fin_event").toLocalTime());
                e.setAdresse(rs.getString("adresse_event"));
                e.setImage(rs.getString("image_event"));
                e.setPrix(rs.getDouble("prix_event"));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }


    public List<Evenement> displayAllByUser(int idUser) {
        String req="select * from evenement where user_id = "+idUser;
        ObservableList<Evenement> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Evenement e=new Evenement();
                e.setId(rs.getInt("id_event"));
                e.setEventCategorie(EventCategorieDao.getInstance().displayById(rs.getInt("event_categorie_id")));
                e.setUser(UserDao.getInstance().displayById(rs.getInt("user_id")));
                e.setNomEvent(rs.getString("nom_event"));
                e.setDescription(rs.getString("description_event"));
                e.setDateDebut(rs.getDate("date_debut_event").toLocalDate());
                e.setDateFin(rs.getDate("date_fin_event").toLocalDate());
                e.setHeureDebut(rs.getTime("heure_debut_event").toLocalTime());
                e.setHeureFin(rs.getTime("heure_fin_event").toLocalTime());
                e.setAdresse(rs.getString("adresse_event"));
                e.setImage(rs.getString("image_event"));
                e.setPrix(rs.getDouble("prix_event"));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    
    public List<Evenement> displayAllByNom(String nom) {
        String req="select * from evenement where nom_event = '"+nom+"'";
        ObservableList<Evenement> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Evenement e=new Evenement();
                e.setId(rs.getInt("id_event"));
                e.setEventCategorie(EventCategorieDao.getInstance().displayById(rs.getInt("event_categorie_id")));
                e.setUser(UserDao.getInstance().displayById(rs.getInt("user_id")));
                e.setNomEvent(rs.getString("nom_event"));
                e.setDescription(rs.getString("description_event"));
                e.setDateDebut(rs.getDate("date_debut_event").toLocalDate());
                e.setDateFin(rs.getDate("date_fin_event").toLocalDate());
                e.setHeureDebut(rs.getTime("heure_debut_event").toLocalTime());
                e.setHeureFin(rs.getTime("heure_fin_event").toLocalTime());
                e.setAdresse(rs.getString("adresse_event"));
                e.setImage(rs.getString("image_event"));
                e.setPrix(rs.getDouble("prix_event"));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    
    
}
