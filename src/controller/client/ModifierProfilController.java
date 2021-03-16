/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;


import dao.UserDao;
import entity.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class ModifierProfilController implements Initializable {

    @FXML
    private TextField nomTxt;
    @FXML
    private TextField prenomTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField adresseTxt;
    @FXML
    private Button btnModifier;
    
    
    private User selectedUser;
    
    
    public void initDataUser(User user){
            selectedUser=user;
            nomTxt.setText(user.getNomUser());
            prenomTxt.setText(user.getPrenomUser());
            adresseTxt.setText(user.getAdressUser());
            emailTxt.setText(user.getEmailUser());
            usernameTxt.setText(user.getUserName());
        
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        btnModifier.setOnAction(e -> {

            try {
                
            UserDao userdao = new UserDao();
            
   
            User user = new User(selectedUser.getIdUser(),nomTxt.getText(),
            prenomTxt.getText(),adresseTxt.getText(),emailTxt.getText(),usernameTxt.getText(),
                    usernameTxt.getText(),emailTxt.getText());
            
           
            
                System.out.println(userdao.update(user));            
            
                
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/client/Profil.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
  
            } catch (IOException ex) {
                Logger.getLogger(ModifierProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
