/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.dropbox.core.DbxException;
import com.jfoenix.controls.JFXComboBox;
import dao.Services.CrudTableService;
import dao.Services.CrudTableSociete;
import entity.Services.Service;
import entity.Services.Societe;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class AjoutSocieteController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    
    FileChooser fc=new FileChooser();
    File selectedFile=new File("");
    
  
    @FXML
    private void image(ActionEvent event) throws FileNotFoundException, IOException {
        File dest=new File("C:\\wamp64\\www\\ProjectPidev_Finalou\\ProjectPidev\\web\\image1");
        //Bureau
        fc.setInitialDirectory(new File("C:\\Users\\Pirateos\\Desktop"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);
         
        soc.setImage(selectedFile.getName());
        
    }
   
    /*
    @FXML
    private void image(ActionEvent event) throws FileNotFoundException, IOException, DbxException {
        dropBox conf = new dropBox();
      
        fc.setInitialDirectory(new File("C:\\Users\\Pirateos\\Desktop"));
        selectedFile = fc.showOpenDialog(null);
        soc.setImage(conf.uploadImage(selectedFile));
        
    }*/
    
    
    
    
    @FXML
    private TextField tel;
    @FXML
    private ImageView back;
    
      @FXML
    private JFXComboBox<String> NomService;
    
    CrudTableService crudtableService;
    ObservableList data ;
    
    Societe soc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          soc = new Societe();
        
       crudtableService = new CrudTableService();
      
       List<Service> listeSer = crudtableService.getAll();
       
       data = FXCollections.observableArrayList(); 
       

           
            for(Service s:listeSer)
                data.add(s.getDescription());
            
            //////
                NomService.setItems(data);
            
       
       
                
        
        
          back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent SocieteParent = null;
         try {
             SocieteParent = FXMLLoader.load(getClass().getResource("/view/Services/GetAllSociete.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
      Scene SocieteScene = new Scene (SocieteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(SocieteScene);
       window.show();  
     });
        

          
          
          
          
           btn.setOnMouseClicked(e -> {
          if(nom.getText().trim().isEmpty()||adresse.getText().trim().isEmpty()
         ){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("veuillez saisir les don!");
        alert.show();
          }else {
         CrudTableSociete ss = new CrudTableSociete();
            
          
            soc.setNom(nom.getText());
            soc.setTel(tel.getText());
            soc.setAdress( adresse.getText() );
           
      soc.setService(crudtableService.rechercherServiceByDescription((String)NomService.getValue()));

            
            ss.add(soc);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Societe insérée avec succés!");
        alert.show();
        nom.setText("");
        tel.setText("");
        adresse.setText("");
        
           
          }
            
        });
       
        }
              
    
        
        
        
    }



    
    
