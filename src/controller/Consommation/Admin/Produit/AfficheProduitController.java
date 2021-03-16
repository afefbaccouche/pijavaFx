/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Produit;

import dao.Consommation.ListDataProduit;
import dao.Consommation.MagasinServices;
import dao.Consommation.ProduitServices;
import entity.Consommation.Magasins;
import entity.Consommation.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class AfficheProduitController implements Initializable {

        @FXML
    private TableView<Produit> ProduitTable;

    @FXML
    private TableColumn<Produit, Integer> idPColonne;

    @FXML
    private TableColumn<Produit, String> nomPColonne;

    @FXML
    private TableColumn<Produit, Float> prixPColonne;

    @FXML
    private TableColumn<Produit, Integer> QuantitPColonne;

    @FXML
    private TableColumn<Produit, String> typePColonne;

    @FXML
    private TableColumn<Produit, String> nomMagPColonne;
    
    
        @FXML
    private Button btnAjoutP;
        
 

    @FXML
    private Button btnUpdateP;

    @FXML
    private Button btnDeleteP;
    
    
      private final ListDataProduit listdata = new ListDataProduit();
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        AffichageProduit();
        
        
         btnAjoutP.setOnAction(e->{
             try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/Produit/AjoutProduit.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 //AjoutProduitController controllerAjouProuduit= loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AfficheProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
         
         ////////////////////// update
         
         btnUpdateP.setOnAction(e -> {

     try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource("/view/Consommation/Admin/Produit/UpdateProduit.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                UpdateProduitController controllerUPProduit = loader.getController();
               
                  
                controllerUPProduit.RetriveData(ProduitTable.getSelectionModel().getSelectedItem());
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficheProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
  

      });
         
         
         btnDeleteP.setOnAction(e -> {
              ProduitServices MagI = ProduitServices.getInstance();
      Produit p = new Produit();
  

             Produit produit= ProduitTable.getItems()
                     .get(ProduitTable.getSelectionModel().getSelectedIndex());
           
              MagI.delete(produit);
              
//        
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("restaurant delet avec succés!");
      alert.show();
      
      
      
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
                Logger.getLogger(AfficheProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      
      
       
       });
    }    
    
    public void AffichageProduit()
    {
 
        ProduitTable.setItems(listdata.getProduit());
        idPColonne.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        nomPColonne.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        prixPColonne.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        QuantitPColonne.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
        typePColonne.setCellValueFactory(new PropertyValueFactory<>("typeProduit"));   
      
        nomMagPColonne.setCellValueFactory(cellData -> new SimpleStringProperty
        (cellData.getValue().getMagProduit().getNomMagasin()));

    }
    
    
   
}
