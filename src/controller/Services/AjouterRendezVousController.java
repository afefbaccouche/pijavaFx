/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import dao.Services.CrudTableRendezVous;
import entity.Services.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class AjouterRendezVousController implements Initializable {

      @FXML
    private JFXTimePicker time;

    @FXML
    private JFXDatePicker date;

    @FXML
    private TextArea description;
     @FXML
     
    private JFXButton add;
     
    @FXML
    private ImageView back;
    RendezVous rendezVous ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rendezVous = new RendezVous();
       
        
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/GetRendezVousFront.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjouterRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
         }
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
     });
              
            
            
            
            
            
            
            
            
            
            
            
        
           add.setOnMouseClicked(e -> {
          if(description.getText().trim().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("veuillez saisir les don!");
        alert.show();
          }else {
         CrudTableRendezVous Rv = new CrudTableRendezVous();
            
          
            rendezVous.setDate(convertToDate(date.getValue()));
            rendezVous.setTime(Time.valueOf(time.getValue()));
            rendezVous.setDescription(description.getText());
            rendezVous.setReponseAvocat("pas de reponse");
             rendezVous.setEtat(0);
            //  System.out.println(rendezVous);
            Rv.add(rendezVous);
           
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("rendez vous insérée avec succés!");
        alert.show();
        time.setValue(null);
        date.setValue(null);
        description.setText("");
        
           
          }
            
        });
        
    }   
    
    public LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
 
    
}
