/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ConnectionBaseDonn.connect;
import entity.Centre;
import entity.Promotion;
import java.sql.Connection;
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
public class PromotionDao {

    private Statement st;
    private ResultSet rs;
    private static PromotionDao instance;

    private PromotionDao() {

    }

    public static PromotionDao getInstance() {
        if (instance == null) {
            return new PromotionDao();
        }
        return instance;
    }

    public ArrayList<Promotion> getAll() {
        ArrayList<Promotion> listPromotions = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM promotioncentre");

            while (rs.next()) {
                Promotion promotion = new Promotion();
                promotion.setId(rs.getInt("id"));
                promotion.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                promotion.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                promotion.setReduction(rs.getInt("reduction"));
                promotion.setDateDebut(rs.getDate("date_debut"));
                promotion.setDateFin(rs.getDate("date_fin"));
                promotion.setNouveauPrix(rs.getFloat("nouveau_prix"));

                listPromotions.add(promotion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCentreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPromotions;

    }

    public boolean add(Promotion p) {
        boolean isAdded = false;
        String req = "insert into promotioncentre (Centre_id,reduction,nouveau_prix,date_debut,date_fin,User_id) values (?,?,?,?,?,?)";
        try {
            Connection connection = connect.getInstance().getCnx();
            PreparedStatement pst;
            pst = connection.prepareStatement(req);
            pst.setInt(1, p.getCentre().getId());
            pst.setInt(2, p.getReduction());

            pst.setFloat(3, p.getNouveauPrix());
            pst.setDate(4, p.getDateDebut());
            pst.setDate(5, p.getDateFin());
            pst.setInt(6, p.getManager().getIdUser());

            int result = pst.executeUpdate();
            if (result > 0) {
                isAdded = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

    public ArrayList<Promotion> getByCentre(int centreId) {
        ArrayList<Promotion> listPromotions = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM promotioncentre WHERE Centre_id=" + centreId);

            while (rs.next()) {
                Promotion promotion = new Promotion();
                promotion.setId(rs.getInt("id"));
                promotion.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                promotion.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                promotion.setReduction(rs.getInt("reduction"));
                promotion.setDateDebut(rs.getDate("date_debut"));
                promotion.setDateFin(rs.getDate("date_fin"));
                promotion.setNouveauPrix(rs.getFloat("nouveau_prix"));

                listPromotions.add(promotion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCentreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPromotions;

    }

    public ArrayList<Promotion> getByManager(int managerId) {
        ArrayList<Promotion> listPromotions = new ArrayList();
        try {
            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM promotioncentre WHERE User_id=" + managerId);

            while (rs.next()) {
                Promotion promotion = new Promotion();
                promotion.setId(rs.getInt("id"));
                promotion.setCentre(CentreDao.getInstance().findById(rs.getInt("Centre_id")));
                promotion.setManager(UserDao.getInstance().findById(rs.getInt("User_id")));
                promotion.setReduction(rs.getInt("reduction"));
                promotion.setDateDebut(rs.getDate("date_debut"));
                promotion.setDateFin(rs.getDate("date_fin"));
                promotion.setNouveauPrix(rs.getFloat("nouveau_prix"));

                listPromotions.add(promotion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCentreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPromotions;

    }

    public boolean delete(Promotion promotion) {
        boolean isDeleted = false;
        try {

            Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
            Statement ste = connection.createStatement();

            int result = ste.executeUpdate("DELETE FROM promotioncentre\n"
                    + "WHERE id=" + promotion.getId());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }
    
    
}

/*   } catch (SQLException ex) {
                Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            //SELECT (ancien_prix*reduction/100) from promotion WHERE libelle=5
            //UPDATE promotion set nouveau_prix=(SELECT (ancien_prix*reduction/100)) WHERE libelle=5;
            PreparedStatement st; 
            String req1="SELECT (prix-(prix*reduction/100)) from centre where id=?"; 
           st = c.prepareStatement(req1);
           st.setInt(1,p.getLibelle());
           ResultSet rs = st.executeQuery();
            while(rs.next())
        {   
             String query = "UPDATE promotion set nouveau_prix = ? where libelle=?";   
             
            PreparedStatement pt =c.prepareStatement(query);
            p.setNouveau_prix(rs.getFloat(1));
            pt.setFloat(1,p.getNouveau_prix());
            pt.setInt(2,p.getLibelle());
            pt.executeUpdate();
         
        // System.out.println("nouveau prix"+rs.getFloat(1));  
        }  
            
        } catch (SQLException ex) {
            Logger.getLogger(servicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }} */
