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
import entity.Services.Societe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import session.Session;

/**
 * FXML Controller class
 *
 * @author amani
 */

 
public class GetAllServicesFrontController implements Initializable {

   
    public static Service ss;

     
     public int idSer;
     @FXML
    public TableView<Service> TabelService;

    @FXML
    private TableColumn<Service, Service> idCol;

    @FXML
    private TableColumn<Service, Service> NomCol;

    @FXML
    private TableColumn<Service,Service> DescriptionCol;
          
    /*@FXML
    private TableColumn<Service,Button> voirSocieteDeService;*/
          
     CrudTableService crudtableService;
    private List<Service> services;
    private ObservableList<Service> data;  
    @FXML
    private ImageView back;
    
    @FXML
    private JFXButton RendezVousAvocat;
    
    @FXML
    private JFXTextField recherche;
   
      @FXML
    private JFXButton logout;
    
    
    
    
     @FXML
    private void logOut(ActionEvent event) {
        try {
            Session.logOut();
            Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLLogin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GetSocieteFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
          
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         data = FXCollections.observableArrayList();
          crudtableService =new CrudTableService();
          loadData();
          remplirColumn();
        addButtonToTable();
     
         List<String> listc = new ArrayList<>();
        
            for(Service s:services){
                listc.add(s.getNom());
                
            }
            
        TextFields.bindAutoCompletion(recherche,listc);
        
        search();
        
        
        
         RendezVousAvocat.setOnMouseClicked(e -> {
         Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/GetRendezVousFront.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)e.getSource()).getScene().getWindow();
      
       window.hide(); //optional
       window.setScene(SocieteScene);
       window.show(); 
         
         });
            
            
        
        
        
        
        
         back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/client/FXMLHomePage.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
     });
        
    }

    
     
    private void loadData() {
services=crudtableService.getAll();
    
        for(Service s:services){
            data.add(s);
          //  System.out.println(data);
        }
        TabelService.setItems(data);
    }

    private void remplirColumn() {

      idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
      // voirSocieteDeService.setCellValueFactory(new PropertyValueFactory<>("button"));

        
    }
     
     private void addButtonToTable() {
        TableColumn<Service, Void> colBtn = new TableColumn("Consulter Societe"); 

        Callback<TableColumn<Service, Void>, TableCell<Service, Void>> cellFactory = new Callback<TableColumn<Service, Void>, TableCell<Service, Void>>() {
            @Override
            public TableCell<Service, Void> call(final TableColumn<Service, Void> param) {
                final TableCell<Service, Void> cell = new TableCell<Service, Void>() {
                    private final Button btn = new Button("Consulter");
                    
                    {
                  btn.setStyle("-fx-background-color: #898ba7; -fx-background-radius: 5em; -fx-border-radius: 5em;-fx-text-fill: #ffffff");
                        
        btn.setOnAction((ActionEvent event) -> {
        GetAllServicesFrontController.ss=getTableView().getItems().get(getIndex());
            try {
                            
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/Services/GetSocieteFront.fxml"));                    
                    Parent tableViewParent= loader.load();         
                    Scene scene = new Scene(tableViewParent);
                    GetSocieteFrontController controller = loader.getController();
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
            }
        };
        colBtn.setCellFactory(cellFactory);

       TabelService.getColumns().add(colBtn);
   
     }
     
     
     
       private void search() {
        FilteredList<Service> filterdata = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased((event) -> {
            System.out.println(recherche.getText());
            
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
              filterdata.setPredicate((Predicate<? super Service>) ser -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((ser.getNom().contains(newValue)) || 
                            (ser.getNom().toLowerCase().contains(newValue))
                            
                            
                            ){
                        return true;
                    }
                    return false;
                }) ;        
            });

            SortedList<Service> sorteddata = new SortedList<>(filterdata);         
            data= sorteddata;  
            TabelService.setItems(data);
             
        });
    }

     
     
 }
   

