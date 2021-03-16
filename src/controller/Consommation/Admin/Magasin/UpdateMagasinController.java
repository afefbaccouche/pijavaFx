/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Magasin;

import ConnectionBaseDonn.connect;
import dao.Consommation.ListData;
import dao.Consommation.MagasinServices;
import entity.Consommation.Magasins;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class UpdateMagasinController implements Initializable {

      @FXML
    private TextField nomMagsin;

    @FXML
    private TextField addressMagasin;

    @FXML
    private TextField telMagasin;

    @FXML
    private TextField mailMagasin;

  
    @FXML
    private Button btnSaveUpM;
    
    
     private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    private ListData listdata = new ListData();
            
           
      public UpdateMagasinController(){
    
       connection =connect.getInstance().getCnx(); 
    }
      
      private Magasins selectRestau;


  public void RetriveData(Magasins magasin){
    selectRestau=magasin;
    //restaurant.setIdRestaurant(Integer.parseInt(idRUP.getText()));
    nomMagsin.setText(magasin.getNomMagasin());
    addressMagasin.setText(magasin.getAdressMagasin());
    telMagasin.setText(magasin.getNumMagasin());
    mailMagasin.setText(magasin.getEmailMagasin());

   // idRUP.setText(Integer.toString(retaurant.getIdRestaurant()));
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     btnSaveUpM.setOnAction(e->{
           MagasinServices MagI = MagasinServices.getInstance();
      Magasins Res = new Magasins();


        
             Magasins magasin= selectRestau;
         
            magasin.setNomMagasin(nomMagsin.getText());
              magasin.setAdressMagasin(addressMagasin.getText());
                magasin.setNumMagasin(telMagasin.getText());
                magasin.setEmailMagasin(mailMagasin.getText());
     
            

      MagI.update(magasin);
      
      try{
          
      
       FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Admin/Magasin/AfficheMagasin.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
      } catch (IOException ex) {
                Logger.getLogger(UpdateMagasinController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      
      
       });
         
    }   
    
    private boolean controleDeSaisi() {

        if (nomMagsin.getText().isEmpty() || addressMagasin.getText().isEmpty() || mailMagasin.getText().isEmpty()|| telMagasin.getText().isEmpty()
               ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[A-Z a-z 0-9]*+@+[gmail yahoo hotmail esprit live]+.+[com tn fr org en]", mailMagasin.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le mail ! ");
                mailMagasin.requestFocus();
                mailMagasin.selectEnd();
                return false;
            }
            
             if (!Pattern.matches("[0-9]", telMagasin.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le telephone ! ");
                telMagasin.requestFocus();
                telMagasin.selectEnd();
                return false;
            }
        }

        return true;
    }

    public void initChamps() {
        mailMagasin.clear();

    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
    
}
