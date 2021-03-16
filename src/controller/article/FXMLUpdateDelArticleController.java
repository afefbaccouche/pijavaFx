/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.article;

import Admin.home.MenuAdminController;
import dao.ArticleDao;
import entity.Article;
import java.io.IOException;
import java.net.MalformedURLException;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLUpdateDelArticleController implements Initializable {

    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private AnchorPane container;

    @FXML
    private AnchorPane containeP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        containeP.getChildren().addAll(new MenuAdminController());

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        TableColumn descCol = new TableColumn("Description");
        descCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        TableColumn bodyCol = new TableColumn("Body");
        bodyCol.setCellValueFactory(
                new PropertyValueFactory<>("body"));

        TableColumn imgCol = new TableColumn("image");
        imgCol.setCellValueFactory(
                new PropertyValueFactory<>("image"));

        articlesTable.getColumns().addAll(titleCol, descCol, bodyCol, imgCol);

        refreshTable();

        /* try {
            boolean addAll = container.getChildren().addAll(FXMLLoader.load(new URL("/view/article/FXMLAjoutArticle.fxml")));
            } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
         */
    }

    @FXML
    private void deleteArticle(ActionEvent event) {
        Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
        if (selectedArticle == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose article");
            errorAlert.show();
        } else {
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setContentText("Are u sure to delete ?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean resDelete = ArticleDao.getInstance().delete(selectedArticle);
                if (resDelete) {
                    Alert infoAlert = new Alert(AlertType.INFORMATION);
                    infoAlert.setContentText("article deleted");
                    infoAlert.show();

                    refreshTable();

                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setContentText("error while deleting article");
                    errorAlert.show();
                }
            } else {
                confirmAlert.close();
            }

        }
    }

    @FXML
    private void updateArticle(ActionEvent event) {
        Article selectedArticle = articlesTable.getSelectionModel().getSelectedItem();
        if (selectedArticle == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose article");
            errorAlert.show();
        } else {
            UpdateArticleController.selectedArticle = selectedArticle;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLUpdateArticle.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void refreshTable() {
        ArrayList<Article> listArticles = new ArrayList();
        listArticles = ArticleDao.getInstance().getBuDoctorId(Session.getConnectedUser().getIdUser());

        ObservableList<Article> data
                = FXCollections.observableArrayList(listArticles);

        articlesTable.setItems(data);
        articlesTable.refresh();
    }

    @FXML
    private void addNew(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLAjoutArticle.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void showMaladies(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLMaladies.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void voirStatistique(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/doctor/FXMLStatArticles.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
