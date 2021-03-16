/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Produit;

import com.jfoenix.controls.JFXCheckBox;
import controller.Consommation.Admin.Restaurant.AjoutRestaurantController;
import dao.Consommation.ListDataMagasin;
import dao.Consommation.MagasinServices;
import dao.Consommation.ProduitServices;
import entity.Consommation.Magasins;
import entity.Consommation.Produit;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField txtNomP;

    @FXML
    private TextField txtPrixP;

    @FXML
    private TextField txtQuantP;

    @FXML
    private ComboBox<String> magP;

    @FXML
    private ImageView imageViewR;

    @FXML
    private Button btnsaveImage;

    @FXML
    private JFXCheckBox food;

    @FXML
    private JFXCheckBox clothes;

    @FXML
    private JFXCheckBox Sport;

    @FXML
    private JFXCheckBox electronique;

    @FXML
    private CheckBox healthy;

    @FXML
    private CheckBox normal;

    @FXML
    private Button btnAjoutP;
        @FXML
    private VBox VBoxFood;
    
       ObservableList data ;
       
        @FXML
    private ImageView imageViewP;
     ////////////////////////////////////////////////////////
    
    JFileChooser  chosser;
    File file;
     String locat,format;
                
        private String type="";
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        food.setOnMouseClicked(e->{
            if(food.isSelected())
            {
                type="food";
                VBoxFood.setVisible(true);
            }
            else
            {
                  VBoxFood.setVisible(false);
            }
        });

         clothes.setOnMouseClicked(e->{
        if(clothes.isSelected())
            {
                type="clothes";
                
            }
        });
         
         Sport.setOnMouseClicked(e->{
        if(Sport.isSelected())
            {
                type="Sport";
             
            }
        });
         
          electronique.setOnMouseClicked(e->{
        if(electronique.isSelected())
            {
                type="electronique";
             
            }
        
           });
        
//  ProduitServices ProduitI = ProduitServices.getInstance();

 MagasinServices magS= new MagasinServices();
 
List<Magasins> listeSer = magS.getAll();
       
       data = FXCollections.observableArrayList(); 
     
        
    for(Magasins s:listeSer)
    {
          data.add(s.getNomMagasin());
            
            //////
   
     
       
    }
              
            magP.setItems(data);          
           
     
     
     
         
        btnAjoutP.setOnAction(e->{
            
             if (!controleDeSaisi()) {
            if (txtNomP.getText().isEmpty()) {
                txtNomP.setText("");
            }
            if (txtPrixP.getText().isEmpty()) {
                txtPrixP.setText("");
            }

            if (txtQuantP.getText().isEmpty()) {
                txtQuantP.setText("");
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
          Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
      }
       
            
            ProduitServices ProduitI = ProduitServices.getInstance();
      Produit produit = new Produit();
      
      produit.setNomProduit(txtNomP.getText());
      produit.setPrixProduit(Float.parseFloat(txtPrixP.getText()));
      produit.setQuantiteProduit(Integer.parseInt(txtQuantP.getText()));
      produit.setMagProduit(magS.rechercherMagasinByNom((String)magP.getValue()));
      produit.setTypeProduit(type);
    produit.setImageProduit(imageData);
      ProduitI.add(produit);
      
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("restaurant insérée avec succés!");
      alert.show();
      txtNomP.setText("");
      txtPrixP.setText("");
      txtQuantP.setText("");
    
             }
//      
//      
       
             
        });
        
        
           
        btnsaveImage.setOnAction(e->{
            
            try{
                
                
          chosser=new JFileChooser();
   //  chosser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png", "*.jpg", "*.jpeg"));
            chosser.showOpenDialog(null);
            file=chosser.getSelectedFile();
       
            BufferedImage Bimg=ImageIO.read(file);
            
            Image img=SwingFXUtils.toFXImage(Bimg, null);
            
            imageViewP.setImage(img);
            }
            catch(IOException ex)
            {
                System.err.println(ex.getMessage());
            }
        });
        
    }   
                
                 private boolean controleDeSaisi() {

        if (txtNomP.getText().isEmpty() || txtPrixP.getText().isEmpty() || txtQuantP.getText().isEmpty()
               ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
//        } else {

//            if (!Pattern.matches("[0-100]", txtPrixP.getText())) {
//                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le prix ! ");
//                txtPrixP.requestFocus();
//                txtPrixP.selectEnd();
//                return false;
//            }
            
//             if (!Pattern.matches("[0-100]", txtQuantP.getText())) {
//                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la quantite ! ");
//                txtQuantP.requestFocus();
//                txtQuantP.selectEnd();
//                return false;
//            }
        }

        return true;
    }

    public void initChamps() {
        txtPrixP.clear();

    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
}
