/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Article;
import entity.Centre;
import entity.ReservationCentre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esprit
 */
public class ReservationCentreDao {

    private Statement st;
    private ResultSet rs;
    private static ReservationCentreDao instance;

    private ReservationCentreDao() {

    }

    public static ReservationCentreDao getInstance() {
        if (instance == null) {
            return new ReservationCentreDao();
        }
        return instance;
    }

    public ArrayList<ReservationCentre> getAll() {
        ArrayList<ReservationCentre> listReservations = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM reservation_centre");

            while (rs.next()) {
                ReservationCentre reservation = new ReservationCentre();
                reservation.setId(rs.getInt("id"));
                reservation.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                reservation.setClient(UserDao.getInstance().findById(rs.getInt("User_id")));
                reservation.setNombreJ(rs.getInt("nbrJour"));
                reservation.setDateD(rs.getDate("dateD"));
                reservation.setDateF(rs.getDate("dateF"));

                listReservations.add(reservation);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCentreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservations;
    }

    public boolean add(ReservationCentre reservation, Float prixPromotion) {
        boolean isAdded = false;
        try {

            if (prixPromotion == null) {
                String req = "insert into reservation_centre (Centre_id,nbrJour,dateD,dateF,User_id) values(?,?,?,?,?)";

                Connection connection = connect.getInstance().getCnx();
                PreparedStatement pst;
                pst = connection.prepareStatement(req);
                pst.setInt(1, reservation.getCentre().getId());
                pst.setInt(2, reservation.getNombreJ());
                pst.setDate(3, reservation.getDateD());
                pst.setDate(4, reservation.getDateF());
                pst.setInt(5, reservation.getClient().getIdUser());
                int result = pst.executeUpdate();
                if (result > 0) {
                    isAdded = true;
                }
            } else {
                String req = "insert into reservation_centre (Centre_id,nbrJour,dateD,dateF,User_id,prix_promotio) values(?,?,?,?,?,?)";

                Connection connection = connect.getInstance().getCnx();
                PreparedStatement pst;
                pst = connection.prepareStatement(req);
                pst.setInt(1, reservation.getCentre().getId());
                pst.setInt(2, reservation.getNombreJ());
                pst.setDate(3, reservation.getDateD());
                pst.setDate(4, reservation.getDateF());
                pst.setInt(5, reservation.getClient().getIdUser());
                pst.setFloat(6, prixPromotion);
                int result = pst.executeUpdate();
                if (result > 0) {
                    isAdded = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

    public ArrayList<ReservationCentre> getByDateReservation(int centreId, Date dateDebut) {
        ArrayList<ReservationCentre> listReservations = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            System.out.println("FROM DAO DATE: " + dateDebut);
            System.out.println("FROM DAO DATE: " + dateDebut.toString());
            System.out.println("FROM DAO DATE: " + centreId);
            ResultSet rs = ste.executeQuery("SELECT * FROM reservation_centre where dateD = " + "'" + dateDebut.toString() + "'" + " && Centre_id=" + centreId);

            while (rs.next()) {
                ReservationCentre reservation = new ReservationCentre();
                reservation.setId(rs.getInt("id"));
                reservation.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                reservation.setClient(UserDao.getInstance().findById(rs.getInt("User_id")));
                reservation.setNombreJ(rs.getInt("nbrJour"));
                reservation.setDateD(rs.getDate("dateD"));
                reservation.setDateF(rs.getDate("dateF"));

                listReservations.add(reservation);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservations;
    }

    public ArrayList<ReservationCentre> getByBeforeDateReservation(int centreId, Date dateDebut) {
        ArrayList<ReservationCentre> listReservations = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            System.out.println("FROM DAO DATE: " + dateDebut);
            System.out.println("FROM DAO DATE: " + dateDebut.toString());
            System.out.println("FROM DAO DATE: " + centreId);
            ResultSet rs = ste.executeQuery("SELECT * FROM reservation_centre where dateD < " + "'" + dateDebut.toString() + "'" + " && Centre_id=" + centreId);

            while (rs.next()) {
                ReservationCentre reservation = new ReservationCentre();
                reservation.setId(rs.getInt("id"));
                reservation.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                reservation.setClient(UserDao.getInstance().findById(rs.getInt("User_id")));
                reservation.setNombreJ(rs.getInt("nbrJour"));
                reservation.setDateD(rs.getDate("dateD"));
                reservation.setDateF(rs.getDate("dateF"));

                listReservations.add(reservation);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservations;
    }

    public ArrayList<ReservationCentre> getAllByCentre(int centreId) {
        ArrayList<ReservationCentre> listReservations = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            ResultSet rs = ste.executeQuery("SELECT * FROM reservation_centre where Centre_id = " + centreId + " order by dateD ASC");

            while (rs.next()) {
                ReservationCentre reservation = new ReservationCentre();
                reservation.setId(rs.getInt("id"));
                reservation.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                reservation.setClient(UserDao.getInstance().findById(rs.getInt("User_id")));
                reservation.setNombreJ(rs.getInt("nbrJour"));
                reservation.setDateD(rs.getDate("dateD"));
                reservation.setDateF(rs.getDate("dateF"));

                listReservations.add(reservation);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservations;
    }

}
