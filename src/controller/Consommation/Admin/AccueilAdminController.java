/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin;

import com.jfoenix.controls.JFXButton;
import controller.Consommation.Admin.Magasin.AfficheMagasinController;
import controller.Consommation.Admin.Restaurant.AfficherRestaurantController;
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
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class AccueilAdminController implements Initializable {
    
 
//    @FXML
//    private JFXButton RestauAcc;

        @FXML
    private MenuBar RestauAcc;
    @FXML
    private JFXButton MagasinAcc;

    @FXML
    private JFXButton ProduitAcc;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        RestauAcc.setOnMouseClicked(e->{
            try{
             FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/AfficheRestaurants.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AfficherRestaurantController controllerUPRestau = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      
        
        MagasinAcc.setOnAction(e->{
            try{
             FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/Magasin/AfficheMagasin.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AfficheMagasinController controllerUPRestau = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }    
    
}
