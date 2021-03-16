/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import static controller.Services.AvocatRendezVousController.Rv;
import dao.Services.CrudTableRendezVous;
import entity.Services.RendezVous;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class ModifierRendezVousController implements Initializable {

  
    
      @FXML
    private JFXComboBox<String> combo;

      @FXML
    private JFXButton btnSave;
       @FXML
    private JFXButton back;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    ObservableList<String> value = 
    FXCollections.observableArrayList("Approuver" );
        combo.setItems(value);
        
        
        btnSave.setOnAction((ActionEvent e) -> {
        RendezVous Rv = new RendezVous();
        Rv.setEtat(1);
        Rv.setReponseAvocat(combo.getValue());
        Rv.setId(AvocatRendezVousController.Rv.getId());
        
        CrudTableRendezVous ctrv=new CrudTableRendezVous();
        ctrv.update(Rv);
        
        
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      back.setOnAction((ActionEvent e) -> {
       
              Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/AvocatRendezVous.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)e.getSource()).getScene().getWindow();
      
       window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();       
        
      });
        
        
        
    }    
    
   }