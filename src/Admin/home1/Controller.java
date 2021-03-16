package Admin.home1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnSante;

    @FXML
    private Button btnEvent;

    @FXML
    private Button btnConsoma;

    @FXML
    private Button btnService;

    @FXML
    private Button btnLoisir;
    
    @FXML
    private Button btnDon;
    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Node[] nodes = new Node[10];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//
//                final int j = i;
//                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
//
//                //give the items some effect
//
//                nodes[i].setOnMouseEntered(event -> {
//                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
//                });
//                nodes[i].setOnMouseExited(event -> {
//                    nodes[j].setStyle("-fx-background-color : #02030A");
//                });
//                pnItems.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
  @FXML
    private void goToSociete(ActionEvent event) throws IOException {
      Parent SocieteParent =  FXMLLoader.load(getClass().getResource("/view/Services/GetAllSociete.fxml"));
      Scene SocieteScene = new Scene (SocieteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
    
       
    }

    public void handleClicks(ActionEvent actionEvent) {
        
          if (actionEvent.getSource() == btnDashboard) {
                    
              try {
             Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
             Scene scene=new Scene(root);
             Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
   
         
           // pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
            
             } catch (IOException e) {
                e.printStackTrace();
            }
               
                     
        }
          
       
 
          if(actionEvent.getSource()==btnSante)
        {
             pnItems.getChildren().clear();
                Node nodes  ;
                
   
            try {

              //  final int j = i;
                nodes = FXMLLoader.load(getClass().getResource("Item.fxml"));

                //give the items some effect

                /*nodes.setOnMouseEntered(event -> {
                    nodes.setStyle("-fx-background-color : #0A0E3F");
                });
                nodes.setOnMouseExited(event -> {
                    nodes.setStyle("-fx-background-color : #02030A");
                });*/
                
               
                pnItems.getChildren().add(nodes);
                 
            
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        }
          
        if (actionEvent.getSource() == btnEvent) {
                pnItems.getChildren().clear();
                Node nodes  ;
                
   
            try {

              //  final int j = i;
                nodes = FXMLLoader.load(getClass().getResource("Item.fxml"));

                //give the items some effect

                nodes.setOnMouseEntered(event -> {
                    nodes.setStyle("-fx-background-color : #0A0E3F");
                });
                nodes.setOnMouseExited(event -> {
                    nodes.setStyle("-fx-background-color : #02030A");
                });
                
               
                pnItems.getChildren().add(nodes);
                 
            
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
         if (actionEvent.getSource() == btnConsoma) {
              pnItems.getChildren().clear();
                Node nodes  ;
                
   
            try {

              //  final int j = i;

                nodes = FXMLLoader.load(getClass().getResource("/view/Consommation/Admin/AfficheRestaurants.fxml"));


                //give the items some effect

        /*        nodes.setOnMouseEntered(event -> {
                    nodes.setStyle("-fx-background-color : #0A0E3F");
                });
                nodes.setOnMouseExited(event -> {
                    nodes.setStyle("-fx-background-color : #02030A");
                });
                
               */
                pnItems.getChildren().add(nodes);
                 
            
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
  
        if (actionEvent.getSource() == btnService) {
          pnItems.getChildren().clear();
                Node nodes  ;
                  
            try {

              //  final int j = i;
                nodes = FXMLLoader.load(getClass().getResource("/view/Services/GetAllSociete.fxml"));

                //give the items some effect

                nodes.setOnMouseEntered(event -> {
                    nodes.setStyle("-fx-background-color : #0A0E3F");
                });
                nodes.setOnMouseExited(event -> {
                    nodes.setStyle("-fx-background-color : #02030A");
                });
                
               
                pnItems.getChildren().add(nodes);
                 
            
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
          if (actionEvent.getSource() == btnLoisir) {
 pnItems.getChildren().clear();
                Node nodes  ;
                
   
            try {

              //  final int j = i;
                nodes = FXMLLoader.load(getClass().getResource("Item.fxml"));

                //give the items some effect

                nodes.setOnMouseEntered(event -> {
                    nodes.setStyle("-fx-background-color : #0A0E3F");
                });
                nodes.setOnMouseExited(event -> {
                    nodes.setStyle("-fx-background-color : #02030A");
                });
                
               
                pnItems.getChildren().add(nodes);
                 
            
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
          
            if (actionEvent.getSource() == btnDon) {
   pnItems.getChildren().clear();
                Node nodes  ;
                
   
            try {

              //  final int j = i;
                nodes = FXMLLoader.load(getClass().getResource("Item.fxml"));

                //give the items some effect

                nodes.setOnMouseEntered(event -> {
                    nodes.setStyle("-fx-background-color : #0A0E3F");
                });
                nodes.setOnMouseExited(event -> {
                    nodes.setStyle("-fx-background-color : #02030A");
                });
                
               
                pnItems.getChildren().add(nodes);
                 
            
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      
        
    }
}
