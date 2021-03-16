/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import Admin.home.MenuAdminController;
import com.itextpdf.text.BadElementException;
import controller.client.HomePageController;
import controller.manager.FXMLAjouterCentrelController;
import dao.CentreDao;
import dao.PromotionDao;
import dao.UserDao;
import entity.Centre;
import entity.Promotion;
import entity.User;
import eu.hansolo.enzo.notification.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Spliterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;
import utils.PDFUtils;
import utils.QRCodeGenerator;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLCentrelController implements Initializable {

    public static Centre selectedCentre;

    @FXML
    private TableView<Centre> centreTable;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField searchInput;

    //private ListData listdata = new ListData();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         root.getChildren().addAll(new MenuAdminController());
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        TableColumn descCol = new TableColumn("Description");
        descCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        TableColumn imgCol = new TableColumn("image");
        imgCol.setCellValueFactory(
                new PropertyValueFactory<>("image"));

        TableColumn prixCol = new TableColumn("prix/heure(DT)");
        prixCol.setCellValueFactory(
                new PropertyValueFactory<>("prix"));

        TableColumn addressCol = new TableColumn("adresse");
        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        TableColumn telCol = new TableColumn("Telephone");
        telCol.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));

        TableColumn nbrPlaceCol = new TableColumn("nombre de place");
        nbrPlaceCol.setCellValueFactory(
                new PropertyValueFactory<>("nbrPlace"));

        centreTable.getColumns().addAll(nomCol, descCol, imgCol, prixCol, nbrPlaceCol, addressCol, telCol);
        refreshTable();

        checkPromotionFini();

    }

    private void refreshTable() {
        ArrayList<Centre> listCentres = new ArrayList();
        //  System.out.println("Connected User from Centre Controller: " + Session.getConnectedUser().getIdUser());
        User fake = UserDao.getInstance().findById(Session.getConnectedUser().getIdUser());
        //System.out.println("Fake: " + fake);
        listCentres = CentreDao.getInstance().getByManagerId(Session.getConnectedUser().getIdUser());

        //  System.out.println(listCentres );
        //System.out.println(Session.getConnectedUser().getIdUser());
        ObservableList<Centre> data
                = FXCollections.observableArrayList(listCentres);

        centreTable.setItems(data);
        centreTable.refresh();
    }

    @FXML
    private void updateCentre(ActionEvent event) {
        Centre selectedCentre = centreTable.getSelectionModel().getSelectedItem();
        if (selectedCentre == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose centre");
            errorAlert.show();
        } else {
            FXMLUpdateCentreController.selectedCentre = selectedCentre;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLUpdateCentre.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLCentrelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void deleteCentre(ActionEvent event) {
        Centre selectedCentre = centreTable.getSelectionModel().getSelectedItem();
        if (selectedCentre == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose Centre");
            errorAlert.show();
        } else {
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setContentText("Are u sure to delete ?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean resDelete = CentreDao.getInstance().delete(selectedCentre);
                if (resDelete) {
                    Alert infoAlert = new Alert(AlertType.INFORMATION);
                    infoAlert.setContentText("Centre deleted");
                    infoAlert.show();

                    refreshTable();

                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setContentText("error while deleting Centre");
                    errorAlert.show();
                }
            } else {
                confirmAlert.close();
            }

        }
    }

    @FXML
    private void addNew(ActionEvent event) throws BadElementException, IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLAjouterCentre.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/manager/text-field-red-border.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void GererPromotion(ActionEvent event) {

        Centre selected = centreTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please choose Centre");
            errorAlert.show();
        } else {
            FXMLAjouterPromotionController.selectedCentre = selected;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/manager/FXMLAjouterPromotion.fxml"));
                Parent content = loader.load();

                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void checkPromotionFini() {
        ArrayList<Promotion> promos = PromotionDao.getInstance().getByManager(Session.getConnectedUser().getIdUser());
        System.out.println("sizeeeeeeee:" + promos.size());
        for (int i = 0; i < promos.size(); i++) {
            Promotion promotion = promos.get(i);
            Date currentDate = new Date();

            Date dateDebutPromotion = promotion.getDateDebut();
            Date dateFinPromotion = promotion.getDateFin();
            boolean isBetween = (currentDate.before(dateFinPromotion)) && (currentDate.after(dateDebutPromotion));
            System.out.println("is between: " + isBetween);

            if (!isBetween) {
                StringBuilder msgNotif = new StringBuilder();
                msgNotif.append("la promotion du centre afef validation: ");
                msgNotif.append(promotion.getCentre().getNom());
                msgNotif.append(" a ete expiré afef validation");
                Notification.Notifier.INSTANCE.notifyWarning("Promotion expiré afef validation", msgNotif.toString());
            }

        }

        /*
        // Show the custom notification
        Notification info = new Notification("Title", "Info-Message");
        Notification.Notifier.INSTANCE.notify(info);
        Notification.Notifier.INSTANCE.notifyWarning("This is a warning", "message");
         */
    }

    @FXML
    private void searchCentre(ActionEvent event) {
        String key = searchInput.getText();
        ArrayList<Centre> listSerchCentre = CentreDao.getInstance().searchCentre(key);
        ObservableList<Centre> data
                = FXCollections.observableArrayList(listSerchCentre);

        centreTable.setItems(data);
        centreTable.refresh();

    }

    @FXML
    private void gererPromotion(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/manager/FXMLPromotionCentre.fxml"));
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
    private void logOut(ActionEvent event) {
     
        try {
            Session.logOut();
            Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLLogin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    

