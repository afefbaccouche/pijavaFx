/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Restaurant;

import ConnectionBaseDonn.connect;
import dao.Consommation.ListData;
import dao.Consommation.RestaurentServices;
import entity.Consommation.Restaurants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author soumaya ch
 */
public class UpdateRestaurantController implements Initializable{
    
      private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    private ListData listdata = new ListData();
            
           
      public UpdateRestaurantController(){
    
       connection =connect.getInstance().getCnx(); 
    }

      
      
        @FXML
    private TextField nomRestau;

    @FXML
    private TextField addressRestau;


    @FXML
    private TextField mailRestau;

    @FXML
    private TextField telRestau;
    
    
    @FXML
    private Button btnSaveUp;

       @FXML
    private ImageView imageViewR;
      
      private Restaurants selectRestau;


     public void RetriveData(Restaurants restaurant){
     selectRestau=restaurant;
//restaurant.setIdRestaurant(Integer.parseInt(idRUP.getText()));
     nomRestau.setText(restaurant.getNomRestaurant());
     addressRestau.setText(restaurant.getAdressRestaurant());
     telRestau.setText(restaurant.getNumRestaurant());
     mailRestau.setText(restaurant.getEmailRestaurant());

   // idRUP.setText(Integer.toString(retaurant.getIdRestaurant()));
}

          
    JFileChooser  chosser;
    File file;
     String locat,format;    
              
               @FXML
    private Button btnsaveImage;
           
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       btnSaveUp.setOnAction(e->{
           
             if (!controleDeSaisi()) {
            if (nomRestau.getText().isEmpty()) {
                nomRestau.setText("");
            }
            if (addressRestau.getText().isEmpty()) {
                addressRestau.setText("");
            }

            if (telRestau.getText().isEmpty()) {
                telRestau.setText("");
            }
            if (mailRestau.getText().isEmpty()) {
                mailRestau.setText("");
            }
      
  } else {
           
            String imageData="";
       
       try {
      
         
           
           BufferedImage imag = ImageIO.read(file);
          imag = ImageIO.read(file);
   
         imageData=file.getName();
           
        locat =System.getProperty("user.dir")+"/src/pidev/images/"+imageData;
                    locat=locat.replace("\\", "/");
         format=file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
      
         ImageIO.write(imag, format, new File(locat));
         
      } catch (IOException ex) {
          Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
      }
           
           RestaurentServices ResI = RestaurentServices.getInstance();
      Restaurants Res = new Restaurants();
     // AffichageRestaurant();

        
             Restaurants retaurant= selectRestau;
            //  retaurant.setIdRestaurant(Integer.parseInt(nomRestau.getText()));
            retaurant.setNomRestaurant(nomRestau.getText());
              retaurant.setAdressRestaurant(addressRestau.getText());
                retaurant.setNumRestaurant(telRestau.getText());
                retaurant.setEmailRestaurant(mailRestau.getText());
     
            

      ResI.update(retaurant);
      
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
      
             }
      
       });
       
        btnsaveImage.setOnAction(e->{
            
            try{
                
                
          chosser=new JFileChooser();
   //  chosser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png", "*.jpg", "*.jpeg"));
            chosser.showOpenDialog(null);
            file=chosser.getSelectedFile();
       
            BufferedImage Bimg=ImageIO.read(file);
            
            Image img=SwingFXUtils.toFXImage(Bimg, null);
            
            imageViewR.setImage(img);
            }
            catch(IOException ex)
            {
                System.err.println(ex.getMessage());
            }
        });
         
    }
    
    
     private boolean controleDeSaisi() {

        if (nomRestau.getText().isEmpty() || addressRestau.getText().isEmpty() || mailRestau.getText().isEmpty()|| telRestau.getText().isEmpty()
               ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[A-Z a-z 0-9]*+@+[gmail yahoo hotmail esprit live]+.+[com tn fr org en]", mailRestau.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le mail ! ");
                mailRestau.requestFocus();
                mailRestau.selectEnd();
                return false;
            }
            
             if (!Pattern.matches("[0-9]", telRestau.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le telephone ! ");
                telRestau.requestFocus();
                telRestau.selectEnd();
                return false;
            }
        }

        return true;
    }

    public void initChamps() {
        telRestau.clear();

    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    
  
}
