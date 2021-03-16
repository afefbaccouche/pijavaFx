/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import Admin.home.MenuAdminController;
import com.jfoenix.controls.JFXTextField;

import dao.CentreDao;
import entity.Centre;
import entity.Upload;
import entity.User;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.util.Collections;
import java.util.Random;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import session.Session;
import utils.FileUpload;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLAjouterCentrelController implements Initializable {

    @FXML
    private TextField nomCentre;
    @FXML
    private TextField descriptionCentre;

    private TextArea imagpath;

    public File afef;

    @FXML
    private ImageView pic;

    @FXML
    private TextField prixCentre;
    @FXML
    private TextField nbrPlace;
    @FXML
    private TextField Adresse;
    @FXML
    private TextField Telephone;

    private File imageCentre = null;
    @FXML
    private AnchorPane root;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().addAll(new MenuAdminController());
        // TODO/**image **/
        /*  File dir = new File("C:/wamp64/www/PIDEV/web/devis/profilePic.png");
       BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(dir);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
              imageIn.setFill(new ImagePattern(image));
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        /**
         * end image*
         */

        ObservableList<String> styleClass = prixCentre.getStyleClass();
        if (prixCentre.getText().trim().length() == 0) {
            if (!styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.removeAll(Collections.singleton("error"));
        }

    }

    @FXML
    private void add(ActionEvent event) {

        if (imageCentre == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Choose image!");
            alert.show();
            return;
        }
        
        if(!checkTelephoneFormat()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Telephone doit etre un numero et 8 chiffre!");
            alert.show();
            return;
        }

        String imageName = getRandomString();
        FileUpload.executeMultiPartRequest("http://localhost/desktop/uploadImage.php", imageCentre, imageName, imageName);

        User connectedUser = Session.getConnectedUser();
        Centre c = new Centre(nomCentre.getText(), descriptionCentre.getText(), "http://localhost/desktop/uploads/" + imageName + ".jpg", connectedUser);
        c.setAddress(Adresse.getText());
        c.setTelephone(Integer.parseInt(Telephone.getText()));
        try {
            c.setPrix(Float.parseFloat(prixCentre.getText()));
            c.setNbrPlace(Integer.parseInt(nbrPlace.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("price must be float & nbr place must be number!");
            alert.show();
            return;
        }
        CentreDao cdao = CentreDao.getInstance();
        boolean isAdded = cdao.add(c);
        System.out.println(c);
        System.out.println(Session.getConnectedUser().getIdUser());

        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Centre insérée avec succés!");
            alert.show();
            nomCentre.setText("");
            descriptionCentre.setText("");
            prixCentre.setText("");
            Adresse.setText("");
            Telephone.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("erreur!");
            alert.show();
        }
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

    /* @FXML
    private void openFIle(ActionEvent event) throws IOException {
         
         
        /*FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open file dialog");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        afef = filechooser.showOpenDialog(stage);
       if ( afef !=null)
       {
           Desktop desktop= Desktop.getDesktop();
           desktop.open(afef);
           imagpath.setText(afef.getAbsolutePath());
          Image image = new Image(afef.toURI().toString(),100,150,true, true);
          //imageView=new imageView(image);
       }*/
 /* FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
           
           Upload u = new Upload();
           u.upload(selectedFile);
           imageBP1.setText(selectedFile.getName());
            
                 Image image = new Image("file:///C://wamp64//www//madame//web//images//"+selectedFile.getName());

                       myImageView.setImage(image); 
     
       }*/
 /*    FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(selectedFile);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageIn.setFill(new ImagePattern(image));
        pic.setImage(image);

    }*/
 /*public static String saveToFile(Image image) {

        String ext = "jpg";
        File dir = new File("C:/wamp64/www/PIDEV/web/devis");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }*/
    @FXML
    private void openFIle(ActionEvent event) throws IOException {
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
            imageCentre = afef;

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

    
}
