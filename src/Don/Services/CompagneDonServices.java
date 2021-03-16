/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Services;

import Don.IServices.ICompagneDon;
import Don.entities.CompagneDon;
import java.sql.Connection;
import java.util.Date;
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
public class CompagneDonServices implements ICompagneDon {

    private Connection connection;

    public CompagneDonServices() {
        connection = ConnectionBaseDonn.connect.getInstance().getCnx();
    }

    @Override
    public void ajouterCompagneDon(CompagneDon o) {
         try {
       /* String query="SELECT MAX(`idCompagneDon`) FROM `compagne_don`";
        Statement st=connection.createStatement();
        ResultSet rs= st.executeQuery(query);
        if(rs.next()){
        o.setId(rs.getInt("idCompagneDon"));
        }*/
            
            String requete = "INSERT INTO `compagne_don`(`titreCompagneDon`, `dateFinCompagneDon`, `descriptionCompagneDon`, `nombreParticipantsCompagneDon`) VALUES(?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(requete);
               
                pst.setString(1, o.getTitre());
                pst.setDate(2, new java.sql.Date(o.getDateFin().getTime()));
                pst.setString(3, o.getDescription());
                pst.setInt(4, o.getNbrParticipation());
                pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCompagneDon(CompagneDon cp) {
        try {
            String requete = "DELETE FROM `compagne_don` WHERE `idCompagneDon`="+cp.getId();
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierCompagneDon(CompagneDon cp) {
        
        try {
            System.out.println(" modifier plz");
            String requete = "UPDATE `compagne_don` SET `titreCompagneDon`=?, `descriptionCompagneDon`=? WHERE `idCompagneDon`="+cp.getId();
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, cp.getTitre());
            pst.setString(2, cp.getDescription());
            pst.executeUpdate();
            System.out.println("CompagneDon modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<CompagneDon> consulterCompagneDon() {
        List<CompagneDon> myList = new ArrayList<CompagneDon>();
        try {

            Statement pst = connection.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * FROM `compagne_don`");
            while (rs.next()) {
                int id = rs.getInt("idCompagneDon");
                String titre = rs.getString("titreCompagneDon");
                String description = rs.getString("descriptionCompagneDon");
                int nbr = rs.getInt("nombreParticipantsCompagneDon");
                Date date= rs.getDate("dateFinCompagneDon");
                CompagneDon o = new CompagneDon(id,titre, description,date, nbr);
                myList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    @Override
    public void modifierCompagneDon(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CompagneDon> CompagneDon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
