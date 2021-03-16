/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.article;

import Admin.home.MenuAdminController;
import dao.ArticleDao;
import dao.MaladieDao;
import dao.UserDao;
import entity.Article;
import entity.Maladie;
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
import javafx.scene.control.TextField;
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
public class FXMLMaladieController implements Initializable {

    @FXML
    private TableView<Maladie> maladiessTable;
    @FXML
    private TextField searchInput;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getChildren().addAll(new MenuAdminController());
        
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        maladiessTable.getColumns().addAll(nomCol);

        refreshTable();
        // TODO
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLUpdateDelArticle.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void refreshTable() {
        ArrayList<Maladie> listMaladies = new ArrayList();
        System.out.println("Connected User from Maladie Controller: " + Session.getConnectedUser().getIdUser());
        User fake = UserDao.getInstance().findById(Session.getConnectedUser().getIdUser());
        System.out.println("Fake: " + fake);
        listMaladies = MaladieDao.getInstance().getBuDoctorId(Session.getConnectedUser().getIdUser());

        System.out.println(listMaladies);
        System.out.println(Session.getConnectedUser().getIdUser());

        ObservableList<Maladie> data
                = FXCollections.observableArrayList(listMaladies);

        maladiessTable.setItems(data);
        maladiessTable.refresh();
    }

    @FXML
    private void deleteMaladie(ActionEvent event) {

        Maladie selectedMaladie = maladiessTable.getSelectionModel().getSelectedItem();
        if (selectedMaladie == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please choose maladie");
            errorAlert.show();
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setContentText("Are u sure to delete ?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean resDelete = MaladieDao.getInstance().delete(selectedMaladie);
                if (resDelete) {
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setContentText("maladie deleted");
                    infoAlert.show();

                    refreshTable();

                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("error while deleting maladie");
                    errorAlert.show();
                }
            } else {
                confirmAlert.close();
            }

        }
    }

    @FXML
    private void addNew(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLAjoutMaladie.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMaladieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchMaladie(ActionEvent event) {
        String key = searchInput.getText();
        ArrayList<Maladie> listMaladies = new ArrayList();
        listMaladies = MaladieDao.getInstance().searchMaladieByDoctor(key, Session.getConnectedUser().getIdUser());

        ObservableList<Maladie> data
                = FXCollections.observableArrayList(listMaladies);

        maladiessTable.setItems(data);
        maladiessTable.refresh();
    }

}
