/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Admin.Magasin;

import dao.Consommation.MagasinServices;
import entity.Consommation.Magasins;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class AjoutMagasinController implements Initializable {

   
    @FXML
    private TextField txtNomM;

    @FXML
    private TextField txtAddressM;

    @FXML
    private TextField txtTelM;

    @FXML
    private TextField txtEmailM;
    
      @FXML
    private Button btnAjoutM;
      
        @FXML
    private Button btnsaveImage;
      
      @FXML
    private ImageView imageViewM;
     ////////////////////////////////////////////////////////
    
    JFileChooser  chosser;
    File file;
     String locat,format;
    ////// affiche
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnAjoutM.setOnAction(e->{
            
             if (!controleDeSaisi()) {
            if (txtNomM.getText().isEmpty()) {
                txtNomM.setText("");
            }
            if (txtAddressM.getText().isEmpty()) {
                txtAddressM.setText("");
            }

            if (txtTelM.getText().isEmpty()) {
                txtTelM.setText("");
            }
            if (txtEmailM.getText().isEmpty()) {
                txtEmailM.setText("");
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
          Logger.getLogger(AjoutMagasinController.class.getName()).log(Level.SEVERE, null, ex);
      }
                 
            
            MagasinServices ResI = MagasinServices.getInstance();
      Magasins Res = new Magasins();
      
      Res.setNomMagasin(txtNomM.getText());
      Res.setAdressMagasin(txtAddressM.getText());
      Res.setNumMagasin(txtTelM.getText());
      Res.setEmailMagasin(txtEmailM.getText());
    Res.setImageMagasin(imageData);
      ResI.add(Res);
      
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("restaurant insérée avec succés!");
      alert.show();
      txtNomM.setText("");
      txtAddressM.setText("");
      txtTelM.setText("");
      txtEmailM.setText("");
             }
//      
//       try{
//          
//      
//       FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/view/Consommation/Admin/Magasin/AfficheMagasin.fxml"));
//                Parent tableViewParent= loader.load();
//                Scene scene = new Scene(tableViewParent);
//                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//      } catch (IOException ex) {
//                Logger.getLogger(UpdateMagasinController.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
            
            imageViewM.setImage(img);
            }
            catch(IOException ex)
            {
                System.err.println(ex.getMessage());
            }
        });
        
    } 
    
     private boolean controleDeSaisi() {

        if (txtNomM.getText().isEmpty() || txtAddressM.getText().isEmpty() || txtEmailM.getText().isEmpty()|| txtTelM.getText().isEmpty()
               ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[A-Z a-z 0-9]*+@+[gmail yahoo hotmail esprit live]+.+[com tn fr org en]", txtEmailM.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le mail ! ");
                txtEmailM.requestFocus();
                txtEmailM.selectEnd();
                return false;
            }
            
           
        }

        return true;
    }

    public void initChamps() {
        txtEmailM.clear();

    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
}
