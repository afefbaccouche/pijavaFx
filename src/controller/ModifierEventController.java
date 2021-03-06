/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import dao.UserDao;
import dao.EvenementDao;
import dao.EventCategorieDao;
import entity.Evenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Baklouti
 */
public class ModifierEventController implements Initializable {

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
    private Button btnEditEvent;
    @FXML
    private Button btnRetour;
    @FXML
    private Label imageFileLabel;
    @FXML
    private AnchorPane container;

    private Evenement selectedEvent;
    FileChooser fc;
    File file;
    String location, format;
    DateFormat dateformatImage;

    public void initData(Evenement evenement) {
        selectedEvent = evenement;
        nomEvent.setText(selectedEvent.getNomEvent());
        prix.setText(Double.toString(selectedEvent.getPrix()));
        dateDebut.setValue(selectedEvent.getDateDebut());
        dateFin.setValue(selectedEvent.getDateFin());
        adresse.setText(selectedEvent.getAdresse());
        heureDebut.setValue(selectedEvent.getHeureDebut());
        heureFin.setValue(selectedEvent.getHeureFin());
        description.setText(selectedEvent.getDescription());

        categorie.setValue(selectedEvent.getEventCategorie());

    }

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
            afficherAlert("Tous les champs doivent ??tre remplis");
            return false;
        }
        Instant instant = Instant.from(dateDebut.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date dateD = Date.from(instant);
        Date cuurentDate = new Date();
        if (dateD.compareTo(cuurentDate) < 0) {

            afficherAlert("Date debut doit ??tre sup??rieur ?? la date d'aujoud'hui");
            return false;
        }
        if (dateDebut.getValue().compareTo(dateFin.getValue()) > 0) {
            afficherAlert("Date fin doit ??tre sup??rieur ou ??gal ?? la date de debut");
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

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        container.getChildren().add(new MenuComponentEventController());

        EventCategorieDao catDao = new EventCategorieDao();
        catDao.displayAll().forEach((cat) -> {
            categorie.getItems().add(cat.getNomCategorie());

        });

        btnRetour.setOnAction(e -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/events/AccueilEvent.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnImage.setOnAction(e -> {
            fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            file = fc.showOpenDialog(null);

            if (file != null) {
                imageFileLabel.setText(file.getName());

            }

        });

        btnEditEvent.setOnAction(e -> {

            if (testSaisie()) {
                String imageData = "";

                try {

                    BufferedImage imag = ImageIO.read(file);

                    dateformatImage = new SimpleDateFormat("yyyyMMddHHmmssSSS");

                    imageData = dateformatImage.format(new Date()) + file.getName();
                    location = "C:/wamp64/www/imageUploads/" + imageData;
                    // location=location.replace("\\", "/");

                    format = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
                    ImageIO.write(imag, format, new File(location));

                } catch (IOException ex) {
                    Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
                }

                EventCategorieDao catdao = EventCategorieDao.getInstance();
                UserDao userdao = UserDao.getInstance();

                Evenement ev = new Evenement(selectedEvent.getId(), catdao.displayByName(categorie.getValue().toString()), userdao.displayById(24), nomEvent.getText(), description.getText(),
                        dateDebut.getValue(), dateFin.getValue(), heureDebut.getValue(), heureFin.getValue(),
                        adresse.getText(), Double.parseDouble(prix.getText()), imageData);

                EvenementDao evdao = EvenementDao.getInstance();
                evdao.update(ev);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("evenement modifi?? avec succ??s!");
                alert.show();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherEventOrganise.fxml"));
                    btnEditEvent.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

}
