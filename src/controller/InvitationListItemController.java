/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.EventInvitation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class InvitationListItemController extends HBox {

    @FXML
    private ImageView image;
    @FXML
    private Label userInvitLabel;
    @FXML
    private Label nomEventLabel;
    @FXML
    private Label dateLabel;
    
    
    EventInvitation selectedInvitation;
    
    
    public void initData(EventInvitation invitation) {
        selectedInvitation = invitation;
        
        userInvitLabel.setText(selectedInvitation.getUser().getPrenomUser()+ " "+selectedInvitation.getUser().getNomUser()+" vous a invité à :");
        nomEventLabel.setText(selectedInvitation.getEvenement().getNomEvent());
        dateLabel.setText(selectedInvitation.getEvenement().getDateDebut()+" - "+selectedInvitation.getEvenement().getDateFin());
        
        
        Image myimage = new Image("file:C:/wamp64/www/imageUploads/" + selectedInvitation.getEvenement().getImageProperty().get());
        image.setImage(myimage);

        

    }


   public InvitationListItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/InvitationListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
