/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.article;

import Admin.home.MenuAdminController;
import controller.maladie.AjoutQuestionController;
import dao.MaladieDao;
import entity.Maladie;
import entity.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLAjoutMaladieController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField nomMaladie;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        container.getChildren().addAll(new MenuAdminController());
    }

    @FXML
    private void add(ActionEvent event) {

        /*
        boolean isAdded = MaladieDao.getInstance().add(new Maladie(nomMaladie.getText(), Session.getConnectedUser()));
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Maladie insérée avec succés!");
            alert.show();
            nomMaladie.setText("");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("erreur!");
            alert.show();
        }
         */
        if (nomMaladie.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller entrer une maladie!");
            alert.show();
        } else {
            try {
                AjoutQuestionController.nomMaladie = nomMaladie.getText();
                Parent root = FXMLLoader.load(getClass().getResource("/view/maladie/FXMLAjoutQuestion.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void goBack(MouseEvent event) {
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

}
