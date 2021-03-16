/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Magasin;


import controller.Consommation.Admin.Produit.AfficheProduitController;
import dao.Consommation.ListDataMagasin;
import dao.Consommation.MagasinServices;
import entity.Consommation.Magasins;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class AfficheMagasinController implements Initializable {

    @FXML
    private Button btnAjoutM;

    @FXML
    private Button btnUpdateM;

    @FXML
    private Button btnDeleteM;
    
    
    ////table
        @FXML
    private TableView<Magasins> MagasinTable;

    @FXML
    private TableColumn<Magasins, Integer> idMColonne;

    @FXML
    private TableColumn<Magasins, String> nomMColonne;

    @FXML
    private TableColumn<Magasins, String> addressMColonne;

    @FXML
    private TableColumn<Magasins, String> telMColonne;

    @FXML
    private TableColumn<Magasins, String> emailMColonne;

    
    
        @FXML
    private Button produitDeMag;
    ///list date
    
     private final ListDataMagasin listdata = new ListDataMagasin();
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        AffichageMagasin();
        
        
        btnAjoutM.setOnAction(e->{
             try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/Magasin/AjoutMagasin.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AjoutMagasinController controllerUPRestau = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AfficheMagasinController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
        
        
        
btnUpdateM.setOnAction(e -> {

     try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Admin/Magasin/UpdateMagasin.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                UpdateMagasinController controllerUPMagasin = loader.getController();
               
                  
                controllerUPMagasin.RetriveData(MagasinTable.getSelectionModel().getSelectedItem());
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficheMagasinController.class.getName()).log(Level.SEVERE, null, ex);
            }
  

      });





       btnDeleteM.setOnAction(e -> {
              MagasinServices MagI = MagasinServices.getInstance();
      Magasins Res = new Magasins();
  

             Magasins magasin= MagasinTable.getItems()
                     .get(MagasinTable.getSelectionModel().getSelectedIndex());
           
              MagI.delete(magasin);
              
//        
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("restaurant delet avec succés!");
      alert.show();
      
      
      
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

       produitDeMag.setOnAction(e -> {
              try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/Produit/AfficheProduit.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
//                AfficheProduitController controllerProduit = loader.getController();
//               
//                  
//                controllerProduit.AffichageProduit2(MagasinTable.getSelectionModel().getSelectedItem());
//                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficheMagasinController.class.getName()).log(Level.SEVERE, null, ex);
            }
  

      });
    
      
       
      

        
    
}
    
    public void AffichageMagasin()
    {
        //listdata.getRestaurant().clear();
       // Restaurants res=new  Restaurants();
          MagasinTable.setItems(listdata.getMagasin());
        idMColonne.setCellValueFactory(new PropertyValueFactory<>("idMagasin"));
        nomMColonne.setCellValueFactory(new PropertyValueFactory<>("nomMagasin"));
        addressMColonne.setCellValueFactory(new PropertyValueFactory<>("adressMagasin"));
        telMColonne.setCellValueFactory(new PropertyValueFactory<>("numMagasin"));
        emailMColonne.setCellValueFactory(new PropertyValueFactory<>("emailMagasin"));   
      
    }
}
