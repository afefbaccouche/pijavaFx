/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import controller.manager.FXMLAjouterCentrelController;
import dao.ArticleDao;
import dao.MaladieDao;
import dao.UserDao;
import entity.Article;
import entity.Maladie;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ListArticleController implements Initializable {

    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TextField searchInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn titreCol = new TableColumn("Titre");
        TableColumn descCol = new TableColumn("Description");
        TableColumn bodyCol = new TableColumn("Contenu");
        titreCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        bodyCol.setCellValueFactory(
                new PropertyValueFactory<>("body"));

        tableArticle.getColumns().addAll(titreCol, descCol, bodyCol);
        tableArticle.setEditable(false);
        refreshTable();
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

    private void refreshTable() {
        ArrayList<Article> listArticles = new ArrayList();
        listArticles = ArticleDao.getInstance().getAll();
        ObservableList<Article> data
                = FXCollections.observableArrayList(listArticles);
        tableArticle.setItems(data);
        tableArticle.refresh();
    }

    @FXML
    private void voirArticle(MouseEvent event) {
        Article selectedArticle = tableArticle.getSelectionModel().getSelectedItem();
        if (selectedArticle == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please choose article to read");
            errorAlert.show();
        } else {
            DetailsArticleController.selectedArticle = selectedArticle;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/client/FXMLDetailsArticle.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                //stage.setMaximized(true);
                //stage.setFullScreen(true);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void searchArticle(ActionEvent event) {
        String key = searchInput.getText();
        ArrayList<Article> listArticles = new ArrayList();
        listArticles = ArticleDao.getInstance().searchArticle(key);
        ObservableList<Article> data
                = FXCollections.observableArrayList(listArticles);
        tableArticle.setItems(data);
        tableArticle.refresh();
    }

}
