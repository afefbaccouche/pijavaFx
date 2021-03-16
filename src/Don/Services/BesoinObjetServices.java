/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Services;

import Don.IServices.IBesoinObjet;
import Don.entities.BesoinObjet;
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
public class BesoinObjetServices implements IBesoinObjet {

    private Connection connection;

    public BesoinObjetServices() {
        connection = ConnectionBaseDonn.connect.getInstance().getCnx();
    }

    @Override
    public void ajouterBesoinObjet(BesoinObjet o) {
        try {
            String requete = "";
            if (String.valueOf(o.getIdMaisonRetraite()).equals("")) {
                requete = "INSERT INTO besoin_objet (titreBesoinObjet,idUserInNeed,idMaisonRetraite,disponibiliteBesoin,typeBesoinObjet) VALUES(?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
                pst.setString(1, o.getTitreBesoinObjet());
                pst.setString(2, o.getUserInNeed());
                //pst.setInt(3, o.getIdMaisonRetraite());
                pst.setInt(3, o.getDisponibilite());
                pst.setString(4, o.getTypeBesoinObjet());
                pst.executeUpdate();
            } else if (o.getUserInNeed().equals("")) {
                requete = "INSERT INTO besoin_objet (titreBesoinObjet,idMaisonRetraite,disponibiliteBesoin,typeBesoinObjet) VALUES(?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
                pst.setInt(1, o.getIdMaisonRetraite());
                pst.setString(2, o.getTitreBesoinObjet());
                pst.setInt(3, o.getDisponibilite());
                pst.setString(4, o.getTypeBesoinObjet());
                pst.executeUpdate();
            } else {
                requete = "INSERT INTO besoin_objet (idMaisonRetraite,titreBesoinObjet,idUserInNeed,disponibiliteBesoin,typeBesoinObjet) VALUES(?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
                pst.setInt(1, o.getIdMaisonRetraite());
                pst.setString(2, o.getTitreBesoinObjet());
                pst.setString(3, o.getUserInNeed());
                pst.setInt(4, o.getDisponibilite());
                pst.setString(5, o.getTypeBesoinObjet());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerBesoinObjet(BesoinObjet o) {
        try {
            String requete = "DELETE FROM besoin_objet WHERE idBesoin = ? ";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, o.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierBesoinObjet(String object, Object obj, int id) {
        
        try {
            String requete = "UPDATE besoin_objet SET ? = ? WHERE idBesoin= ? ";
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
            System.out.println("BesoiObjet modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<BesoinObjet> consulterBesoinObjet() {
        List<BesoinObjet> myList = new ArrayList<>();
        try {

            Statement pst = connection.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * from besoin_objet");
            while (rs.next()) {
                int id = rs.getInt("idBesoin");
                int idMaisonRetraite = rs.getInt("idMaisonRetraite");
                int dispo = rs.getInt("disponibiliteBesoin");
                String titre = rs.getString("titreBesoinObjet");
                String user = rs.getString("idUserInNeed");
                String typeBesoinObjet = rs.getString("typeBesoinObjet");
                BesoinObjet o = new BesoinObjet(id,idMaisonRetraite, titre, user, dispo, typeBesoinObjet);
                myList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    @Override
    public void modifierBesoinObjet(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
