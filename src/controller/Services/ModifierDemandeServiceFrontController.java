/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.CrudTableDemandeService;
import dao.Services.CrudTableSociete;
import entity.Services.DemandeService;
import entity.Services.Societe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class ModifierDemandeServiceFrontController implements Initializable {

    
    @FXML
    private JFXTextField description;

    @FXML
    private JFXTextField date;
    @FXML
    private JFXButton btnUpdate;
    
    DemandeService demandeser;
    
    
    @FXML
    private ImageView back;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      demandeser =  GetAllDemandeServiceFrontController.demandeserbuttonedit;
     // System.out.println("1223456"+demandeser);
        
      
      
             description.setText(demandeser.getDescription());
             date.setText(demandeser.getDatee());
         btnUpdate.setOnMouseClicked(ev->{ 
         
             
             DemandeService demandeservice=new DemandeService();
             demandeservice.setId(demandeser.getId());
             demandeservice.setDescription(description.getText());
             demandeservice.setDatee(date.getText());
             CrudTableDemandeService ds = new CrudTableDemandeService();
             ds.update(demandeservice);
             description.setText("");
             date.setText("");
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("demande modifié avec succés!");
             alert.show();});
         
         
         back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/client/GetAllDemandeServiceFront.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
     });
         
         
         
  
      }    
    
}

     
