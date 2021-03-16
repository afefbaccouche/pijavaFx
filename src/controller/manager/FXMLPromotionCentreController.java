/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import Admin.home.MenuAdminController;
import controller.article.FXMLUpdateDelArticleController;
import controller.client.HomePageController;
import dao.CentreDao;
import dao.PromotionDao;
import dao.UserDao;
import entity.Centre;
import entity.Promotion;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLPromotionCentreController implements Initializable {

    @FXML
    private TableView<Promotion> TablePromotion;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().addAll(new MenuAdminController());
        TableColumn centreCol = new TableColumn("Centre");
        centreCol.setCellValueFactory(
                new PropertyValueFactory<>("centre"));

        TableColumn reductionCol = new TableColumn("Reduction");
        reductionCol.setCellValueFactory(
                new PropertyValueFactory<>("reduction"));

        TableColumn newPriceCol = new TableColumn("Nouveaux Prix");
        newPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("nouveauPrix"));

        TableColumn dateDebutCol = new TableColumn("Date debut");
        dateDebutCol.setCellValueFactory(
                new PropertyValueFactory<>("dateDebut"));

        TableColumn dateFinCol = new TableColumn("Date fin");
        dateFinCol.setCellValueFactory(
                new PropertyValueFactory<>("dateFin"));

        TablePromotion.getColumns().addAll(centreCol, reductionCol, newPriceCol, dateDebutCol, dateFinCol);
        refreshTable();
    }

    @FXML
    private void Remove(ActionEvent event) {
        Promotion selected = TablePromotion.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please choose Promotion");
            errorAlert.show();
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setContentText("Are u sure to delete ?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean resDelete = PromotionDao.getInstance().delete(selected);
                if (resDelete) {
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setContentText("Promotion deleted");
                    infoAlert.show();

                    refreshTable();

                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("error while deleting Centre");
                    errorAlert.show();
                }
            } else {
                confirmAlert.close();
            }
        }
    }

    private void addNew(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLAjouterPromotion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPromotionCentreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshTable() {
        ArrayList<Promotion> listPromotions = new ArrayList();
        listPromotions = PromotionDao.getInstance().getByManager(Session.getConnectedUser().getIdUser());

        ObservableList<Promotion> data
                = FXCollections.observableArrayList(listPromotions);

        TablePromotion.setItems(data);
        TablePromotion.refresh();
    }

    @FXML
    private void goBack2(MouseEvent event) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLCentre.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
