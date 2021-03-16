
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ConnectionBaseDonn.connect;
import com.jfoenix.controls.JFXButton;
import dao.LoginDao;
import entity.Roles;
import entity.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import session.Session;
import utils.Navigation;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class SignUpUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtEmail;

    @FXML

    private TextField txtNom;

    @FXML
    private TextField txtPassword;

    private Connection connection;
    private Statement ste;

    private PreparedStatement pst;

    private ResultSet rs;
    @FXML
    private Button signUp;

    @FXML
    public void SignUP(MouseEvent event) {
        LoginDao dao = LoginDao.getInstance();
        User user = new User();
        user.setNomUser(txtNom.getText());
        user.setPrenomUser(txtPrenom.getText());
        user.setAdressUser(txtAddress.getText());
        user.setNumUser(txtNum.getText());
        user.setEmailUser(txtEmail.getText());
        user.setPasswordUser(txtPassword.getText());
        boolean isSignedUp = dao.signUp(user, Roles.Client.toString(), true);
        if (isSignedUp) {
            try {
                user.setRole(Roles.Client.toString());
                Session.setConnectedUser(user);

                String homePagePath = Navigation.getHomePagePath(user.getRole());

                Parent root = FXMLLoader.load(getClass().getResource(homePagePath));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                /*
                 txtNom.clear();
                txtPrenom.clear();
                txtAddress.clear();
                txtNum.clear();
                txtEmail.clear();
                txtPassword.clear();
                 */
            } catch (IOException ex) {
                Logger.getLogger(SignUpUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Number(javafx.scene.input.KeyEvent event) {
        /*
        char c = event.getKeyChar();

        if (!(Character.isDigit(c))) {
            // event.consume();
            System.out.println("falt");
        }
         */
    }

    @FXML
    private void goToLogin(javafx.event.ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLLogin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void docteurManager(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLSignUpDoctorManager.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
