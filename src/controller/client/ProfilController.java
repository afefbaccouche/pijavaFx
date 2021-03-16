/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;



import com.jfoenix.controls.JFXButton;
import dao.UserDao;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class ProfilController implements Initializable {

    @FXML
    private Label nameTxt;
    @FXML
    private Label userNameTxt;
    @FXML
    private Label emailTxt;
    @FXML
    private Label adresseTxt;
    @FXML
    private Button btnModifier;
    @FXML
    private JFXButton btnwish;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = new User();
        UserDao userdao = new UserDao();
        user=userdao.displayById(Session.getConnectedUser().getIdUser());
        System.out.println(user.toString());
        nameTxt.setText(user.getPrenomUser() + " "+user.getNomUser());
        userNameTxt.setText(user.getUserName());
        emailTxt.setText(user.getEmailUser());
        adresseTxt.setText(user.getAdressUser());
        
        
        btnModifier.setOnAction(e -> {
            
            
            try {
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/client/ModifierProfil.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                ModifierProfilController controller = loader.getController();
                controller.initDataUser(userdao.displayById(Session.getConnectedUser().getIdUser()));
               
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        });
    }    

    @FXML
    private void wishlist(ActionEvent event) {
         try {
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Loisir/FXML_wish.fxml"));
                btnwish.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
