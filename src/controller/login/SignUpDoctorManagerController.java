/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import dao.LoginDao;
import entity.Roles;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import session.Session;
import utils.FileUpload;
import utils.Navigation;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class SignUpDoctorManagerController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private Button signUp;
    @FXML
    private ChoiceBox<String> chooseRole;
    @FXML
    private ImageView pic;

    public File afef = null;

    private boolean checkCorrectPassword() {
        String pass = txtPassword.getText();
        String confPass = txtConfirmPassword.getText();
        if (pass.equals(confPass)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("doctoo");
        ObservableList<String> options = FXCollections.observableArrayList(Roles.Doctor.toString(), Roles.Manager.toString());

        chooseRole.setValue(Roles.Doctor.toString()); // this statement shows default value 

        chooseRole.setItems(options); // this statement adds all values in choiceBox
    }

    @FXML
    private void SignUP(MouseEvent event) {
        boolean isPassCorrect = checkCorrectPassword();
        if (isPassCorrect) {
            if (afef == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Choose file!");
                alert.show();
                return;
            }

            String imageName = getRandomString();
            FileUpload.executeMultiPartRequest("http://localhost/desktop/uploadImage.php", afef, imageName, imageName);

            LoginDao dao = LoginDao.getInstance();
            User user = new User();
            user.setNomUser(txtNom.getText());
            user.setPrenomUser(txtPrenom.getText());
            user.setAdressUser("");
            user.setNumUser("");
            user.setEmailUser(txtEmail.getText());
            user.setPasswordUser(txtPassword.getText());
            user.setPieceJustificatif("http://localhost/desktop/uploads/" + imageName + ".jpg");
            boolean isSignedUp = dao.signUp(user, chooseRole.getValue(), false);
            if (isSignedUp) {
                try {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("votre demande de creer un compte est en train d'etre traiter!");
                    alert.show();
                    



                    Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLLogin.fxml"));
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
        } else {

        }
    }

    @FXML
    private void goToLogin(ActionEvent event) {
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
    private void openFile(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open file dialog");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        afef = filechooser.showOpenDialog(stage);
        if (afef != null) {
            /*
            Desktop desktop = Desktop.getDesktop();
            desktop.open(afef);
             */
            //imagpath.setText(afef.getAbsolutePath());
            Image image = new Image(afef.toURI().toString(), 100, 150, true, true);
            //imageView=new imageView(image);

            pic.setImage(image);
        }
    }

    private String getRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

}
