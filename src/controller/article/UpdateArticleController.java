/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.article;

import Admin.home.MenuAdminController;
import dao.ArticleDao;
import entity.Article;
import entity.User;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import session.Session;
import utils.FileUpload;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class UpdateArticleController implements Initializable {

    public static Article selectedArticle;

    @FXML
    private TextField titreArticle;
    @FXML
    private TextArea descriptionArticle;
    @FXML
    private TextArea corpsArticle;

    @FXML
    private Label Titre;
    @FXML
    private ImageView pic;

    public File afef = null;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         container.getChildren().addAll(new MenuAdminController());
        // TODO
        System.out.println("selected article " + selectedArticle);
        System.out.println(selectedArticle.getId());
        titreArticle.setText(selectedArticle.getTitle());
        descriptionArticle.setText(selectedArticle.getDescription());
        corpsArticle.setText(selectedArticle.getBody());

        try {
            pic.setImage(new Image(selectedArticle.getImage()));
        } catch (Exception e) {

        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (afef != null) {
            String imageName = getRandomString();
            FileUpload.executeMultiPartRequest("http://localhost/desktop/uploadImage.php", afef, imageName, imageName);
            selectedArticle.setImage("http://localhost/desktop/uploads/" + imageName + ".jpg");
        }

        ArticleDao adao = ArticleDao.getInstance();
        selectedArticle.setTitle(titreArticle.getText());
        selectedArticle.setDescription(descriptionArticle.getText());
        selectedArticle.setBody(corpsArticle.getText());
        boolean isUpdated = adao.update(selectedArticle);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Article modifier avec succés!");
            alert.show();
            titreArticle.setText("");
            descriptionArticle.setText("");
            corpsArticle.setText("");
            goBackToTable(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("erreur!");
            alert.show();
        }

    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLUpdateDelArticle.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void goBackToTable(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLUpdateDelArticle.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
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
