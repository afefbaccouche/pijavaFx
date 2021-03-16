/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Admin.home1.MenuAdminController;
import dao.EvenementDao;
import entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AfficherEventAdminController implements Initializable {

    @FXML
    private Button ajouterBtn;
    @FXML
    private Button modifierBtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private TableView<Evenement> eventsTable;
    @FXML
    private TableColumn<Evenement, String> nomColumn;
    @FXML
    private TableColumn<Evenement, String> descriptionColumn;
    @FXML
    private TableColumn<Evenement, LocalDate> dateDebutColumn;
    @FXML
    private TableColumn<Evenement, LocalDate> dateFinColumn;
    @FXML
    private TableColumn<Evenement, LocalTime> heureDebutColumn;
    @FXML
    private TableColumn<Evenement, LocalTime> heureFinColumn;
    @FXML
    private TableColumn<Evenement, String> adresseColumn;
    @FXML
    private TableColumn<Evenement, Integer> prixColumn;
    @FXML
    private Button categoriesBtn;
    @FXML
    private AnchorPane container;
    @FXML
    private Button statBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        container.getChildren().add(new MenuAdminController());

        EvenementDao eventdao = EvenementDao.getInstance();
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        list = (ObservableList<Evenement>) eventdao.displayAll();

        eventsTable.setItems(list);

        nomColumn.setCellValueFactory(cell -> cell.getValue().getNomEventProperty());
        adresseColumn.setCellValueFactory(cell -> cell.getValue().getAdresseProperty());
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<Evenement, LocalDate>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<Evenement, LocalDate>("dateFin"));
        heureDebutColumn.setCellValueFactory(new PropertyValueFactory<Evenement, LocalTime>("heureDebut"));
        heureFinColumn.setCellValueFactory(new PropertyValueFactory<Evenement, LocalTime>("heureFin"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("prix"));

    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/AjouterEventAdmin.fxml"));
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifierEventAdmin.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);

            ModifierEventAdminController controller = loader.getController();
            controller.initData(eventsTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerAction(ActionEvent event) {

        try {
            EvenementDao evdao = EvenementDao.getInstance();
            evdao.delete(eventsTable.getSelectionModel().getSelectedItem());
            
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AfficherEventAdmin.fxml"));
            supprimerBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherCategoriesAction(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AfficherCategorieEventAdmin.fxml"));
            categoriesBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    private void statisticAction(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/EventStatistique.fxml"));
            statBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
