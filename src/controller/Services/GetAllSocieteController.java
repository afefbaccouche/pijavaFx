/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.Services.CrudTableService;
import dao.Services.CrudTableSociete;
import entity.Services.Service;
import entity.Services.Societe;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author amani
 */
public class GetAllSocieteController implements Initializable {
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnUpdate;
    @FXML
    public  TextField idSociete;
    @FXML
    public  TextField nom;
    @FXML
    public TextField adresse;
    @FXML
    public TextField image;
    @FXML
    public TextField tel;
    
   
    
    @FXML
    private JFXTextField searchBox;
     
    CrudTableSociete crudtableSociete;
    private List<Societe> societes;
    private ObservableList<Societe> data; 
    
    
    @FXML
    private TableView<Societe>TabelSociete;
    @FXML
    private TableColumn<Societe, Integer> id;
    @FXML
    private TableColumn<Societe, String> NomCol;
    @FXML
    private TableColumn<Societe, Integer> Telcol;
    @FXML
    private TableColumn<Societe, String> Addressecol;
   // @FXML
   // private TableColumn<Societe, String> imagecol;
     @FXML
    private TableColumn<Societe,String> NomServiceCol;
       @FXML
    private JFXComboBox<String> NomService;
    
    
    CrudTableService crudtableService;
    ObservableList data2 ;
       
    
    @FXML
    private void  goToListeService(ActionEvent event) throws IOException {
      Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/view/Services/GetAllService.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show();  
       
    }
    
    @FXML
    private void goToAjoutSocieteButtonPushed(ActionEvent event) throws IOException {
      Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/view/Services/AjoutSociete.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show();  
    
       
    }
     @FXML
    private void goToAccueil(ActionEvent event) throws IOException {
      Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/Admin/home/Home.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show(); 
    
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
                
                
                
         data = FXCollections.observableArrayList();
        crudtableSociete=new CrudTableSociete();
          loadData();
          remplirColumn();
      
          
          /////////////////////////////update
         
            
         
                
           TabelSociete.setOnMouseClicked(e -> {
              Societe soc = TabelSociete.getSelectionModel().getSelectedItem();
              crudtableService = new CrudTableService();
      
       List<Service> listeSer = crudtableService.getAll();
       
       data2 = FXCollections.observableArrayList(); 
       
            for(Service s:listeSer)
                data2.add(s.getNom());
                NomService.setItems(data2);
                
              idSociete.setText(Integer.toString(soc.getId()));
              nom.setText(soc.getNom());
              tel.setText(soc.getTel());
               adresse.setText(soc.getAdress());
               image.setText(soc.getImage());

               
               
               
               
               
               
            btnUpdate.setOnMouseClicked(ev->{ 
         
         Societe soci = new Societe() ;
        // soci.setId(soc.getId());
         soci.setId(Integer.parseInt(idSociete.getText()));
         soci.setNom(nom.getText());
         soci.setTel(tel.getText());
         soci.setImage(image.getText());
         soci.setAdress(adresse.getText());
   soci.setService(crudtableService.rechercherServiceByDescription((String)NomService.getValue()));

         CrudTableSociete ss = new CrudTableSociete();
        ss.update(soci);
        nom.setText("");
        tel.setText("");
        adresse.setText("");
        image.setText("");
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Societe modifié avec succés!");
        alert.show();
        data.clear();
        loadData();
          remplirColumn();
         });
            });
           
         
           
          ///////////////////////////////delete
             
            btnDelete.setOnMouseClicked(ev->{ 
         
         Societe soci = new Societe();
         soci.setId(Integer.parseInt(idSociete.getText()));
         soci.setNom(nom.getText());
         soci.setTel(tel.getText());
         soci.setImage(image.getText());
         soci.setAdress(adresse.getText());
         CrudTableSociete ss = new CrudTableSociete();
         ss.delete(soci);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Societe a été supprimé avec succés!");
        alert.show();
        nom.setText("");
        tel.setText("");
        idSociete.setText("");
        adresse.setText("");
        image.setText("");
           data.clear();
        loadData();
          remplirColumn();
         });
       
           
        
           
           
           
           
           
           
           
           
           
    }    
     private void loadData() {
        societes=crudtableSociete.getAll();
    
        for(Societe s:societes){
            data.add(s);
            //System.out.println(data);
        }
        TabelSociete.setItems(data);
    }

    private void remplirColumn(){
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Telcol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        Addressecol.setCellValueFactory(new PropertyValueFactory<>("adress"));
       
     
        NomServiceCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getService().getDescription()));

    }
    
       
      
}
