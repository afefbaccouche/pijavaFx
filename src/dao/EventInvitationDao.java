/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.EventInvitation;
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
public class EventInvitationDao implements IEventdao<EventInvitation> {

    private static EventInvitationDao instance;
    private Statement st;
    private ResultSet rs;

    public EventInvitationDao() {
        connect cs = connect.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventInvitationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static EventInvitationDao getInstance() {
        if (instance == null) {
            instance = new EventInvitationDao();
        }
        return instance;
    }

    @Override
    public void add(EventInvitation t) {
        try {
            String req = "insert into event_invitation (user_id,user_invite_id,evenement_id,date_invitation)values("
                    + t.getUser().getIdUser() + ","
                    + t.getUserInvite().getIdUser() + ","
                    + t.getEvenement().getId() + ",'"
                    + t.getDate() + "')";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventInvitationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean updateInvit(EventInvitation t, int reponse) {
        try {
            String qry = "UPDATE event_invitation SET reponse_invitation = " + reponse
                    + " WHERE id_event_invitation = " + t.getIdInvitation();
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventInvitationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    @Override
    public void delete(EventInvitation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventInvitation> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventInvitation displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EventInvitation> displayAllByUserInvite(User userInvite) {
        String req = "select * from event_invitation where user_invite_id = " + userInvite.getIdUser()
                + " and reponse_invitation is NULL";
        ObservableList<EventInvitation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                EventInvitation invit = new EventInvitation();
                invit.setIdInvitation(rs.getInt("id_event_invitation"));
                invit.setUser(new UserDao().displayById(rs.getInt("user_id")));
                invit.setUserInvite(new UserDao().displayById(rs.getInt("user_invite_id")));
                invit.setEvenement(new EvenementDao().displayById(rs.getInt("evenement_id")));
                invit.setReponse(rs.getBoolean("reponse_invitation"));
                invit.setDate(rs.getDate("date_invitation"));

                list.add(invit);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventInvitationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public boolean update(EventInvitation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
