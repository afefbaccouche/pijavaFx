/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Services;

import Don.IServices.IBesoinArgent;
import Don.entities.BesoinArgent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author jacem
 */
public class BesoinArgentServices implements IBesoinArgent {

    private Connection connection;

    public BesoinArgentServices() {
        connection = ConnectionBaseDonn.connect.getInstance().getCnx();
    }

    @Override
    public void ajouterBesoinArgent(BesoinArgent o) {
        try {
            String requete = "";
            if (String.valueOf(o.getIdMaisonRetraite()).equals("")) {
                requete = "INSERT INTO besoin_argent (titreBesoinArgent,idUserInNeed,disponibiliteBesoin,montantBesoin) VALUES(?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
                pst.setString(1, o.getTitreBesoinArgent());
                pst.setString(2, o.getUserInNeed());
                pst.setInt(3, o.getDisponibilite());
                pst.setDouble(4, o.getMontantArgent());
                pst.executeUpdate();
            } else if (o.getUserInNeed().equals("")) {
                requete = "INSERT INTO besoin_argent (idMaisonRetraite,titreBesoinArgent,disponibiliteBesoin,montantBesoin) VALUES(?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
                pst.setInt(1, o.getIdMaisonRetraite());
                pst.setString(2, o.getTitreBesoinArgent());
                pst.setInt(3, o.getDisponibilite());
                pst.setDouble(4, o.getMontantArgent());
                pst.executeUpdate();
            } else {
                requete = "INSERT INTO besoin_argent (idMaisonRetraite,titreBesoinArgent,idUserInNeed,disponibiliteBesoin,montantBesoin) VALUES(?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
                pst.setInt(1, o.getIdMaisonRetraite());
                pst.setString(2, o.getTitreBesoinArgent());
                pst.setString(3, o.getUserInNeed());
                pst.setInt(4, o.getDisponibilite());
                pst.setDouble(5, o.getMontantArgent());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerBesoinArgent(BesoinArgent o) {
        try {
            String requete = "DELETE FROM besoin_argent WHERE idBesoin = ? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, o.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierBesoinArgent(String object, Object obj, int id) {
        
        try {
            String requete = "UPDATE besoin_argent SET ? = ? WHERE idBesoin= ? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, object);
            pst.setObject(2, obj);
            pst.setInt(3, id);
            String ch = pst.toString().replaceFirst("\'", "");
            String ch2 = ch.replaceFirst("\'", "");
            int pos = ch2.indexOf("UPDATE");
            String ch3 = ch2.substring(pos, ch2.length());
            pst = connection.prepareStatement(ch3);
            pst.executeUpdate();
            System.out.println("BesoiARgent modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<BesoinArgent> consulterBesoinArgent() {
        List<BesoinArgent> myList = new ArrayList<>();
        try {

            Statement pst = connection.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * from besoin_argent");
            while (rs.next()) {
                int id = rs.getInt("idBesoin");
                int idMaisonRetraite = rs.getInt("idMaisonRetraite");
                int dispo = rs.getInt("disponibiliteBesoin");
                String titre = rs.getString("titreBesoinArgent");
                String user = rs.getString("idUserInNeed");
                Double montantArgent = rs.getDouble("montantBesoin");
                BesoinArgent o = new BesoinArgent(id,idMaisonRetraite, titre, user, dispo, montantArgent);
                myList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

}
