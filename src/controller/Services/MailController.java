/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class MailController implements Initializable {

      @FXML
    private JFXButton envoyer;

    @FXML
    private JFXTextField txtMail;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private TextArea txtMessage;
     @FXML
    private ImageView back;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   envoyer.setOnAction((ActionEvent event) -> {
        
       if (txtSubject.getText()!=null){
           Email email = new Email();
           email.sendMail( txtSubject.getText(), txtMessage.getText());
           
       }else{
           JOptionPane.showMessageDialog(null, "Value rquired : Mail", "ERROR",JOptionPane.OK_OPTION);
       }
  
  
    });
  
  
   
   back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/GetRendezVousFront.fxml"));
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
    

