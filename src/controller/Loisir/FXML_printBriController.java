/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Loisir;

import com.jfoenix.controls.JFXButton;
import dao.Services.ServiceBricolage;
import dao.Services.ServicePrefere;
import entity.Loisir.Bricolage;
import entity.Loisir.Prefere;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Pirateos
 */
public class FXML_printBriController implements Initializable {
  
   double xOffset=0;
double yOffset=0;
    @FXML
    private StackPane stc;

    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane panell;
    @FXML
    private ScrollPane scroll;
    @FXML
    private JFXButton bck;
    @FXML
    private JFXButton bck1;

 
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
AlertMaker AlertMaker=new AlertMaker();

          GridPane grid = new GridPane();

    grid.setPadding(new javafx.geometry.Insets(10,10,10,10));
   
             
       
ServiceBricolage sb=new ServiceBricolage();
List<Bricolage>lst=sb.getAll();
int elements=sb.counte();


 grid.setHgap(10);
 grid.setVgap(10);
 int cols=2, colCnt = 0, rowCnt = 0;

    for (int i=0; i<elements; i++) {
        String xurl = "file:/C:/wamp64/www/pijava-masterLaste/src/pidev/video/"+lst.get(i).getUrl_bri();
    Button playButton = new Button("Play");
        Button stopButton = new Button("Stop");
          Button AddtoWishlist = new Button("Add To WishList");
        Button chooseButton= new Button("View video");
    System.out.println(xurl);
             MediaView mv = new MediaView();
        Media media = new Media(xurl);
        MediaPlayer  mediaplayer= new MediaPlayer(media);
        mv.setFitHeight(200);
        mv.setFitWidth(400);
        mv.setMediaPlayer(mediaplayer);
        Label label1 = new Label(lst.get(i).getTire_bri());
        Label label2 = new Label(lst.get(i).getDescription_bri());
         int x=lst.get(i).getId();
         HBox hbxBox = new HBox(label1);
         playButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
                if (mediaplayer.getStatus() == Status.PLAYING) 
                {
                    mediaplayer.stop();
                    mediaplayer.play();
                } 
                else
                {
                    mediaplayer.play();
                }
            }
        });     
 
        stopButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
                mediaplayer.stop();
            }
        });     
        
        
        chooseButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
              VideoChoice.INSTANCE.setVideo(xurl);
              VideoNumber.INSTANCE.setVideo(x);
              
               Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/Loisir/ViewVideo.fxml"));
                
        
                   
                    Stage stage= new Stage();
                    
                    
                    
                    
                    
                    
                     Scene scene = new Scene(root);
                     
                     
                          root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
                 
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               
                stage.setX(event.getScreenX() - xOffset);
             stage.setY(event.getScreenY() - yOffset);
               stage.setOpacity(0.8);
                
               
            }
        });
        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               
                
             stage.setOpacity(1);
                
               
            }
        });
                    stage.initStyle(StageStyle.UNDECORATED);
                 
//               stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                   stage.setScene(scene);
                
                stage.show();
                
                
                } catch (IOException ex) {
                   ex.printStackTrace();
                }
                
            }
        });     
        
         AddtoWishlist.setOnAction(new EventHandler <ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
                Prefere j=new Prefere();
                j.setClient_pref_id(Session.getConnectedUser().getIdUser());
                j.setBri_pref_id(x);
                System.out.println(x);
                ServicePrefere i=new ServicePrefere();
              i.add(j);
            
               AlertMaker.showMaterialDialog(stc,pane, new ArrayList<>(), "wishlist", "Added To wishlist");
            }
        });
         HBox hbxBox4 = new HBox(playButton,AddtoWishlist,stopButton,chooseButton);
            HBox hbxBox2 = new HBox(label2);
          VBox root = new VBox(hbxBox,mv,hbxBox4,hbxBox2);
        grid.add(root,colCnt,rowCnt);
        colCnt+=1;
        if (colCnt>cols) {
            rowCnt++;
            colCnt=0;
        }
    }
 
    
    

  
panell.getChildren().add(grid);
    
  
    
    
    
    
    }    

    @FXML
    private void goback(ActionEvent event) {
   
            try {
                
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/client/FXMLHomePage.fxml"));
                bck.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            
      
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try{ Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Loisir/FXML_addBri.fxml"));
                bck.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            
    }
    
}
