/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Evenement;
import entity.EventInvitation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class ItemEventOrganiseController extends HBox {

    @FXML
    private ImageView image;

    @FXML
    private Label nomEventLabel;
    @FXML
    private Label dateLabel;
   

    Evenement selectedEvent;

    public void initData(Evenement evenement) {
        selectedEvent = evenement;

        nomEventLabel.setText(selectedEvent.getNomEvent());
        dateLabel.setText(selectedEvent.getDateDebut() + " - " + selectedEvent.getDateFin());

        Image myimage = new Image("file:C:/wamp64/www/imageUploads/" + selectedEvent.getImageProperty().get());
        image.setImage(myimage);

    }

    public ItemEventOrganiseController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ItemEventOrganise.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ItemEventOrganiseController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }

}
