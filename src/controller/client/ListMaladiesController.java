/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import controller.manager.FXMLAjouterCentrelController;
import dao.MaladieDao;
import dao.UserDao;
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
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ListMaladiesController implements Initializable {

    @FXML
    private TableView<Maladie> tableMaladies;
    @FXML
    private TextField searchInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        tableMaladies.getColumns().addAll(nomCol);

        refreshTable();
    }

    @FXML
    private void consultation(ActionEvent event) {
        Maladie selectedMaladie = tableMaladies.getSelectionModel().getSelectedItem();
        if (selectedMaladie == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please choose maladie");
            errorAlert.show();
        } else {
            QuestionMaladieController.chosenMaladie = selectedMaladie;
            QuestionMaladieController.index = 0;
            QuestionMaladieController.numReponseAdded = 0;
            QuestionMaladieController.listReponse = null;
            QuestionMaladieController.listQuestions = null;

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/client/FXMLQuestionMaladie.fxml"));
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

    private void refreshTable() {
        ArrayList<Maladie> listMaladies = new ArrayList();
        User fake = UserDao.getInstance().findById(Session.getConnectedUser().getIdUser());
        listMaladies = MaladieDao.getInstance().getAll();

        System.out.println(listMaladies);
        System.out.println(Session.getConnectedUser().getIdUser());

        ObservableList<Maladie> data
                = FXCollections.observableArrayList(listMaladies);

        tableMaladies.setItems(data);
        tableMaladies.refresh();
    }

    @FXML
    private void goBack(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/client/FXMLHomeSante.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchMaladie(ActionEvent event) {
        String key = searchInput.getText();
        ArrayList<Maladie> listMaladies = new ArrayList();
        listMaladies = MaladieDao.getInstance().searchMaladie(key);

        ObservableList<Maladie> data
                = FXCollections.observableArrayList(listMaladies);

        tableMaladies.setItems(data);
        tableMaladies.refresh();
    }

}
