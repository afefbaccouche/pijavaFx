/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvenementDao;
import dao.UserDao;
import entity.Evenement;
import entity.EventInvitation;
import entity.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AfficherEventOrganiseController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ListView<ItemEventOrganiseController> eventListView;

    User connectedUser;
    @FXML
    private Button modifierBtn;
    @FXML
    private Button supprimerBtn;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getChildren().add(new MenuComponentEventController());

        connectedUser = UserDao.getInstance().displayById(Session.getConnectedUser().getIdUser());

        ObservableList<Evenement> listEvent = FXCollections.observableArrayList();

        listEvent = (ObservableList<Evenement>) EvenementDao.getInstance().displayAllByUser(connectedUser.getIdUser());

        ObservableList<ItemEventOrganiseController> itemsList = FXCollections.observableArrayList();

        ItemEventOrganiseController item;
        for (int i = 0; i < listEvent.size(); i++) {
            item = new ItemEventOrganiseController();
            item.initData(listEvent.get(i));
            itemsList.add(item);

        }

        eventListView.setItems(itemsList);

    }
    
    
    public void refresh(){
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/AfficherEventOrganise.fxml"));
            supprimerBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventOrganiseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void modifierAction(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifierEvent.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);

            ModifierEventController controller = loader.getController();
            controller.initData(eventListView.getSelectionModel().getSelectedItem().selectedEvent);

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventOrganiseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void supprimerAction(ActionEvent event) {

        ButtonType ouiBtn = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType nonBtn = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.NONE,
                "Voulez vous vraiment supprimer l'événement "+eventListView.getSelectionModel().getSelectedItem().selectedEvent.getNomEvent()+" ?",
                ouiBtn,
                nonBtn);

        alert.setTitle("");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(nonBtn) == ouiBtn) {
            System.out.println("delete");
            EvenementDao edao = EvenementDao.getInstance();
            edao.delete(eventListView.getSelectionModel().getSelectedItem().selectedEvent);
            refresh();
        }

    }

}
