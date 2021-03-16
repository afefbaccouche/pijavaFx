/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Loisir;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.Services.ServiceBricolage;
import entity.Comments;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCVALTYPE;

/**
 * FXML Controller class
 *
 * @author fahdj
 */
public class ViewVideoController implements Initializable {
    @FXML
    private MediaView mediaView;
   private Media media = new Media(VideoChoice.INSTANCE.getVideo());
    private MediaPlayer  mediaplayer= new MediaPlayer(media); 
    @FXML
    private JFXTextField TFCommentaire;
    @FXML
    private VBox vBox;
   @FXML
    private AnchorPane anchorComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        JFXTextField TFComment = new JFXTextField();
//        TFComment.setPromptText("Commenter");
       
        
                updateView();
        
        mediaView.setMediaPlayer(mediaplayer);
          mediaplayer.play();
          
         
           
    
                
                   }

    public  MediaPlayer getMediaPlayer() {
        return mediaplayer;
    }
   public void updateView()
   {
     List<Comments> list = new ArrayList<Comments>();
         ServiceBricolage sb= new ServiceBricolage();
         
        list=sb.getAllComments(VideoNumber.INSTANCE.getVideo());
       
//       anchorComment.getChildren().add(TFComment);
     for(Comments C:list)
     {
     boolean modifier=session.Session.getConnectedUser().getNomUser().equals(C.getUsername());
      Button modifierC= new Button("Modifier");
     Label luser= new Label(C.getUsername());
         TextField tf= new TextField(C.getCommentaire());
        
            tf.setDisable(true);
       
         Label ldate = new Label(C.getDate());
         VBox vBox2= new VBox(0,luser,tf,ldate);
         
         vBox2.setStyle("-fx-background-color: #F8F8FF	;");
        
        vBox.getChildren().add(vBox2);
         if(modifier)
         {vBox2.getChildren().add(modifierC);}
         modifierC.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               if (modifierC.getText().equals("Modifier"))
             
                {tf.setDisable(false);
                modifierC.setText("Valider");}
               else {
               tf.setDisable(true);
sb.updateComment(tf.getText(), C.getId());
                   modifierC.setText("Modifier");
               }
                
             }
         });
         
     }
   }
     @FXML
    void exit(MouseEvent event) {
Stage stage= (Stage) mediaView.getScene().getWindow();
mediaplayer.stop();
stage.close();


    }
           
  

    @FXML
    void Comment(ActionEvent event) {
if(!TFCommentaire.getText().isEmpty())
{
    ServiceBricolage sb = new ServiceBricolage();
    sb.addComment(VideoNumber.INSTANCE.getVideo(), TFCommentaire.getText(),session.Session.getConnectedUser().getIdUser() );
    vBox.getChildren().clear();
    updateView();
    
}




    }
        
    
}
