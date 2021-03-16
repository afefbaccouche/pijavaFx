/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Evenement;
import entity.EventParticipation;
import entity.User;
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
public class EventParticipationDao implements IEventdao<EventParticipation> {

    private static EventParticipationDao instance;
    private Statement st;
    private ResultSet rs;

    public EventParticipationDao() {
        connect cs = connect.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static EventParticipationDao getInstance() {
        if (instance == null) {
            instance = new EventParticipationDao();
        }
        return instance;
    }

    @Override
    public void add(EventParticipation t) {

        try {

            String req = "insert into event_participation (user_id,evenement_id,date_participation)values("
                    + t.getUser().getIdUser() + ","
                    + t.getEvenement().getId() + ",'"
                    + t.getDateParticipation() + "')";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean update(EventParticipation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(EventParticipation t) {
        String req = "delete from event_participation where id_event_participation=" + t.getId();
        EventParticipation e = displayById(t.getId());

        if (e != null) {
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }

    }

    @Override
    public List<EventParticipation> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventParticipation displayById(int id) {

        EventParticipation p = new EventParticipation();
        EvenementDao edao = new EvenementDao();
        UserDao userdao = new UserDao();

        try {
            String req = "select * from event_participation where id_event_participation =" + id;

            rs = st.executeQuery(req);

            rs.next();

            p.setId(rs.getInt("id_event_participation"));
            p.setUser(userdao.displayById(rs.getInt("user_id")));
            p.setEvenement(edao.displayById(rs.getInt("evenement_id")));
            p.setDateParticipation(rs.getDate("date_participation"));

        } catch (SQLException ex) {
            Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

    public EventParticipation displayByUserEvent(User user, Evenement evenement) {

        EventParticipation p = new EventParticipation();
        EvenementDao edao = new EvenementDao();
        UserDao userdao = new UserDao();

        //System.out.println("EventParticipationUser=="+user);
        //System.out.println("EventParticipationEv=="+evenement);
        try {
            String req = "select * from event_participation where user_id =" + user.getIdUser() + " and evenement_id=" + evenement.getId();

            rs = st.executeQuery(req);

            rs.next();

            p.setId(rs.getInt("id_event_participation"));
            p.setUser(userdao.displayById(rs.getInt("user_id")));
            p.setEvenement(edao.displayById(rs.getInt("evenement_id")));
            p.setDateParticipation(rs.getDate("date_participation"));

        } catch (SQLException ex) {
            Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    public List<EventParticipation> displayAllByEvent(int idEvent) {
        String req = "select * from event_participation where evenement_id = " + idEvent;
        ObservableList<EventParticipation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                EventParticipation p = new EventParticipation();
                p.setId(rs.getInt("id_event_participation"));
                p.setUser(new UserDao().displayById(rs.getInt("user_id")));
                p.setEvenement(new EvenementDao().displayById(rs.getInt("evenement_id")));
                p.setDateParticipation(rs.getDate("date_participation"));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<EventParticipation> displayAllByUser(int idUser) {

        String req = "select * from event_participation where user_id = " + idUser;
        ObservableList<EventParticipation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                EventParticipation p = new EventParticipation();
                p.setId(rs.getInt("id_event_participation"));
                p.setUser(new UserDao().displayById(rs.getInt("user_id")));
                p.setEvenement(new EvenementDao().displayById(rs.getInt("evenement_id")));
                p.setDateParticipation(rs.getDate("date_participation"));

                list.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(EventParticipationDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        return list;
    }

}
