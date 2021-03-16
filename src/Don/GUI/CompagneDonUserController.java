/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.GUI;

import Don.Services.CompagneDonServices;
import Don.entities.CompagneDon;
import controller.client.HomePageController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author jacem
 */
public class CompagneDonUserController implements Initializable {

    @FXML
    private TextField titreUser;
    @FXML
    private TextField descriptionUser;
    @FXML
    private DatePicker dateUser;
    @FXML
    private TableView<?> tableCompagneUser;
    @FXML
    private TableColumn<?, ?> tabTitre;
    @FXML
    private TableColumn<?, ?> tabDescription;
    @FXML
    private TableColumn<?, ?> tabNBR;
    @FXML
    private Button AjouterCompagneUser;
    @FXML
    private Button BesoinArgentUser;
    @FXML
    private Button BesoinObjetUser;
    @FXML
    private Button retourCDUser;
    @FXML
    private Button participerUser;
    private ObservableList obs;
    @FXML
    private Button smsUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        dateUser.setValue(LocalDate.now());
        CompagneDonServices c = new CompagneDonServices();
        obs = FXCollections.observableArrayList(c.consulterCompagneDon());
        tableCompagneUser.setItems(obs);
      //  this.tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tabTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        this.tabDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        //this.tabDate.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        this.tabNBR.setCellValueFactory(new PropertyValueFactory<>("nbrParticipation"));
        
        BesoinArgentUser.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/AjouterBesoinArgentUser.fxml"));
                BesoinArgentUser.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
        
        smsUser.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/sms/FXMLDocument.fxml"));
                smsUser.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
        
        
        BesoinObjetUser.setOnAction(e -> {

            
            try {
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/AjouterBesoinObjetUser.fxml"));
                BesoinObjetUser.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        participerUser.setOnAction(e -> {

            
            try {
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/pdf/FXMLDocument.fxml"));
                participerUser.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        retourCDUser.setOnAction(e -> {

            
            try {
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/client/FXMLHomePage.fxml"));
                retourCDUser.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }    

    @FXML
    private void ajouterCompagne(ActionEvent event) {
        
         if(!(titreUser.getText().equals("") && descriptionUser.getText().equals(""))){
        CompagneDonServices c = new CompagneDonServices(); 
        CompagneDon cdd= new CompagneDon(titreUser.getText(),descriptionUser.getText());
        c.ajouterCompagneDon(cdd);
        tabRafresh();
        }
    }

    private void tabRafresh() {
        
        CompagneDonServices c = new CompagneDonServices();
        obs = FXCollections.observableArrayList(c.consulterCompagneDon());
        tableCompagneUser.setItems(obs);
    }
    
}
