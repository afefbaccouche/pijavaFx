/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Consommation.Agee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class RatingController implements Initializable {

      @FXML
    private Rating Rating;
      
         @FXML
    private Label msg;
      
      @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	Rating.ratingProperty().addListener(new ChangeListener<Number>() {
            
            @Override 
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              msg.setText("Rating : "+ t1.toString());
            }

            
        });
        		
	}
      
   
        
    
}
