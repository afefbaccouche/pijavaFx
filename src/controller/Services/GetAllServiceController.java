/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.CrudTableService;
import entity.Services.Service;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class GetAllServiceController implements Initializable {

       @FXML
    private TableView<Service> TabelService;

    @FXML
    private TableColumn<Service, Service> idCol;

    @FXML
    private TableColumn<Service, Service> NomCol;

    @FXML
    private TableColumn<Service,Service> DescriptionCol;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXTextField idService;
    
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    
    CrudTableService crudtableService;
    private List<Service> services;
    private ObservableList<Service> data;  
    
    @FXML
    void goToAccueil(ActionEvent event)throws IOException {
         Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/Admin/home/Home.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show();  
    }
     @FXML
    private void  goToAjoutService(ActionEvent event) throws IOException {
      Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/view/Services/AjoutService.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show();  
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         data = FXCollections.observableArrayList();
          crudtableService =new CrudTableService();
          loadData();
          remplirColumn();
        
        
         /////////////////////////////update
         
           TabelService.setOnMouseClicked(e -> {
              Service ser = TabelService.getSelectionModel().getSelectedItem();
              idService.setText(Integer.toString(ser.getId()));
              System.out.println(ser.getId());
              nom.setText(ser.getNom());
              description.setText(ser.getDescription());
              
             
            btnUpdate.setOnMouseClicked(ev->{ 
         
         Service service = new Service() ;
        
         service.setId(Integer.parseInt(idService.getText()));
         service.setNom(nom.getText());
         service.setDescription(description.getText());
        
         CrudTableService ss = new CrudTableService();
         ss.update(service);
        idService.setText("");
        nom.setText("");
        description.setText("");
        
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Service modifié avec succés!");
        alert.show();
        data.clear();
        loadData();
          remplirColumn();
         });
            });
           
        
        
           
           
            ///////////////////////////////delete
             
            btnDelete.setOnMouseClicked(ev->{ 
         
         Service ser = new Service() ;
        // soci.setId(soc.getId());
         ser.setId(Integer.parseInt(idService.getText()));
         ser.setNom(nom.getText());
         ser.setDescription(description.getText());
         
         CrudTableService ss = new CrudTableService();
         ss.delete(ser);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Service a été supprimé avec succés!");
        alert.show();
        nom.setText("");
        description.setText("");
        idService.setText("");

           data.clear();
        loadData();
          remplirColumn();
         });
        
        
        
    }    

    private void loadData() {

       services=crudtableService.getAll();
    
        for(Service s:services){
            data.add(s);
            System.out.println(data);
        }
        TabelService.setItems(data);

    }

    private void remplirColumn() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
       




    }
    
}
