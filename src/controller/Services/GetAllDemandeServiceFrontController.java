/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import dao.Services.CrudTableDemandeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entity.Services.DemandeService;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author amani
 */
public class GetAllDemandeServiceFrontController implements Initializable {

      @FXML
    private JFXButton accueil;
   DemandeService demande;
   static DemandeService ds;
   
   static DemandeService demandeserbuttonedit;
   @FXML
    private TableView<DemandeService> tabelviewDemande;

    @FXML
    private TableColumn<DemandeService, String> datecol;

    @FXML
    private TableColumn<DemandeService, String> soccol;

    @FXML
    private JFXTextArea description;
    
    List<DemandeService> demandes ;
    CrudTableDemandeService CTDS ;
    private ObservableList<DemandeService> data; 
    
    @FXML
    private void goToAccueil(ActionEvent event) throws IOException {
      Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/view/client/FXMLHomePage.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show(); 
    
       
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
            data = FXCollections.observableArrayList();
            
         CTDS=new CrudTableDemandeService();
          loadData();
        
             
        remplirColumn();
        addEditButtonToTable();
        
    tabelviewDemande.setOnMouseClicked(e -> {
     DemandeService demande = tabelviewDemande.getSelectionModel().getSelectedItem();
        System.out.println("la demande est "+demande);
     description.setText(demande.getDescription());
   });
    
    
    
    
    
    
    
 }    
    
    
     private List<DemandeService> loadData() {
        demandes=CTDS.getAll();
    
        for(DemandeService d:demandes){
            data.add(d);
            //System.out.println(d);
        }
        
        tabelviewDemande.setItems(data);
        return demandes;
    }

    private void remplirColumn(){
        
       datecol.setCellValueFactory(new PropertyValueFactory<>("datee"));
       soccol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSocietes().getNom()));
       
    }
    
    
    
   
    
    
     
          private void addEditButtonToTable() {
            TableColumn<DemandeService, Void> colBtn = new TableColumn("Edit Column"); 

        Callback<TableColumn<DemandeService, Void>, TableCell<DemandeService, Void>> cellFactory = (final TableColumn<DemandeService, Void> param) -> {
            final TableCell<DemandeService, Void> cell = new TableCell<DemandeService, Void>() {
                private final Button btn = new Button("Edit");
                {
                      btn.setStyle("-fx-background-color: #1d20c2; -fx-background-radius: 5em; -fx-border-radius: 5em;-fx-text-fill: #ffffff");

                    btn.setOnAction((ActionEvent event) -> {

                 
     GetAllDemandeServiceFrontController.ds=getTableView().getItems().get(getIndex());
                      //  System.out.println("la demande "+GetAllDemandeServiceFrontController.ds);
                        try {
                            

        demandeserbuttonedit =getTableView().getItems().get(getIndex()) ;
                // System.out.println("getdemandeservice"+demandeserbuttonedit);
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/view/Services/ModifierDemandeServiceFront.fxml"));
                            Parent tableViewParent= loader.load();
                            Scene scene = new Scene(tableViewParent);
                            ModifierDemandeServiceFrontController controller = loader.getController();
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            
                        }
                    });
                }
                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        };
        colBtn.setCellFactory(cellFactory);

       tabelviewDemande.getColumns().add(colBtn);
   
     }

    
}
