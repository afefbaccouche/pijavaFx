/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Restaurant;

import ConnectionBaseDonn.connect;
import controller.Consommation.Admin.Magasin.AfficheMagasinController;
import controller.Consommation.Admin.Magasin.AjoutMagasinController;
import controller.Consommation.Admin.Magasin.UpdateMagasinController;
import controller.Consommation.Admin.Produit.AfficheProduitController;
import controller.Consommation.Admin.Produit.UpdateProduitController;


import dao.Consommation.ListData;
import dao.Consommation.ListDataMagasin;
import dao.Consommation.ListDataProduit;
import dao.Consommation.MagasinServices;
import dao.Consommation.ProduitServices;
import dao.Consommation.RestaurentServices;
import entity.Consommation.Magasins;
import entity.Consommation.Produit;
import entity.Consommation.Restaurants;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Pagination;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 *
 * @author soumaya ch
 */
public class AfficherRestaurantController implements Initializable{

    int from=0,to=0;
    
      private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    
     ////// affiche
   
     
     
     //// table restau
     
    @FXML
    private TableView<Restaurants> RestarantsTable;
    
      @FXML
   public static TableView<Restaurants> RestarantsTable2;
    
       @FXML
    private TableColumn<Restaurants, Integer> idRColonne;
       @FXML
    private TableColumn<Restaurants, String> addressRColonne;
    @FXML
    private TableColumn<Restaurants, String> nomRColonne;
    @FXML
    private TableColumn<Restaurants, String> telRColonne;

    @FXML
    private TableColumn<Restaurants, String> emailRColonne;
    
    @FXML
    private TableColumn<Restaurants, String> ImageColonne;
    
        @FXML
    private TableColumn<Restaurants, String>  TypeColonne;
   
         @FXML
    private Pagination pagination;
       
       @FXML
    private Pagination paginationM;
       
           @FXML
    private Pagination paginationP;
    
   private final ListData listdata = new ListData();
     

    @FXML
    private Button btnAjoutR;
     
    @FXML
    private Button  btnUpdateR;
     //////////////////
     
  
    
    /////////////////// delete
    @FXML
    private Button  btnDelete;
    ///////////////////
    
    /////////////// table magadin
    
    
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
    
     private final ListDataMagasin listdataM = new ListDataMagasin();
    
    
    ///////////////////////////////////////////////////////////////
     
     /////////////////////Produit
     
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
    
    
    
    
      private final ListDataProduit listdataP = new ListDataProduit();
      
     //////////////////////////////////////////////////
    
    
    
    
    
      private final static int dataSize=100;
       private final static int rowPage=10;
     private final List<Restaurants> data=createData();
       
       
       private List<Restaurants> createData()
       {
           List<Restaurants>data=new ArrayList<>(dataSize);
           data=listdata.getRestaurant();
          // listdata.getRestaurant().size(dataSize);
           return data;
           
       }
       
       
       private Node createPage(int pageIndex)
       {
          
           int fromIndex=pageIndex *rowPage;
           int toIndex=Math.min(fromIndex +rowPage,data.size() );
             RestarantsTable.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
           
             return RestarantsTable;
       }
       
       
       
       
       ////////////// mmmmmmgasin page
       private final static int dataSizeM=100;
       private final static int rowPageM=10;
     private final List<Magasins> dataM=createDataM();
       
       
       private List<Magasins> createDataM()
       {
           List<Magasins>dataM=new ArrayList<>(dataSizeM);
           dataM=listdataM.getMagasin();
          // listdata.getRestaurant().size(dataSize);
           return dataM;
           
       }
       
       
       private Node createPageM(int pageIndex)
       {
          
           int fromIndex=pageIndex *rowPageM;
           int toIndex=Math.min(fromIndex +rowPageM,dataM.size() );
             MagasinTable.setItems(FXCollections.observableArrayList(dataM.subList(fromIndex, toIndex)));
           
             return MagasinTable;
       }
       
       
       /////////
       
       ///////////////////// produit pagination
         private final static int dataSizeP=100;
       private final static int rowPageP=10;
     private final List<Produit> dataP=createDataP();
       
       
       private List<Produit> createDataP()
       {
           List<Produit>dataP=new ArrayList<>(dataSizeP);
           dataP=listdataP.getProduit();
          // listdata.getRestaurant().size(dataSize);
           return dataP;
           
       }
       
       
       private Node createPageP(int pageIndex)
       {
          
           int fromIndex=pageIndex *rowPageP;
           int toIndex=Math.min(fromIndex +rowPageP,dataP.size() );
             ProduitTable.setItems(FXCollections.observableArrayList(dataP.subList(fromIndex, toIndex)));
           
             return ProduitTable;
       }
       
       //////////////////////////
       
       public AfficherRestaurantController(){
    
       connection =connect.getInstance().getCnx(); 
    }
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
// AffichageRestaurant();
//  setCellValue();
 

    pagination.setPageFactory(this :: createPage);
     paginationM.setPageFactory(this :: createPageM);
    paginationP.setPageFactory(this :: createPageP);
    
           
    
