/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import Admin.home.MenuAdminController;
import ConnectionBaseDonn.connect;
import dao.PromotionDao;
import entity.Centre;
import entity.Promotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import session.Session;

import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DateUtils;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLAjouterPromotionController implements Initializable {

    public static Centre selectedCentre = null;
    @FXML
    private Label Ancien_prix;
    private TextField Reduction;
    @FXML
    private Label Nouveau_prix;
    @FXML
    private ImageView pic;
    @FXML
    private Label Date_debut;
    @FXML
    private DatePicker dateF;
    @FXML
    private Label prixError;
    @FXML
    private Label labelCentre;
    @FXML
    private DatePicker DateD;
    @FXML
    private Label Reductionlab;
    @FXML
    private Label Date_fin;
    @FXML
    private Label Reductionr;
    @FXML
    private Label Datedd;
    @FXML
    private Label Dateff;
    @FXML
    private Label cntr;
    private ProgressBar progressBar;
    @FXML
    private Slider slider;
    @FXML
    private Label spinnerValLabel;

    private float newPrice = 0;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         root.getChildren().addAll(new MenuAdminController());
       
        try{
            pic.setImage(new Image(selectedCentre.getImage()));
        }catch(Exception e){
            
        }
       
        labelCentre.setText("Reserver centre: " + selectedCentre.getNom());

        Ancien_prix.setText(selectedCentre.getPrix() + "DT/JOUR");
        Nouveau_prix.setText("0DT");

        /*
        Reduction.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            if (!newValue.isEmpty()) {
                try {
                    int valInt = Integer.parseInt(newValue);
                    int a = 1;
                    float y = (float) valInt / 100;
                    float x = 1 - y;
                    System.out.println("valInt " + valInt);
                    System.out.println("y " + y);
                    System.out.println("x " + x);
                    float priceTotal = x * selectedCentre.getPrix();
                    Nouveau_prix.setText(priceTotal + "DT");
                    prixError.setText("");

                } catch (Exception e) {
                    prixError.setText("must be number");
                    Nouveau_prix.setText("0DT");
                    System.out.println("ERROR NUMBER PRICE");
                    return;
                }
            } else {
                prixError.setText("");
                Nouveau_prix.setText("0DT");
            }

        });
         */
        slider.setMin(10);
        slider.setMax(90);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        /*
         slider.setMajorTickUnit(35);
        slider.setMinorTickCount(5);
         */
        slider.setBlockIncrement(1);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                System.out.println("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
                spinnerValLabel.setText(newValue.intValue() + "%");

                int percent = newValue.intValue();
                float y = (float) percent / 100;
                float x = 1 - y;
                System.out.println("percent " + percent);
                System.out.println("y (/100) " + y);
                System.out.println("x (1-) " + x);
                float priceTotal = x * selectedCentre.getPrix();
                Nouveau_prix.setText(priceTotal + "DT");
                newPrice = priceTotal;
                prixError.setText("");

            }
        });
    }

    @FXML
    private void add(ActionEvent event) throws ParseException, SQLException {

        boolean b = saisie();
        if (b == true) {
            Promotion promotion = new Promotion();
            promotion.setCentre(selectedCentre);
            promotion.setManager(Session.getConnectedUser());
            promotion.setReduction((int) slider.getValue());
            promotion.setNouveauPrix(newPrice);
            java.util.Date dateDebut = DateUtils.convertLocalDateToUtilDate(DateD.getValue());
            java.util.Date dateFin = DateUtils.convertLocalDateToUtilDate(dateF.getValue());
            promotion.setDateDebut(DateUtils.convertUtilDateToSqlDate(dateDebut));
            promotion.setDateFin(DateUtils.convertUtilDateToSqlDate(dateFin));
            boolean isAdded = PromotionDao.getInstance().add(promotion);
            if (isAdded) {
                Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                errorAlert.setContentText("SUCCESS");
                errorAlert.show();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("ERROR");
                errorAlert.show();
            }

        }
    }

    private boolean saisie() {
        boolean a = true;

        /*
         if (Reduction.getText().isEmpty()) {
            Reductionlab.setStyle("-fx-text-fill: red; -fx-font-size: 12px;-fx-border-color: red ;");
            Reductionlab.setText("ce champ est obligatoire");
            a = false;
        } else {
            Reductionlab.setStyle("-fx-font-size: 0px;");
            Reductionlab.setText("");
            ////METTRE LE BORDURE DE TEXTE FIELD EN VERT 
            Reduction.setStyle("-fx-border-color: green ;");

        }
         */
        if (DateD.getValue() == null) {
            Date_debut.setStyle("-fx-text-fill: red; -fx-font-size: 12px;-fx-border-color: red ;");
            Date_debut.setText("ce champ est obligatoire");
            a = false;

        } else {
            Date_debut.setStyle("-fx-font-size: 0px;");
            Date_debut.setText("");
            ////METTRE LE BORDURE DE TEXTE FIELD EN VERT 
            DateD.setStyle("-fx-border-color: green ;");
        }
        if (dateF.getValue() == null) {
            Date_fin.setStyle("-fx-text-fill: red; -fx-font-size: 12px;-fx-border-color: red ;");
            Date_fin.setText("ce champ est obligatoire");
            a = false;

        } else {
            Date_fin.setStyle("-fx-font-size: 0px;");
            Date_fin.setText("");
            ////METTRE LE BORDURE DE TEXTE FIELD EN VERT 
            dateF.setStyle("-fx-border-color: green ;");

        }
        /*
        if (Integer.parseInt(Reduction.getText()) < 0) {
            Reductionlab.setStyle("-fx-text-fill: red; -fx-font-size: 12px;-fx-border-color: red ;");
            Reductionlab.setText("saisir un nombre positif");
            a = false;
        } else {
            Reductionlab.setStyle("-fx-font-size: 0px;");
            Reductionlab.setText("");
            ////METTRE LE BORDURE DE TEXTE FIELD EN VERT 
            Reduction.setStyle("-fx-border-color: green ;");

        }
         */

 /*
        if (((Integer.parseInt(Reduction.getText())) > 90) || ((Integer.parseInt(Reduction.getText())) < 10)) {
            Reductionlab.setStyle("-fx-text-fill: red; -fx-font-size: 12px;-fx-border-color: red ;");
            Reductionlab.setText("saisir un nombre entre 10% et 90%");
            a = false;
        } else {
            Reductionlab.setStyle("-fx-font-size: 0px;");
            Reductionlab.setText("");
            ////METTRE LE BORDURE DE TEXTE FIELD EN VERT 
            Reduction.setStyle("-fx-border-color: green ;");

        }
         */
        //   if(!historyDate.after(todayDate) && !futureDate.before(todayDate)) {
        if (dateF.getValue().compareTo(DateD.getValue()) < 0) {
            cntr.setStyle("-fx-text-fill: red; -fx-font-size: 12px;-fx-border-color: red ;");
            cntr.setText("date fin doit étre superieur a date début ");
            a = false;

        } else {
            cntr.setStyle("-fx-font-size: 0px;");
            cntr.setText("");
            ////METTRE LE BORDURE DE TEXTE FIELD EN VERT 
            DateD.setStyle("-fx-border-color: green ;");
            dateF.setStyle("-fx-border-color: green ;");

        }

        return a;
    }

    /*  Promotion promotion = new Promotion();
        promotion.setCentre(selectedCentre);
        promotion.setManager(Session.getConnectedUser());
/****date
         LocalDate localDateD = dateD.getValue();
         Date dateD  = (Date) DateUtils.convertLocalDateToUtilDate(localDateD);
         
          LocalDate localDateF = dateF.getValue();
         Date dateF  = (Date) DateUtils.convertLocalDateToUtilDate(localDateF);
      
        
        
 /****reduction
        try {
            promotion.setReduction(Integer.parseInt(Reduction.getText()));
        } catch (Exception e) {
            prixError.setText("Doit etre une pourcentage");
            return;
        }
/**Nouveau_prix*
        promotion.setNouveau_prix(Integer.parseInt(Nouveau_prix.getText()));

        /*if (!checkAvailableSeats(dateD, Integer.parseInt(Reduction.getText()))) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("no available seats");
            errorAlert.show();
            return;
        }

        boolean isAdded = PromotionDao.getInstance().add(promotion);
        if (isAdded) {
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setContentText("SUCCESS");
            errorAlert.show();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("ERROR");
            errorAlert.show();
        }*/
    @FXML
    private void goBack2(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLCentre.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
