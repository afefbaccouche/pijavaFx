/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import dao.PromotionDao;
import entity.Centre;
import entity.Promotion;
import eu.hansolo.enzo.notification.Notification;
import eu.hansolo.enzo.notification.Notification.Notifier;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class DetailsCentreController implements Initializable {

    public static Centre selectedCentre = null;

    @FXML
    private Label nomCentre;
    @FXML
    private Label descCentre;
    @FXML
    private ImageView imgCentre;
    @FXML
    private Label adrCentre;
    @FXML
    private Label telCentre;
    @FXML
    private Label nbPlaceCentre;
    @FXML
    private Label prixCentre;
    @FXML
    private Label promoCentre;
    @FXML
    private Label reductionPromo;
    @FXML
    private Label dateFinPromo;
    @FXML
    private Label reductionLabel;
    @FXML
    private Label dateFinLabel;
    @FXML
    private Label newPriceLabel;
    @FXML
    private Label newPrice;

    private boolean isPromotion = false;
    private float prixPromotion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomCentre.setText(selectedCentre.getNom());
        descCentre.setText(selectedCentre.getDescription());
        adrCentre.setText(selectedCentre.getAddress());
        telCentre.setText(Integer.toString(selectedCentre.getTelephone()));
        nbPlaceCentre.setText(Integer.toString(selectedCentre.getNbrPlace()));
        prixCentre.setText(Float.toString(selectedCentre.getPrix()) + " DT");

        try {
            imgCentre.setImage(new Image(selectedCentre.getImage()));
        } catch (Exception e) {

        }

        ArrayList<Promotion> promos = PromotionDao.getInstance().getByCentre(selectedCentre.getId());
        System.out.println(promos);
        if (promos.isEmpty()) {
            hidePromoLabels();
        } else {
            Promotion promotion = promos.get(0);
            Date currentDate = new Date();

            Date dateDebutPromotion = promotion.getDateDebut();
            Date dateFinPromotion = promotion.getDateFin();
            System.out.println("current date: " + currentDate);
            System.out.println("debut date: " + dateDebutPromotion);
            System.out.println("fin date: " + dateFinPromotion);
            boolean isBetween = (currentDate.before(dateFinPromotion)) && (currentDate.after(dateDebutPromotion));
            System.out.println("is between: " + isBetween);

            if (isBetween) {
                reductionPromo.setText(promotion.getReduction() + "%");
                dateFinPromo.setText(promotion.getDateFin().toString());
                newPrice.setText(promotion.getNouveauPrix() + " DT");
                isPromotion = true;
                prixPromotion = promotion.getNouveauPrix();
            } else {
                hidePromoLabels();
            }
        }
    }

    @FXML
    private void reserverCentre(ActionEvent event) {
        ReserverCentreController.selectedCentre = selectedCentre;
        if (isPromotion) {
            ReserverCentreController.prixPromotion = prixPromotion;
        } else {
            ReserverCentreController.prixPromotion = null;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/client/FXMLReserverCentre.fxml"));
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
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/client/FXMLListCentre.fxml"));
            Parent content = loader.load();

            Scene scene = new Scene(content);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void hidePromoLabels() {
        promoCentre.setText("");
        reductionPromo.setText("");
        dateFinPromo.setText("");
        reductionLabel.setText("");
        dateFinLabel.setText("");
        newPriceLabel.setText("");
        newPrice.setText("");
    }

}
