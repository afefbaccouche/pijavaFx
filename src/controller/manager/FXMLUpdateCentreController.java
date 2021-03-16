/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import Admin.home.MenuAdminController;
import dao.CentreDao;
import entity.Centre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.FileUpload;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLUpdateCentreController implements Initializable {

    public static Centre selectedCentre;

    @FXML
    private Label Nom;
    @FXML
    private TextField nomCentre;
    @FXML
    private TextField descriptionCentre;

    @FXML
    private TextField Adresse;
    @FXML
    private TextField Telephone;
    @FXML
    private TextField NombrePlace;
    @FXML
    private ImageView pic;

    public File afef = null;
    @FXML
    private TextField prixCentre;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().addAll(new MenuAdminController());
        // TODO
        System.out.println("selected centre " + selectedCentre);
        System.out.println(selectedCentre.getId());
        nomCentre.setText(selectedCentre.getNom());
        descriptionCentre.setText(selectedCentre.getDescription());

        Adresse.setText(selectedCentre.getAddress());
        Telephone.setText(Integer.toString(selectedCentre.getTelephone()));
        NombrePlace.setText(Integer.toString(selectedCentre.getNbrPlace()));
        prixCentre.setText(Float.toString(selectedCentre.getPrix()));

        try {
            pic.setImage(new Image(selectedCentre.getImage()));
        } catch (Exception e) {

        }
    }

    @FXML
    private void update(ActionEvent event) {

        if (!checkTelephoneFormat()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Telephone doit etre un nombre et 8 chiffre!");
            alert.show();
            return;
        }

        try {
            Float.parseFloat(prixCentre.getText());
            Integer.parseInt(NombrePlace.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("price must be float & nbr place must be number!");
            alert.show();
            return;
        }

        if (afef != null) {
            String imageName = getRandomString();
            FileUpload.executeMultiPartRequest("http://localhost/desktop/uploadImage.php", afef, imageName, imageName);
            selectedCentre.setImage("http://localhost/desktop/uploads/" + imageName + ".jpg");
        }

        CentreDao adao = CentreDao.getInstance();
        selectedCentre.setNom(nomCentre.getText());
        selectedCentre.setDescription(descriptionCentre.getText());
        selectedCentre.setTelephone(Integer.parseInt(Telephone.getText()));
        selectedCentre.setNbrPlace(Integer.parseInt(NombrePlace.getText()));
        selectedCentre.setAddress(Adresse.getText());
        selectedCentre.setPrix(Float.parseFloat(prixCentre.getText()));

        boolean isUpdated = adao.update(selectedCentre);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Centre modifier avec succés!");
            alert.show();
            nomCentre.setText("");
            descriptionCentre.setText("");
            NombrePlace.setText("");
            Adresse.setText("");
            Telephone.setText("");
            goBackToTable(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("erreur!");
            alert.show();
        }
    }


    private void goBackToTable(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLCentre.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateCentreController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void openFile(ActionEvent event) throws IOException {
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

            pic.setImage(image);
        }
    }

    private boolean checkTelephoneFormat() {
        String tel = Telephone.getText();
        try {
            Integer.parseInt(tel);
        } catch (Exception e) {
            return false;
        }

        if (tel.isEmpty()) {
            return false;
        }
        return tel.length() == 8;
    }

    @FXML
    private void goBack2(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/manager/FXMLCentre.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
