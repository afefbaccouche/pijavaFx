/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.superAdmin;

import Admin.home.MenuAdminController;
import dao.UserDao;
import entity.User;
import java.awt.Insets;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import session.Session;
import utils.MailUtils;
import utils.Popup;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class SuperAdminController implements Initializable {

    @FXML
    private TableView<User> usersTable;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().addAll(new MenuAdminController());
        
        TableColumn firstNameCol = new TableColumn("Nom");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("nomUser"));

        TableColumn lastNameCol = new TableColumn("Prenom");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("prenomUser"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("emailUser"));

        TableColumn roleCol = new TableColumn("Role");
        roleCol.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        usersTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol, roleCol);

        refreshTable();
    }

    @FXML
    private void approuverUser(ActionEvent event) {
        System.out.println("approuve");
        System.out.println(usersTable.getSelectionModel().getSelectedItem());
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose user");
            errorAlert.show();
            
            
        } else {
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setContentText("Are u sure ?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean resUpdate = UserDao.getInstance().enableUser(selectedUser);
                if (resUpdate) {
                    try {
                        MailUtils.sendMail(selectedUser.getEmailUser());
                    } catch (Exception e) {

                    }
                    Alert infoAlert = new Alert(AlertType.INFORMATION);
                    infoAlert.setContentText("user enablesd");
                    infoAlert.show();

                    refreshTable();

                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setContentText("error while enabling user");
                    errorAlert.show();
                }
            } else {
                confirmAlert.close();
            }

        }
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose user");
            errorAlert.show();
        } else {
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
            confirmAlert.setContentText("Are u sure to delete ?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean resDelete = UserDao.getInstance().deleteUser(selectedUser);
                if (resDelete) {
                    Alert infoAlert = new Alert(AlertType.INFORMATION);
                    infoAlert.setContentText("user deleted");
                    infoAlert.show();

                    refreshTable();

                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setContentText("error while deleting user");
                    errorAlert.show();
                }
            } else {
                confirmAlert.close();
            }

        }
    }

    private void refreshTable() {
        ArrayList<User> listUsers = new ArrayList();
        listUsers = UserDao.getInstance().getNotEnabledUsers();

        ObservableList<User> data
                = FXCollections.observableArrayList(listUsers);

        usersTable.setItems(data);
        usersTable.refresh();
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
            Logger.getLogger(SuperAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void voirPiece(ActionEvent event) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Please choose user");
            errorAlert.show();
            
            
        } else {
            Popup.display(selectedUser.getPieceJustificatif());
        }
        
    }

   
}
