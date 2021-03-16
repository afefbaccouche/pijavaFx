/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Restaurant;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

import ConnectionBaseDonn.connect;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Consommation.RestaurentServices;
import entity.Consommation.Restaurants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.apache.commons.io.FileUtils;


/**
 *
 * @author soumaya ch
 */
public class AjoutRestaurantController implements Initializable{

     private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
     
     
     
        @FXML
    private ComboBox<String> typeRestau;
    ////// ajout
  @FXML
    private TextField  txtTelR;

    @FXML
    private TextField  txtNomR;

    @FXML
    private TextField  txtAddressR;

    @FXML
    private TextField  txtEmailR;
    
    
    @FXML
    private Button btnAjoutR;
    
        @FXML
    private Button btnsaveImage;
        
        
    @FXML
    private ImageView imageViewR;
     ////////////////////////////////////////////////////////
    
    JFileChooser  chosser;
    File file;
     String locat,format;
    ////// affiche
   
  
/////////////////////////////////////////////////////////////
       ObservableList data ;
     
    public AjoutRestaurantController(){
    
       connection =connect.getInstance().getCnx(); 
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
      //  MagasinServices magS= new MagasinServices();
 
 
       
       data = FXCollections.observableArrayList("Restaurant","Café","Restau-café"); 
     
  
        typeRestau.setItems(data);
        
        ////// ajout restaurant
  btnAjoutR.setOnAction(e->{
      
      if (!controleDeSaisi()) {
            if (txtNomR.getText().isEmpty()) {
                txtNomR.setText("");
            }
            if (txtAddressR.getText().isEmpty()) {
                txtAddressR.setText("");
            }

            if (txtTelR.getText().isEmpty()) {
                txtTelR.setText("");
            }
            if (txtEmailR.getText().isEmpty()) {
                txtEmailR.setText("");
            }
      
  } else {
       String imageData="";
       
       try {
      
         
           
           BufferedImage imag = ImageIO.read(file);
          imag = ImageIO.read(file);
   
         imageData=file.getName();
           
        locat =System.getProperty("user.dir")+"/src/pidev/"+imageData;
                    locat=locat.replace("\\", "/");
         format=file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
      
         ImageIO.write(imag, format, new File(locat));
         
      } catch (IOException ex) {
          Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
      }
       
       
       
       
       
       
      RestaurentServices ResI = RestaurentServices.getInstance();
      Restaurants Res = new Restaurants();
      Res.setNomRestaurant(txtNomR.getText());
      Res.setAdressRestaurant(txtAddressR.getText());
      Res.setNumRestaurant(txtTelR.getText());
      Res.setEmailRestaurant(txtEmailR.getText());
      Res.setImagRestau(imageData);
      Res.setType(typeRestau.getValue());
      ResI.add(Res);
      
      
        
  
      

      
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("restaurant insérée avec succés!");
      alert.show();
      txtNomR.setText("");
      txtAddressR.setText("");
      txtEmailR.setText("");
      txtTelR.setText("");
      
      }
        });
    
         /////////////////////////////////////////////////////////////////
      

          
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

        if (txtNomR.getText().isEmpty() || txtAddressR.getText().isEmpty() || txtEmailR.getText().isEmpty()|| txtTelR.getText().isEmpty()
               ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[A-Z a-z 0-9]*+@+[gmail yahoo hotmail esprit live]+.+[com tn fr org en]", txtEmailR.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le mail ! ");
                txtEmailR.requestFocus();
                txtEmailR.selectEnd();
                return false;
            }
           
        }

        return true;
    }

    public void initChamps() {
        txtEmailR.clear();

    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

}
