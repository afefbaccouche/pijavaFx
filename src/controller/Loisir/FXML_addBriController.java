/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Loisir;


import com.jfoenix.controls.JFXButton;
import controller.client.HomePageController;
import dao.Services.ServiceBricolage;
import entity.Loisir.Bricolage;
import session.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * FXML Controller class
 *
 * @author Pirateos
 */
public class FXML_addBriController implements Initializable {
 @FXML
    private AnchorPane mainContainer;
 @FXML
    private StackPane rootPane;

    @FXML
    private TextField desc;
    @FXML
    private TextField titrebri;
    @FXML
    private Button video;
    @FXML
    private TextField vdo;
    @FXML
    private JFXButton btnBRRR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       vdo.setVisible(false);
    }    

    @FXML
    private void uploadVideo(MouseEvent event) {
        
 FileChooser fileChooser = new FileChooser();
 fileChooser.setTitle("Open Resource File");
 fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("video Files", "*.mp4"),
         new ExtensionFilter("All Files", "*.*"));
 File selectedFile = fileChooser.showOpenDialog(null);
 if (selectedFile != null) {
    System.out.println(selectedFile.getName());
     vdo.setText(selectedFile.getName());
 }
  
    }

    @FXML
    private void ajoutBri(MouseEvent event) {
      if(desc.getText().trim().isEmpty()||titrebri.getText().trim().isEmpty()||vdo.getText().trim().isEmpty())
      {
       AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Donnees Incompletes", "Veuillez remplir tous les champs");
             return;
      }
      else{
  
  
       Bricolage b=new Bricolage();
        ServiceBricolage S=new ServiceBricolage();
        b.setUrl_bri(vdo.getText());
        b.setTire_bri(titrebri.getText());
        b.setDescription_bri(desc.getText());
        b.setClient_bri_id(Session.getConnectedUser().getIdUser());
        System.out.println(b.getClient_bri_id());
        S.add(b);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Bricolage Ajouté avec succées !!!!");
        alert.show();
        System.out.println(b);
        
    }
    }

    @FXML
    private void LstBri(ActionEvent event) {
            try {
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Loisir/FXML_printBri.fxml"));
                btnBRRR.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }



}
