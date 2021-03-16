/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.CrudTableService;
import entity.Services.Service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class AjoutServiceController implements Initializable {
    @FXML
    private ImageView back;
    
    @FXML
    private JFXButton btn;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField description;
    
    
    
    /*@FXML
    private void backtoGetAllSociete(ActionEvent event) throws IOException {
      Parent SocieteParent =  FXMLLoader.load(getClass().getResource("/view/Services/GetAllSociete.fxml"));
      Scene SocieteScene = new Scene (SocieteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
    
       
    }*/
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/GetAllService.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
     });
         
     
     
        btn.setOnMouseClicked(e -> {
            
            if(nom.getText().trim().isEmpty()||description.getText().trim().isEmpty()){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("veuillez saisir les don!");
        alert.show();
          }else {
         CrudTableService ss = new CrudTableService();
            
            Service soc = new Service();
            soc.setNom(nom.getText());
            soc.setDescription(description.getText());
           
            
            ss.add(soc);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Societe insérée avec succés!");
        alert.show();
        nom.setText("");
        description.setText("");
            }
        });
    }    
    
}