    ///////////////// image
//    im =new Image("http://localhost/inlovewithsymfony/web/image1/"+item.getImage());
//    
//                System.out.println(item.getImage()+"111");
//     
//                
//               imge.setImage(im);
//    
//   Image image;
// image= new Image("/pidev/"+res.getImagRestau());
//        ImageView imageview1 = new ImageView();


//Restaurants res=new Restaurants();
//Image image;
// System.out.println("-------------"+res.getImagRestau() );
//        image= new Image("/pidev/"+res.getImagRestau());
//        ImageView imageview1 = new ImageView();
    //RestarantsTable.setItems(listdata.getRestaurant());
        idRColonne.setCellValueFactory(new PropertyValueFactory<>("idRestaurant"));
        nomRColonne.setCellValueFactory(new PropertyValueFactory<>("nomRestaurant"));
        addressRColonne.setCellValueFactory(new PropertyValueFactory<>("adressRestaurant"));
        telRColonne.setCellValueFactory(new PropertyValueFactory<>("numRestaurant"));
        emailRColonne.setCellValueFactory(new PropertyValueFactory<>("emailRestaurant"));  
         /// ImageColonne.setCellValueFactory(new PropertyValueFactory<>(imageview1.setImage(image))); 
        TypeColonne.setCellValueFactory(new PropertyValueFactory<>("Type")); 
  
  
  
  
  
  
  
  
/////////////// ajout
btnAjoutR.setOnAction(e->{
    try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Admin/AjoutRestaurant.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 AjoutRestaurantController controllerUPRestau = loader.getController();
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(AfficherRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            }
});



////////////////// update 2



btnUpdateR.setOnAction(e -> {

     try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Admin/UpdateRestau.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                UpdateRestaurantController controllerUPRestau = loader.getController();
                //  RestarantsTable.setItems(listdata.getRestaurant());
                  
                controllerUPRestau.RetriveData(RestarantsTable.getSelectionModel().getSelectedItem());
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            }
  
 });







 
       btnDelete.setOnAction(e -> {
      
      RestaurentServices ResI = RestaurentServices.getInstance();
      Restaurants Res = new Restaurants();
   
             Restaurants retaurant= RestarantsTable.getItems()
                     .get(RestarantsTable.getSelectionModel().getSelectedIndex());
             
           
              ResI.delete(retaurant);
              
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("restaurant delet avec succés!");
      alert.show();

       
              
                try{
          
      
       FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Admin/AfficheRestaurants.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
      } catch (IOException ex) {
                Logger.getLogger(AfficherRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            }
//      

 //setCellValue();
        });
 
 
       
       
       
       /////////////////////////////// magasin
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
                
//              
//                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficheMagasinController.class.getName()).log(Level.SEVERE, null, ex);
            }
  

      });
    
      
        /////////////////////////// Produit
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
         
         /////////////////////////////////////////// fin produit
      
       
       ////////////////////
}
  
//   
    
    private void setCellValue()
    {
 
         
        RestarantsTable.setOnMouseClicked(e ->
        {
              
             Restaurants retaurant= RestarantsTable.getItems()
                     .get(RestarantsTable.getSelectionModel().getSelectedIndex());
//          

            
        });
        
        
        
       
    }
    
//    public void AffichageRestaurant()
//    {
//      
//          RestarantsTable.setItems(listdata.getRestaurant());
//        idRColonne.setCellValueFactory(new PropertyValueFactory<>("idRestaurant"));
//        nomRColonne.setCellValueFactory(new PropertyValueFactory<>("nomRestaurant"));
//        addressRColonne.setCellValueFactory(new PropertyValueFactory<>("adressRestaurant"));
//        telRColonne.setCellValueFactory(new PropertyValueFactory<>("numRestaurant"));
//        emailRColonne.setCellValueFactory(new PropertyValueFactory<>("emailRestaurant"));   
//      
////        
//    }
    
  public void AffichageMagasin()
    {
        //listdata.getRestaurant().clear();
       // Restaurants res=new  Restaurants();
          MagasinTable.setItems(listdataM.getMagasin());
        idMColonne.setCellValueFactory(new PropertyValueFactory<>("idMagasin"));
        nomMColonne.setCellValueFactory(new PropertyValueFactory<>("nomMagasin"));
        addressMColonne.setCellValueFactory(new PropertyValueFactory<>("adressMagasin"));
        telMColonne.setCellValueFactory(new PropertyValueFactory<>("numMagasin"));
        emailMColonne.setCellValueFactory(new PropertyValueFactory<>("emailMagasin"));   
      
    }
  
  
   public void AffichageProduit()
    {
 
        ProduitTable.setItems(listdataP.getProduit());
        idPColonne.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        nomPColonne.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        prixPColonne.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        QuantitPColonne.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
        typePColonne.setCellValueFactory(new PropertyValueFactory<>("typeProduit"));   
      
        nomMagPColonne.setCellValueFactory(cellData -> new SimpleStringProperty
        (cellData.getValue().getMagProduit().getNomMagasin()));

    }
}
