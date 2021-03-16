/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EventInvitationDao;
import dao.EventParticipationDao;
import dao.UserDao;
import entity.Evenement;
import entity.EventInvitation;
import entity.EventParticipation;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class RepondreEventInvitationController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ImageView imageEvent;
    @FXML
    private Text nomEvent;
    @FXML
    private Text dateTxt;
    @FXML
    private Text heureTxt;
    @FXML
    private Text prixTxt;
    @FXML
    private Text categorieTxt;
    @FXML
    private Text adresseTxt;
    @FXML
    private Text descriptionTxt;
    @FXML
    private Button refuserBtn;
    @FXML
    private Button accepterBtn;
    @FXML
    private Button retourBtn;

    Evenement selectedEvent;
    User connectedUser;
    EventInvitation selectedInvit;

    public void initDataEvent(Evenement evenement, EventInvitation invitation) {
        selectedEvent = evenement;

        nomEvent.setText(selectedEvent.getNomEvent());
        prixTxt.setText(Double.toString(selectedEvent.getPrix()) + " DT");
        dateTxt.setText(selectedEvent.getDateDebut().toString() + " - " + selectedEvent.getDateFin().toString());
        adresseTxt.setText(selectedEvent.getAdresse());
        heureTxt.setText(selectedEvent.getHeureDebut().toString() + " - " + selectedEvent.getHeureFin().toString());
        descriptionTxt.setText(selectedEvent.getDescription());
        //categorieTxt.setText(selectedEvent.getEventCategorie().getNomCategorie());
        Image image = new Image("file:C:/wamp64/www/imageUploads/" + selectedEvent.getImageProperty().get());
        imageEvent.setImage(image);

        selectedInvit = invitation;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getChildren().add(new MenuComponentEventController());

        connectedUser = new User();
        UserDao userdao = UserDao.getInstance();
        connectedUser = userdao.displayById(Session.getConnectedUser().getIdUser());
    }

    @FXML
    private void accpeterAction(ActionEvent event) {
        EventParticipationDao epdao = EventParticipationDao.getInstance();
        Date date = new Date();
        EventParticipation ep = new EventParticipation(connectedUser, selectedEvent, new java.sql.Date(date.getTime()));
        epdao.add(ep);

        EventInvitationDao invitdao = EventInvitationDao.getInstance();
        invitdao.updateInvit(selectedInvit, 1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez accepté l'invitation");
        alert.show();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherEventInvitation.fxml"));
            accepterBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RepondreEventInvitationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void refuserAction(ActionEvent event) {
        EventInvitationDao invitdao = EventInvitationDao.getInstance();
        invitdao.updateInvit(selectedInvit, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez refusé l'invitation");
        alert.show();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherEventInvitation.fxml"));
            accepterBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RepondreEventInvitationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
