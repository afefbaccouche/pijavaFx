/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EventParticipationDao;
import dao.UserDao;
import entity.Evenement;
import entity.EventParticipation;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class AfficherDetailsEventController implements Initializable {

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
    private Button participerBtn;
    @FXML
    private Button inviterBtn;
    @FXML
    private Button retourBtn;
    @FXML
    private AnchorPane container;

    Evenement selectedEvent;
    User user;
    UserDao userdao;

    public void verifParticipation(Evenement ev) {
        EventParticipationDao p = EventParticipationDao.getInstance();
        if (p.displayByUserEvent(user, ev).getId() != 0) {
            participerBtn.setStyle("-fx-background-color: #6c757d; ");
            participerBtn.setText("Ignorer participation");
        } else {
            participerBtn.setStyle("-fx-background-color:  #5cb85c; ");
            participerBtn.setText("Participer");
        }

    }

    public void initData(Evenement evenement) {
        selectedEvent = evenement;
        nomEvent.setText(selectedEvent.getNomEvent());
        prixTxt.setText(Double.toString(selectedEvent.getPrix()) + " DT");
        dateTxt.setText(selectedEvent.getDateDebut().toString() + " - " + selectedEvent.getDateFin().toString());
        adresseTxt.setText(selectedEvent.getAdresse());
        heureTxt.setText(selectedEvent.getHeureDebut().toString() + " - " + selectedEvent.getHeureFin().toString());
        descriptionTxt.setText(selectedEvent.getDescription());
        categorieTxt.setText(selectedEvent.getEventCategorie().getNomCategorie());
        Image image = new Image("file:C:/wamp64/www/imageUploads/" + selectedEvent.getImageProperty().get());
        imageEvent.setImage(image);

        verifParticipation(evenement);

    }

    public void refresh(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AfficherDetailsEvent.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);

            AfficherDetailsEventController controller = loader.getController();
            controller.initData(selectedEvent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getChildren().add(new MenuComponentEventController());

        user = new User();
        userdao = new UserDao();
        user = userdao.displayById(Session.getConnectedUser().getIdUser());

    }

    @FXML
    private void retourAction(ActionEvent event) {
        try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AccueilEvent.fxml"));
            retourBtn.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void participerAction(ActionEvent event) {

        EventParticipationDao epdao = EventParticipationDao.getInstance();

        if (epdao.displayByUserEvent(user, selectedEvent).getId() == 0) {

            Date date = new Date();
            EventParticipation ep = new EventParticipation(user, selectedEvent, new java.sql.Date(date.getTime()));
            epdao.add(ep);

        } else {
            epdao.delete(epdao.displayByUserEvent(user, selectedEvent));
        }

        refresh(event);

    }

    @FXML
    private void inviterAction(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AjouterInvitationEvent.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);

            AjouterInvitationEventController controller = loader.getController();
            controller.initData(selectedEvent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AfficherDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
