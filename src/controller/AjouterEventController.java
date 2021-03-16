/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import dao.LoginDao;
import dao.UserDao;

import dao.EvenementDao;
import dao.EventCategorieDao;
import entity.User;
import entity.Evenement;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javax.imageio.ImageIO;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AjouterEventController implements Initializable {

    @FXML
    private TextField nomEvent;
    @FXML
    private TextField prix;
    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXTimePicker heureFin;
    @FXML
    private TextArea description;
    @FXML
    private TextArea adresse;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private ComboBox categorie;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnAddEvent;

    @FXML
    private Label imageFileLabel;
    @FXML
    private Label imageLabelInvisible;
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
        
        System.out.println("compare="+dateDebut.getValue().compareTo(dateFin.getValue()));

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
        if (dateDebut.getValue().compareTo(dateFin.getValue()) == 0) {
            if (heureDebut.getValue().compareTo(heureFin.getValue()) > 0) {
                afficherAlert("Heure fin doit être supérieur à l'heure de début");
                return false;
            }
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        container.getChildren().add(new MenuComponentEventController());

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

                    format = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
                    ImageIO.write(imag, format, new File(location));

                } catch (IOException ex) {
                    Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
                }

                EventCategorieDao catdao = EventCategorieDao.getInstance();
                UserDao userdao = UserDao.getInstance();
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

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/AccueilEvent.fxml"));
                    btnAddEvent.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

    }

}
