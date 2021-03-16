/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import dao.ReservationCentreDao;
import entity.Centre;
import entity.ReservationCentre;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import session.Session;
import utils.DateUtils;
import utils.PDFUtils;
import utils.QRCodeGenerator;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ReserverCentreController implements Initializable {

    public static Centre selectedCentre = null;
    public static Float prixPromotion = null;
    @FXML
    private Label labelCentre;
    @FXML
    private DatePicker dateD;
    @FXML
    private TextField nbrJour;
    @FXML
    private Label prixCentre;
    @FXML
    private Label totalPrice;
    @FXML
    private Label prixError;

    private float prixUtiliser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ArrayList<ReservationCentre> list = ReservationCentreDao.getInstance().getAll();
        System.out.println("listttttt: " + list);

        labelCentre.setText("Reserver centre: " + selectedCentre.getNom());

        totalPrice.setText("0DT");

        prixUtiliser = selectedCentre.getPrix();

        if (prixPromotion != null) {
            prixUtiliser = prixPromotion;
        }

        prixCentre.setText(prixUtiliser + "DT/JOUR");

        nbrJour.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            //if(ok==true)
            if (!newValue.isEmpty()) {
                try {
                    int valInt = Integer.parseInt(newValue);
                    float priceTotal = valInt * prixUtiliser;
                    totalPrice.setText(priceTotal + "DT");
                    prixError.setText("");

                } catch (Exception e) {
                    prixError.setText("must be number");
                    totalPrice.setText("0DT");
                    System.out.println("ERROR NUMBER PRICE");
                    return;
                }
            } else {
                prixError.setText("");
                totalPrice.setText("0DT");
            }

        });

        /* private    boolean enpromo(boolean ok ) 
    {
         
         ok= true;
        if (selectedCentre.getEnPromotion()=="oui")
        {
        ok = false;
        System.out.println("ok"+ ok);
    }
        
    }*/
    }

    private boolean checkAvailableSeats(Date chosenDate, int nbJour) throws ParseException {
        boolean available = false;

        LocalDate localChosenDateFin = DateUtils.addDays(nbJour, DateUtils.convertUtilDateToLocalDate(chosenDate));
        Date dChosenDateFin = DateUtils.convertLocalDateToUtilDate(localChosenDateFin);
        java.sql.Date chosenDateFin = DateUtils.convertUtilDateToSqlDate(dChosenDateFin);

        ArrayList<ReservationCentre> listRes = ReservationCentreDao.getInstance().getAllByCentre(selectedCentre.getId());
        int nbSeats = 0;
        System.out.println(listRes.size());
        System.out.println("checkAvailableSeats");
        for (int i = 0; i < listRes.size(); i++) {
            System.out.println("************************************************");
            java.sql.Date dateDebut = listRes.get(i).getDateD();
            System.out.println("Date Debut. " + dateDebut);
            java.util.Date dDateDebut = DateUtils.convertSqlDateToUtilDate(dateDebut);

            LocalDate localDateFin = DateUtils.addDays(listRes.get(i).getNombreJ(), DateUtils.convertUtilDateToLocalDate(dDateDebut));
            Date dDateFin = DateUtils.convertLocalDateToUtilDate(localDateFin);
            java.sql.Date dateFin = DateUtils.convertUtilDateToSqlDate(dDateFin);
            System.out.println("Date Fin. " + dateFin);

            System.out.println("Chosen Date: " + chosenDate);
            System.out.println("Chosen Date Fin: " + chosenDateFin);

            System.out.println("After: " + dateFin.after(chosenDate));
            System.out.println("Before: " + dateFin.before(chosenDateFin));

            if (dateFin.after(chosenDate)) {
                nbSeats++;
            }
            System.out.println("************************************************");
        }
        /*
        
         */

        available = nbSeats < selectedCentre.getNbrPlace();

        return available;
    }

    private void showAlertSuccess(MouseEvent event, ReservationCentre reservation) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reservation avec succée");
        alert.setHeaderText("Votre reservation a ete enregistré avec succès");
        alert.setContentText("Choisissez votre option.");
        ButtonType buttonTypeOne = new ButtonType("Voir mon reservation");
        ButtonType buttonTypeCancel = new ButtonType("Terminer", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();

        // Create Reservation PDF
        com.itextpdf.text.Image img = QRCodeGenerator.createQRCode(selectedCentre.getNom());
        String idDocument = selectedCentre.getId() + "-" + Session.getConnectedUser().getIdUser() + "-reservation.pdf";
        PDFUtils.createStyledPDF(selectedCentre, Session.getConnectedUser(), reservation, img, idDocument);
        if (result.get() == buttonTypeOne) {
            // ... user chose "One"
            PDFUtils.runPdf("src/pdf/" + idDocument);
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/client/FXMLListCentre.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/client/FXMLDetailsCentre.fxml"));
            Parent content = loader.load();

            Scene scene = new Scene(content);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reserver(MouseEvent event) throws ParseException {

        ReservationCentre reservation = new ReservationCentre();
        reservation.setCentre(selectedCentre);
        reservation.setClient(Session.getConnectedUser());

        LocalDate localDateD = dateD.getValue(); // Local Date from DatePicker
        if (localDateD == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please Choose a date");
            errorAlert.show();
            return;
        }
        Date dateD = DateUtils.convertLocalDateToUtilDate(localDateD);

        Date currentDate = new Date(); // Current Date(Today)
        boolean isAfter = currentDate.after(dateD);
        if (isAfter) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Choose date after in the present");
            errorAlert.show();
            return;
        }

        /*
        TEST
         */
        int currentDay = DateUtils.getDay(currentDate);
        int currentMonth = DateUtils.getMonth(currentDate);
        int currentYear = DateUtils.getYear(currentDate);

        int day = DateUtils.getDay(dateD);
        int month = DateUtils.getMonth(dateD);
        System.out.println("Current Day: " + currentDay);
        System.out.println("Current Month: " + currentMonth);
        System.out.println("Chosen Day: " + day);
        System.out.println("Chosen Day: " + month);
        System.out.println("DAYS BETWEEN NOW AND: " + Integer.toString(DateUtils.getDaysBetween(dateD, currentDate)));
        /*
        /Test
         */

        reservation.setDateD(DateUtils.convertUtilDateToSqlDate(dateD));
        reservation.setDateF(DateUtils.convertUtilDateToSqlDate(dateD));

        try {
            reservation.setNombreJ(Integer.parseInt(nbrJour.getText()));
        } catch (Exception e) {
            prixError.setText("must be number");
            return;
        }

        if (!checkAvailableSeats(dateD, Integer.parseInt(nbrJour.getText()))) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("no available seats");
            errorAlert.show();
            return;
        }

        boolean isAdded = ReservationCentreDao.getInstance().add(reservation, prixPromotion);
        if (isAdded) {
            showAlertSuccess(event, reservation);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("ERROR");
            errorAlert.show();
        }
    }

}
