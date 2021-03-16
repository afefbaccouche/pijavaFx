/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Association;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.Loisir.AlertMaker;
import dao.Services.ServiceAssociation;
import entity.Association;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author fahdj
 */
public class AjouterAssociationController implements Initializable {

    @FXML
    private JFXTextField TFDescriptionAss;

    @FXML
    private JFXTextField TFNomAss;

    @FXML
    private JFXComboBox<String> CBType;
@FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;
    @FXML
    void AjouterAssociation(ActionEvent event) {
       if(TFDescriptionAss.getText().isEmpty()||TFNomAss.getText().isEmpty()|| CBType.getSelectionModel().getSelectedItem().isEmpty())
       {
           AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Ajout", "Veuillez remplir tous les champs");
           
       }
        else
        {
        ServiceAssociation sa= new ServiceAssociation();
        Association t= new Association( 8 ,TFNomAss.getText(),0, TFDescriptionAss.getText(), CBType.getSelectionModel().getSelectedItem());
        sa.add(t);
        
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Ajout", "Association ajoutee avec succes");
         try {
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Associations/Associations.fxml"));
                TFNomAss.getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      CBType.getItems().add("animaux");
    }    
    
}
