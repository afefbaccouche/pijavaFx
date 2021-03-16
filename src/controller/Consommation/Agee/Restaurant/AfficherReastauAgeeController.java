/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee.Restaurant;

import ConnectionBaseDonn.connect;
import com.jfoenix.controls.JFXButton;
import dao.Consommation.ListData;
import dao.Consommation.RestaurentServices;
import entity.Consommation.Restaurants;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;


import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class AfficherReastauAgeeController implements Initializable {

//        @FXML
//    private ListView<Restaurants> ListViewRestau;
//        

   

  @FXML
    private TableView<Restaurants> RestarantsTableAgee;

    @FXML
    private TableColumn<Restaurants, Integer> idRColonneAgee;

    @FXML
    private TableColumn<Restaurants, String> nomRColonneAgee;

    @FXML
    private TableColumn<Restaurants, String> addressRColonneAgee;

    @FXML
    private TableColumn<Restaurants, String> telRColonneAgee;

    @FXML
    private TableColumn<Restaurants, String> emailRColonneAgee;

        
    

    
    
    @FXML
    private JFXButton AcceuilR;;
    
    
        @FXML
    private GridPane gridRestau;
         Image image;
          GridPane item;
        
    @FXML
    private Button btnDetaille;
    
        // liste observable
    private final ListData listdata = new ListData();
         
             private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
         
     
//     
//     ObservableList<String> LRestau;
     
     
     ///gridPane
     
     
     
       public GridPane initItem(Restaurants res){
        GridPane gridItem = new GridPane();
        
        System.out.println("-------------"+res.getImagRestau() );
        image= new Image("/pidev/"+res.getImagRestau());
        ImageView imageview1 = new ImageView();
        imageview1.setImage(image);
        imageview1.setFitWidth(150);
        imageview1.setFitHeight(150);
     
          Label nom=new Label(res.getNomRestaurant());
        nom.setFont(Font.font("Cambria", 28));
        nom.setMaxWidth(Double.MAX_VALUE);
        nom.setAlignment(Pos.CENTER);
        
        Label address=new Label(res.getAdressRestaurant());
        address.setFont(Font.font("Cambria", 18));
        address.setMaxWidth(Double.MAX_VALUE);
        address.setAlignment(Pos.CENTER);
        
        
        GridPane.setConstraints(imageview1, 0, 0);
        GridPane.setConstraints(nom, 0, 1);
        GridPane.setConstraints(address, 0, 2);
        
        gridItem.getChildren().addAll(imageview1,nom,address);
       
        gridItem.setPrefWidth(350);
        gridItem.setPrefHeight(220);
        gridItem.setCenterShape(true);
     
        
          gridItem.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/Consommation/Agee/DetailleRestau.fxml"));
                    Parent tableViewParent= loader.load();
                    Scene scene = new Scene(tableViewParent);
                    
                   DetailleRestauController controllerDetailRestau = loader.getController();
                    controllerDetailRestau.RetriveData(res);
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherReastauAgeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        
         return gridItem;
       }
     
     
     
     
     
     
     
     
     
    public AfficherReastauAgeeController(){
    
       connection =connect.getInstance().getCnx(); 
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            
        RestaurentServices RESI = RestaurentServices.getInstance();
        ObservableList<Restaurants> list=FXCollections.observableArrayList();
        list= (ObservableList<Restaurants>) RESI.getAll();
        
        
        int k=-1;
        int n;
        Restaurants res;
        for(int i=0; i<=1;i++){
            n=list.size()%2==0?(list.size()/2)-1:list.size()/2;
            for(int j=0; j<=n;j++){
          
                k++;
                if(k<list.size()){
                    res=list.get(k);
                    
                    item=initItem(res);
                     
                    GridPane.setConstraints(item, i, j);
                    gridRestau.getChildren().add(item);
                }
                
            }
        }


//btnDetaille.setOnAction(e->{
//     try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/view/Consommation/Agee/DetailleRestau.fxml"));
//                Parent tableViewParent= loader.load();
//                Scene scene = new Scene(tableViewParent);
//                
//                DetailleRestauController controllerUPRestau = loader.getController();
//                //  RestarantsTable.setItems(listdata.getRestaurant());
//                  
//                controllerUPRestau.RetriveData(RestarantsTableAgee.getSelectionModel().getSelectedItem());
//                
//                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                Logger.getLogger(AfficherReastauAgeeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//  
// });




     
    AcceuilR.setOnMouseClicked(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/Accueil.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AfficherReastauAgeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
    
    
//    
//     checkRestau.setOnMouseClicked(e->{
//            if(checkRestau.isSelected())
//            {
//                type="food";
//                VBoxFood.setVisible(true);
//            }
//            else
//            {
//                  VBoxFood.setVisible(false);
//            }
//        });

    }    
    
    
    
//    private void setCellValue()
//    {
//       
//         
//        RestarantsTableAgee.setOnMouseClicked(e ->
//        {
//              
//             Restaurants retaurant= RestarantsTableAgee.getItems()
//                     .get(RestarantsTableAgee.getSelectionModel().getSelectedIndex());
////             nomRUP.setText(retaurant.getNomRestaurant());
////              adressRUP.setText(retaurant.getAdressRestaurant());
////               telRUP.setText(retaurant.getNumRestaurant());
////                mailRUP.setText(retaurant.getEmailRestaurant());
////           
////            idRUP.setText(Integer.toString(retaurant.getIdRestaurant()));
//
//            
//        });
//    }
    
//    public void AffichageRestaurant()
//    {
//        //listdata.getRestaurant().clear();
//       // Restaurants res=new  Restaurants();
//          RestarantsTableAgee.setItems(listdata.getRestaurant());
//        idRColonneAgee.setCellValueFactory(new PropertyValueFactory<>("idRestaurant"));
//        nomRColonneAgee.setCellValueFactory(new PropertyValueFactory<>("nomRestaurant"));
//        addressRColonneAgee.setCellValueFactory(new PropertyValueFactory<>("adressRestaurant"));
//        telRColonneAgee.setCellValueFactory(new PropertyValueFactory<>("numRestaurant"));
//        emailRColonneAgee.setCellValueFactory(new PropertyValueFactory<>("emailRestaurant"));   
//      
//    }
    
   
    
}
