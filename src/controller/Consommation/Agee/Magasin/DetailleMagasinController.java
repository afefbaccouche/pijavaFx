 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee.Magasin;

import ConnectionBaseDonn.connect;
import com.jfoenix.controls.JFXButton;
import dao.Consommation.ListDataMagasin;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class DetailleMagasinController implements Initializable {
 
    @FXML
    private Label DnomMag;

    @FXML
    private Label DaddressMag;

    @FXML
    private Label DtelMag;

    @FXML
    private Label DemailMag;
    
     @FXML
    private ImageView DimageM;
    
     
    @FXML
    private JFXButton AcceuilMD;
     
     
        @FXML
    private Button ProdMag;
    
     private Magasins selectRestau;
    
      private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    private ListDataMagasin listdata = new ListDataMagasin();
     
            Image image;
       public void RetriveData(Magasins magasin){
           
           image= new Image("/pidev/"+magasin.getImageMagasin());
        selectRestau=magasin;
        //restaurant.setIdRestaurant(Integer.parseInt(idRUP.getText()));
      int res=magasin.getIdMagasin();
           DnomMag.setText(magasin.getNomMagasin());
              DaddressMag.setText(magasin.getAdressMagasin());
               DtelMag.setText(magasin.getNumMagasin());
                DemailMag.setText(magasin.getEmailMagasin());
                 DimageM.setImage(image);
             }
           
       
       
     public DetailleMagasinController(){
    
       connection =connect.getInstance().getCnx(); 
       
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    AcceuilMD.setOnAction(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficheMagasinAgee.fxml"));
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
