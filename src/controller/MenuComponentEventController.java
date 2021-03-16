/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EventInvitationDao;
import dao.UserDao;
import entity.EventInvitation;
import entity.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class MenuComponentEventController extends VBox {

    @FXML
    private Button btnAddEvent;
    @FXML
    private Button EventsBtn;
    @FXML
    private Button eventOrganiseBtn;
    @FXML
    private Button eventParticpeBtn;
    @FXML
    private Button invitationBtn;
    @FXML
    private Label invitationLabel;
    @FXML
    private Button accueilBtn;
    @FXML
    private Button logoutBtn;

    User connectedUser;

    public MenuComponentEventController() {
        connectedUser = new User();
        UserDao userdao = UserDao.getInstance();
        connectedUser = userdao.displayById(Session.getConnectedUser().getIdUser());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MenuComponentEvent.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventInvitationDao invitdao = EventInvitationDao.getInstance();
        invitationLabel.setText("Vous avez " + invitdao.displayAllByUserInvite(connectedUser).size() + " invitation(s)");
    }

    @FXML
    private void afficherEventsBtn(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AccueilEvent.fxml"));
            accueilBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventOrganiseAction(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AfficherEventOrganise.fxml"));
            eventOrganiseBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventParticpeAction(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/CalendarEvent.fxml"));
            eventParticpeBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void invitationAction(ActionEvent event) {
        try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AfficherEventInvitation.fxml"));
            invitationBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addEventAction(ActionEvent event) {

        try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AjouterEvent.fxml"));
            btnAddEvent.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void logoutAction(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/login/FXMLLogin.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);
            
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void accueilAction(ActionEvent e) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/client/FXMLHomePage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuComponentEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
