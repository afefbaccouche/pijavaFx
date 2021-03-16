/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EventInvitationDao;
import dao.UserDao;
import entity.Evenement;
import entity.EventInvitation;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AfficherEventInvitationController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ListView<InvitationListItemController> listViewInvit;
    
    User connectedUser;

    
    public void clickItem(Evenement evenement,MouseEvent e,EventInvitation invitation){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RepondreEventInvitation.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);
            
            RepondreEventInvitationController controller = loader.getController();
            controller.initDataEvent(evenement,invitation);
            
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEventInvitationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        container.getChildren().add(new MenuComponentEventController());
        
        connectedUser = new User();
        UserDao userdao = UserDao.getInstance();
        connectedUser = userdao.displayById(Session.getConnectedUser().getIdUser());
        
        EventInvitationDao invitdao = EventInvitationDao.getInstance();
        
        ObservableList<EventInvitation> listInvit=FXCollections.observableArrayList();
        listInvit= (ObservableList<EventInvitation>) invitdao.displayAllByUserInvite(connectedUser);
        
        ObservableList<InvitationListItemController>itemsList = FXCollections.observableArrayList();
        
        InvitationListItemController item ;
        for(int i=0;i<listInvit.size();i++){
            item= new InvitationListItemController();
            item.initData(listInvit.get(i));
            itemsList.add(item);
        }
        
        listViewInvit.setItems(itemsList);
        
        
        listViewInvit.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            
            clickItem(listViewInvit.getSelectionModel().getSelectedItem().selectedInvitation.getEvenement(),event,listViewInvit.getSelectionModel().getSelectedItem().selectedInvitation);
            
        }
    });
        
    }    
    
}
