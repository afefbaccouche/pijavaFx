/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee;

import ConnectionBaseDonn.connect;
import com.jfoenix.controls.JFXButton;
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
public class AfficheProduitController implements Initializable {

    
    @FXML
    private JFXButton AcceuilP;
    
        @FXML
    private GridPane gridP;
          Image image;
          GridPane item;
          
          
          
           private final ListData listdata = new ListData();
         
             private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
         
       public GridPane initItem(Produit res){
        GridPane gridItem = new GridPane();
        
        System.out.println("-------------"+res.getImageProduit());
        image= new Image("/pidev/"+res.getImageProduit());
        ImageView imageview1 = new ImageView();
        imageview1.setImage(image);
        imageview1.setFitWidth(150);
        imageview1.setFitHeight(150);
        
    
//           Button btn = new Button();
//        btn.setText("Achat");
       
     
          Label nom=new Label(res.getNomProduit());
        nom.setFont(Font.font("Cambria", 28));
        nom.setMaxWidth(Double.MAX_VALUE);
        nom.setAlignment(Pos.CENTER);
        
        Label prix=new Label(""+res.getPrixProduit());
        prix.setFont(Font.font("Cambria", 18));
        prix.setMaxWidth(Double.MAX_VALUE);
        prix.setAlignment(Pos.CENTER);
        
        
        GridPane.setConstraints(imageview1, 0, 0);
        GridPane.setConstraints(nom, 0, 1);
        GridPane.setConstraints(prix, 0, 2);
        //GridPane.setConstraints(btn, 1, 2);
        
        gridItem.getChildren().addAll(imageview1,nom,prix);//,btn);
       
        gridItem.setPrefWidth(350);
        gridItem.setPrefHeight(220);
        gridItem.setCenterShape(true);
     
        
          gridItem.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/Consommation/Agee/detail.fxml"));
                    Parent tableViewParent= loader.load();
                    Scene scene = new Scene(tableViewParent);
//                    
                   DetailController controllerDetailRestau = loader.getController();
                    controllerDetailRestau.RetriveData(res);
                    
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficheProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
//
//btn.setOnAction(e->{
//    System.out.println("hiiiiiiiii");
//});
        
         return gridItem;
       } 
       
        public AfficheProduitController(){
    
       connection =connect.getInstance().getCnx(); 
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ProduitServices RESI = ProduitServices.getInstance();
        ObservableList<Produit> list=FXCollections.observableArrayList();
        list= (ObservableList<Produit>) RESI.getAll();
        
        
        int k=-1;
        int n;
        Produit res;
        for(int i=0; i<=1;i++){
            n=list.size()%2==0?(list.size()/2)-1:list.size()/2;
            for(int j=0; j<=n;j++){
          
                k++;
                if(k<list.size()){
                    res=list.get(k);
                    
                    item=initItem(res);
                     
                    GridPane.setConstraints(item, i, j);
                    gridP.getChildren().add(item);
                }
                
            }
            
        }



 AcceuilP.setOnAction(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficheMagasinAgee.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AfficheProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });



     
// 
    }    
    
}
