/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import static controller.Services.GetRendezVousFrontController.Rv;
import dao.Services.CrudTableRendezVous;
import entity.Services.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class AvocatRendezVousController implements Initializable {

     CrudTableRendezVous CTRV ;
    private ObservableList<RendezVous> data;
    List<RendezVous> rendezvous;
    
    static RendezVous Rv;
    
    @FXML
    private TableColumn<RendezVous, Integer> id;

    @FXML
    private TableColumn<RendezVous,String> description;

    @FXML
    private TableColumn<RendezVous,Time> time;

    @FXML
    private TableColumn<RendezVous,Date> date;

    @FXML
    private TableColumn<RendezVous,String> reponse;


    @FXML
    private TableColumn<RendezVous,String> avocat;
    
    @FXML
    private TableView<RendezVous> tableviewRendezVous;
    @FXML
    private TableColumn<RendezVous,Integer> etat;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          data = FXCollections.observableArrayList();
        CTRV=new CrudTableRendezVous();
          loadData();
          remplirColumn();
          updateRendezVousButton();
    }    

    private void loadData() {
 rendezvous=CTRV.getAllAvocat();
    
        for(RendezVous s:rendezvous){
            data.add(s);
          System.out.println("data obsevable get rendez vous"+data);
        }
        tableviewRendezVous.setItems(data);



    }

    private void remplirColumn() {
  id.setCellValueFactory(new PropertyValueFactory<>("id"));
       description.setCellValueFactory(new PropertyValueFactory<>("description"));
       time.setCellValueFactory(new PropertyValueFactory<>("time"));
       date.setCellValueFactory(new PropertyValueFactory<>("date"));
       reponse.setCellValueFactory(new PropertyValueFactory<>("reponseAvocat"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       avocat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvocat().getUserName()));


    }
    
    
      private void updateRendezVousButton() {
        TableColumn<RendezVous, Void> colBtn = new TableColumn("Repondre au rendez vous"); 

        Callback<TableColumn<RendezVous, Void>, TableCell<RendezVous, Void>> cellFactory = new Callback<TableColumn<RendezVous, Void>, TableCell<RendezVous, Void>>() {
            @Override
            public TableCell<RendezVous, Void> call(final TableColumn<RendezVous, Void> param) {
                final TableCell<RendezVous, Void> cell = new TableCell<RendezVous, Void>() {
                    private final Button btn = new Button("Accepter ");
                    {
                        
        btn.setOnAction((ActionEvent e) -> {
     Rv=getTableView().getItems().get(getIndex());
            System.out.println("RV"+Rv);
       
              Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/ModifierRendezVous.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)e.getSource()).getScene().getWindow();
      
       window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();       
     
            
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

       tableviewRendezVous.getColumns().add(colBtn);
   
     }
    
    
    
}
