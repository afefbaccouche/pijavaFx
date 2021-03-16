/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Admin.home1.MenuAdminController;
import dao.EventCategorieDao;
import dao.UserDao;
import entity.EventCategorie;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AfficherCategorieEventAdminController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private Button ajouterBtn;
    @FXML
    private Button modifierBtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private ListView<String> categorieListView;
    @FXML
    private TextField categorieText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getChildren().add(new MenuAdminController());

        EventCategorieDao catdao = EventCategorieDao.getInstance();
        ObservableList<EventCategorie> list = FXCollections.observableArrayList();
        list = (ObservableList<EventCategorie>) catdao.displayAll();

        List<String> names = list.stream().map(c -> c.getNomCategorie()).collect(Collectors.toList());

        ObservableList<String> listNames = FXCollections.observableArrayList(names);

        categorieListView.setItems((ObservableList<String>) listNames);

    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        if (categorieText.getText() != "") {
            try {
                EventCategorieDao catdao = EventCategorieDao.getInstance();
                User connectedUser = UserDao.getInstance().displayById(Session.getConnectedUser().getIdUser());
                EventCategorie categorie = new EventCategorie(categorieText.getText(),connectedUser);
                catdao.add(categorie);

                Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherCategorieEventAdmin.fxml"));
                ajouterBtn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AfficherCategorieEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void modifierAction(ActionEvent event) {
        if (categorieText.getText() != "") {

            try {
                EventCategorieDao catdao = EventCategorieDao.getInstance();
                catdao.updateCat(catdao.displayByName(categorieListView.getSelectionModel().getSelectedItem()), categorieText.getText());

                Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherCategorieEventAdmin.fxml"));
                modifierBtn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AfficherCategorieEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void supprimerAction(ActionEvent event) {

        try {
            EventCategorieDao catdao = EventCategorieDao.getInstance();
            catdao.delete(catdao.displayByName(categorieListView.getSelectionModel().getSelectedItem()));

            Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherCategorieEventAdmin.fxml"));
            supprimerBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void clickItemAction(MouseEvent event) {
        categorieText.setText(categorieListView.getSelectionModel().getSelectedItem());
    }

}
