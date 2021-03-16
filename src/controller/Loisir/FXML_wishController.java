/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Loisir;

import controller.client.HomePageController;
import dao.Services.ServiceBricolage;
import dao.Services.ServicePrefere;
import entity.Loisir.Bricolage;
import entity.Loisir.Prefere;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Pirateos
 */
public class FXML_wishController implements Initializable {

    @FXML
    private Label nameTxt;
    @FXML
    private ImageView star;
    @FXML
    private Pane popo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   //   star = new ImageView("file:/C:/wamp64/www/pijava-master/src/controller/Loisir/Etoile-icon.png");
   
          GridPane grid = new GridPane();

    grid.setPadding(new javafx.geometry.Insets(10,10,10,10));
   
             
       
ServicePrefere sb=new ServicePrefere();
List<Bricolage>lst=sb.getPref(Session.getConnectedUser().getIdUser());
int elements=sb.counte(Session.getConnectedUser().getIdUser());


 grid.setHgap(10);
    grid.setVgap(10);
 int cols=2, colCnt = 0, rowCnt = 0;

    for (int i=0; i<elements; i++) {
        String xurl = "file:/C:/wamp64/www/pijava-master/src/pidev/video/"+lst.get(i).getUrl_bri();
    Button playButton = new Button("Play");
        Button stopButton = new Button("Stop");
          Button supprimerButton = new Button("Remove From WishList");
    System.out.println(xurl);
             MediaView mv = new MediaView();
        Media media = new Media(xurl);
        MediaPlayer  mediaplayer= new MediaPlayer(media);
        mv.setFitHeight(200);
        mv.setFitWidth(400);
        mv.setMediaPlayer(mediaplayer);
        Label label1 = new Label(lst.get(i).getTire_bri());
        Label label2 = new Label(lst.get(i).getDescription_bri());
        int gg = lst.get(i).getId();
         HBox hbxBox = new HBox(label1);
         playButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            {
                if (mediaplayer.getStatus() == MediaPlayer.Status.PLAYING) 
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
   supprimerButton.setOnAction(new EventHandler <ActionEvent>() 
        {
            public void handle(ActionEvent event) 
            { 
                ServicePrefere jbb=new ServicePrefere();
                jbb.deletewish(gg);
                  try {
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Loisir/FXML_wish.fxml"));
                supprimerButton.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
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
         HBox hbxBox4 = new HBox(playButton,stopButton,supprimerButton);
            HBox hbxBox2 = new HBox(label2);
          VBox root = new VBox(hbxBox,mv,hbxBox4,hbxBox2);
        grid.add(root,colCnt,rowCnt);
        colCnt+=1;
        if (colCnt>cols) {
            rowCnt++;
            colCnt=0;
        }
    }
    popo.getChildren().add(grid);
    }    
    
}
