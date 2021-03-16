/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import com.jfoenix.controls.JFXTextField;
import dao.LoginDao;
import entity.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import session.Session;
import utils.Navigation;

/**
 * FXML Controller class
 *
 * @author Pirateos
 */
public class LoginController implements Initializable {

    private Connection connection = ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private ResultSet rs;

    private User user = null;
    @FXML
    private TextField username_box;
    @FXML
    private PasswordField password_box;
    @FXML
    private Button btnSignin;
    @FXML
    private Label btnForgot;
    @FXML
    private Button btnFB;
    @FXML
    private Button SignUp;
    @FXML
    private Label invalid_label;
    @FXML
    private ImageView old;
    @FXML
    private JFXTextField numtel;

    /**
     * Initializes the controller class.
     */
      @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
       if (isValidCredentials()) {
            if (!user.isEnabled()) {
                invalid_label.setText("Sorry, you are not approuved yet");
                return;
            }
            Session.setConnectedUser(user);

            String homePagePath = Navigation.getHomePagePath(user.getRole());
            //String homePagePath = "/view/article/FXMLTest.fxml";

            Parent root = FXMLLoader.load(getClass().getResource(homePagePath));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            //stage.setFullScreen(true);
            stage.show();

        } else {
            username_box.clear();
            password_box.clear();
            invalid_label.setText("Sorry, invalid credentials");
        }

    }

    private boolean isValidCredentials() {
      
      boolean letIn = false;
        user = LoginDao.getInstance().login(username_box.getText(), password_box.getText());

        if (user != null) {
            letIn = true;
        }
        return letIn;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        old.setVisible(true);
        numtel.setVisible(false);
        SignUp.setOnAction(ev -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLSignUpUser.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void forget(MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mot de passe oublié ?");
        dialog.setHeaderText("Veuillez saisir votre numero de telephone");
        dialog.setContentText("Saissizer votre numero:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (isPhoneNumber(result.get()) == 0) {
                Alert alert = getAlert("ERREUR", "Attention! Numero Invalide!");
                alert.showAndWait();
            } else {
                 User U= new User();
                LoginDao us = new LoginDao();
               int i = Integer.parseInt(result.get());
                if (!us.findbynum(i)) {
                    Alert alert = getAlert("ERREUR", "Attention! Utilisateur n'existe pas!");
                    alert.showAndWait();
                } else {
            
            
       
                  Twilio.init("ACd15db1db907ababc75a163d7b8ee01be","0e5b93febe9693bd402de7a1995b7cdb");
System.out.println(us.findbypwd(i));
                    Message message = Message
                            .creator(new PhoneNumber("+21622326677"), new PhoneNumber("+13345390874"),
                                    "Votre mot de passe = " + us.findbypwd(i))
                            .create();

                   System.out.println(message.getSid());
                   
                    
                    getAlert("SUCCES", "Votre mot de passe a été envoyé à " + i).showAndWait();
    
    }
                
    }} 
    }
     public static int isPhoneNumber(String ch) {
        int i = 0;
        if (ch.length() == 8) {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }
        public Alert getAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }
    

}
