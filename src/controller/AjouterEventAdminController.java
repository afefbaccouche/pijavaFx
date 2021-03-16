/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Admin.home1.MenuAdminController;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import dao.UserDao;
import dao.EvenementDao;
import dao.EventCategorieDao;
import entity.Evenement;
import entity.EventCategorie;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AjouterEventAdminController implements Initializable {

    @FXML
    private TextField nomEvent;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextArea adresse;
    @FXML
    private TextField prix;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnRetour;
    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXTimePicker heureFin;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private Label imageFileLabel;
    @FXML
    private AnchorPane container;

    FileChooser fc;
    File file;
    String location, format;
    DateFormat dateformatImage;


    public void afficherAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public boolean testSaisie() {

        if (nomEvent.getText().trim().isEmpty() || prix.getText().trim().isEmpty()
                || description.getText().trim().isEmpty()
                || adresse.getText().trim().isEmpty()
                || categorie.getValue() == null
                || imageFileLabel.getText().trim().isEmpty()) {
            afficherAlert("Tous les champs doivent être remplis");
            return false;
        }
        Instant instant = Instant.from(dateDebut.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date dateD = Date.from(instant);
        Date cuurentDate = new Date();
        if (dateD.compareTo(cuurentDate) < 0) {

            afficherAlert("Date debut doit être supérieur à la date d'aujoud'hui");
            return false;
        }
        if (dateDebut.getValue().compareTo(dateFin.getValue()) > 0) {
            afficherAlert("Date fin doit être supérieur ou égal à la date de debut");
            return false;
        }
        if (heureDebut.getValue().compareTo(heureFin.getValue()) > 0) {
            afficherAlert("Heure fin doit être supérieur à l'heure de début");
            return false;
        }
        if (heureDebut.getValue().compareTo(heureFin.getValue()) > 0) {
            afficherAlert("Heure fin doit être supérieur à l'heure de début");
            return false;
        }
        try {
            Double num = Double.parseDouble(prix.getText());
        } catch (NumberFormatException e) {
            afficherAlert("Champs prix invalide");
            return false;
        }
        return true;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        container.getChildren().add(new MenuAdminController());

        EventCategorieDao catDao = new EventCategorieDao();
        catDao.displayAll().forEach((cat) -> {
            categorie.getItems().add(cat.getNomCategorie());

        });

        btnImage.setOnAction(e -> {
            fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            file = fc.showOpenDialog(null);

            if (file != null) {
                imageFileLabel.setText(file.getName());

            }

        });

        btnAddEvent.setOnAction(e -> {
            if (testSaisie()) {

                String imageData = "";

                try {

                    BufferedImage imag = ImageIO.read(file);

                    dateformatImage = new SimpleDateFormat("yyyyMMddHHmmssSSS");

                    imageData = dateformatImage.format(new Date()) + file.getName();
                    location = "C:/wamp64/www/imageUploads/" + imageData;
                    //location = location.replace("\\", "/");

                    format = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
                    ImageIO.write(imag, format, new File(location));

                } catch (IOException ex) {
                    Logger.getLogger(AjouterEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

                EventCategorieDao catdao = new EventCategorieDao();
                UserDao userdao = new UserDao();
                
                User connectedUser = UserDao.getInstance().displayById(Session.getConnectedUser().getIdUser());

                Evenement ev = new Evenement(catdao.displayByName(categorie.getValue().toString()), userdao.displayById(connectedUser.getIdUser()), nomEvent.getText(), description.getText(),
                        dateDebut.getValue(), dateFin.getValue(), heureDebut.getValue(), heureFin.getValue(),
                        adresse.getText(), Double.parseDouble(prix.getText()), imageData);

                EvenementDao evdao = EvenementDao.getInstance();
                evdao.add(ev);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("evenement insérée avec succés!");
                alert.show();
            }
        });

    }

}
