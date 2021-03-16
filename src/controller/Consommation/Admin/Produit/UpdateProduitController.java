/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Produit;

import ConnectionBaseDonn.connect;
import dao.Consommation.ListData;
import dao.Consommation.ProduitServices;
import entity.Consommation.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class UpdateProduitController implements Initializable {
   @FXML
    private TextField nomProduit;

    @FXML
    private TextField prixProduit;

    @FXML
    private TextField QuantProduit;

    @FXML
    private TextField typeProduit;

    @FXML
    private Label magProduit;

    @FXML
    private ImageView imageView;

    @FXML
    private Button btnSaveUpP;
    
     private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    private ListData listdata = new ListData();
    
    
    
      public UpdateProduitController(){
    
       connection =connect.getInstance().getCnx(); 
    }
      
      private Produit selectRestau;


  public void RetriveData(Produit produit){
    selectRestau=produit;
    //restaurant.setIdRestaurant(Integer.parseInt(idRUP.getText()));
    nomProduit.setText(produit.getNomProduit());
    prixProduit.setText(Float.toString(produit.getPrixProduit()));
    QuantProduit.setText(Integer.toString(produit.getQuantiteProduit()));
    typeProduit.setText(produit.getTypeProduit());
    magProduit.setText(produit.getMagProduit().getNomMagasin());
    
  }
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnSaveUpP.setOnAction(e->{
           ProduitServices MagI = ProduitServices.getInstance();
           
      Produit Res = new Produit();
  

        
             Produit produit= selectRestau;
           
            produit.setNomProduit(nomProduit.getText());
            
              produit.setPrixProduit(Float.parseFloat(prixProduit.getText()));
              
                produit.setQuantiteProduit(Integer.parseInt(QuantProduit.getText()));
                
                produit.setTypeProduit(typeProduit.getText());
     
            

      MagI.update(produit);
      
      try{
          
      
       FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/Produit/AfficheProduit.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
      } catch (IOException ex) {
                Logger.getLogger(UpdateProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      
      
       });
         
    }    
    
}
