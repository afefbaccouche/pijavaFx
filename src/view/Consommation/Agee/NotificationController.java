/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Consommation.Agee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class NotificationController implements Initializable {

  
    @FXML
    private Button notif;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        notif.setOnAction(e->{
            Notifications notification=Notifications.create()
                    .title("notif")
                    .text("hhhhhh")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("click");
                }
                        
                    });
            notification.showConfirm();
                    
        });
    }    
    
}
