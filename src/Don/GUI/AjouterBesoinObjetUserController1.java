/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.GUI;

import Don.Services.BesoinObjetServices;
import Don.entities.BesoinObjet;
import controller.client.HomePageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jacem
 */
public class AjouterBesoinObjetUserController1 implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField titre;
    @FXML
    private TextField user;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button retourBOUser;
    @FXML
    private TextField description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         consulterBesoinObjet();
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(titre.textProperty(),
                        user.textProperty(),
                        titre.textProperty(),
                        description.textProperty()
                );
            }

            @Override
            protected boolean computeValue() {
                return (titre.getText().isEmpty()
                        || user.getText().isEmpty()
                        || titre.getText().isEmpty()
                        
                        || description.getText().isEmpty());
            }
        };

        btnAjouter.disableProperty().bind(bb);

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ajouterBesoinObjet();
            }
        });
        
      
        retourBOUser.setOnAction(e -> {

            
            try {
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/CompagneDonUser.fxml"));
                retourBOUser.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }    

    private void consulterBesoinObjet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void ajouterBesoinObjet() {
        String titreB = titre.getText();
        String userB = user.getText();
        String descB = description.getText();

        BesoinObjetServices bs = new BesoinObjetServices();
        
            
            BesoinObjet o = new BesoinObjet(titreB, userB, descB);
            bs.ajouterBesoinObjet(o);
        
        

        titre.setText("");
        user.setText("");
        description.setText("");
        
        
    }
    
}
