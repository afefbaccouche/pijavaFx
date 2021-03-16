/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Consommation.Agee;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class TabReservationController implements Initializable {

        private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
