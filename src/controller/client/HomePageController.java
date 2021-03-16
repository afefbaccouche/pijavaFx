
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 *
 * @author amani
 */
public class HomePageController implements Initializable {
    @FXML
    private JFXButton btnServices;
    @FXML
    private Button btnEvent;
    
     @FXML
    private Button btnProfil;
     
    @FXML
    private Button logoutBtn;
    @FXML
    private ImageView imgProfil;
    @FXML
    private JFXButton sante;
    @FXML
    private JFXButton loisir;
    @FXML
    private JFXButton Don;
    @FXML
    private JFXButton consomma;
    @FXML
    private JFXButton asso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
              
        sante.setOnAction(e -> {
            try {
                
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/client/FXMLHomeSante.fxml"));
                Don.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            
        });
        asso.setOnAction(e -> {
            try {
                
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Associations/Associations.fxml"));
                Don.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            
        });
    loisir.setOnAction(e -> {
            try {
                
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Loisir/FXML_printBri.fxml"));
                Don.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            
        });
        Don.setOnAction(e -> {
            try {
                
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/splashscreen/SplashFXML.fxml"));
                Don.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            
        });
       
         btnEvent.setOnAction(e -> {

            
            try {
              /*  Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/events/AccueilEvent.fxml"));
                btnEvent.getScene().setRoot(root);*/

                
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/AccueilEvent.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                
                
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
         
         
         btnProfil.setOnAction(e -> {

            
            try {
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/client/Profil.fxml"));
                btnProfil.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
         
          btnServices.setOnAction(e -> {

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Services/GetAllServicesFront.fxml"));
                Parent content = loader.load(); 
                
                
                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
          
          
          logoutBtn.setOnAction(e -> {

            
           try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/login/FXMLLogin.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);
            
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
          consomma.setOnAction(ev->{
           try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Consommation/Agee/Accueil.fxml"));
            Parent tableViewParent = loader.load();
            Scene scene = new Scene(tableViewParent);
            
            Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }     
          });
          
          
         
         
    }    
    

}
