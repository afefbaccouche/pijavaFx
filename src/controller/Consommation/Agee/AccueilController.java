/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee;

import controller.Consommation.Admin.Restaurant.AfficherRestaurantController;
import controller.Consommation.Admin.Restaurant.AjoutRestaurantController;
import controller.Consommation.Agee.Magasin.AfficheMagasinAgeeController;
import controller.Consommation.Agee.Restaurant.AfficherReastauAgeeController;
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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */




public class AccueilController implements Initializable {
    
     @FXML
    private Pane btnRestauAgee;

    @FXML
    private Pane btnMagasinAgee;

    @FXML
    private Pane btnProduitAgee;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    btnRestauAgee.setOnMouseClicked(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficherReastauAgee.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AfficherReastauAgeeController controllerUPRestau = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
    
    btnMagasinAgee.setOnMouseClicked(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficheMagasinAgee.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AfficheMagasinAgeeController controllerUPRestau = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
    
    
    btnProduitAgee.setOnMouseClicked(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficheProduit.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AfficheProduitController controllerUPProduit = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
    }    
    
}
