/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee.Magasin;

import ConnectionBaseDonn.connect;
import com.jfoenix.controls.JFXButton;
import dao.Consommation.ListDataMagasin;
import dao.Consommation.MagasinServices;
import dao.Consommation.ProduitServices;
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
import javafx.scene.control.Label;
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
public class AfficheMagasinAgeeController implements Initializable {


    
   @FXML
    private JFXButton AcceuilM;
   
   
        @FXML
    private GridPane gridMagasin;
           Image image;
          GridPane item;
        
    
    
        // liste observable
    private final ListDataMagasin listdata = new ListDataMagasin();
         
    private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
         
     
//     
//     ObservableList<String> LRestau;
     
     
     ///gridPane
     
       public GridPane initItem(Magasins res){
        GridPane gridItem = new GridPane();
     
         System.out.println("-------------"+res.getImageMagasin());
        image= new Image("/pidev/"+res.getImageMagasin());
        ImageView imageview1 = new ImageView();
        imageview1.setImage(image);
        imageview1.setFitWidth(150);
        imageview1.setFitHeight(150);
        
          Label nom=new Label(res.getNomMagasin());
        nom.setFont(Font.font("Cambria", 28));
        nom.setMaxWidth(Double.MAX_VALUE);
        nom.setAlignment(Pos.CENTER);
        
        Label address=new Label(res.getAdressMagasin());
        address.setFont(Font.font("Cambria", 10));
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
                    loader.setLocation(getClass().getResource("/view/Consommation/Agee/DetailleMagasin.fxml"));
                    Parent tableViewParent= loader.load();
                    Scene scene = new Scene(tableViewParent);
                    
                   DetailleMagasinController controllerDetailMag = loader.getController();
                    controllerDetailMag.RetriveData(res);
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficheMagasinAgeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        
         return gridItem;
       }
     
     
  
    public AfficheMagasinAgeeController(){
    
       connection =connect.getInstance().getCnx(); 
    }
        
   
   
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             MagasinServices RESI = MagasinServices.getInstance();
        ObservableList<Magasins> list=FXCollections.observableArrayList();
        list= (ObservableList<Magasins>) RESI.getAll();
        
        
        int k=-1;
        int n;
        Magasins res;
        for(int i=0; i<=1;i++){
            n=list.size()%2==0?(list.size()/2)-1:list.size()/2;
            for(int j=0; j<=n;j++){
          
                k++;
                if(k<list.size()){
                    res=list.get(k);
                    
                    item=initItem(res);
                     
                    GridPane.setConstraints(item, i, j);
                    gridMagasin.getChildren().add(item);
                }
                
            }
        }
        
        
        
        AcceuilM.setOnAction(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/Accueil.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AfficheMagasinAgeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });
        
           
  
    }    
    
 
}
