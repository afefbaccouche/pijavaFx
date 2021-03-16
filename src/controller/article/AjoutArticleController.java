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
import javafx.event.EventHandler;
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

public class AjoutArticleController implements Initializable {

    @FXML
    private Label Titre;

    @FXML
    private TextField titreArticle;

    @FXML
    private TextArea descriptionArticle;

    @FXML
    private TextArea corpsArticle;

    private File imageArticle = null;

    private Button BtnAjouter;
    @FXML
    private Button ajouter;

    public File afef;

    @FXML
    private ImageView pic;
    @FXML
    private AnchorPane containerPA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        containerPA.getChildren().addAll(new MenuAdminController());
        // TODO     

    }

    @FXML
    private void add(ActionEvent event) {
        User connectedUser = Session.getConnectedUser();
        String imageName = getRandomString();
        FileUpload.executeMultiPartRequest("http://localhost/desktop/uploadImage.php", imageArticle, imageName, imageName);

        Article a = new Article(titreArticle.getText(), descriptionArticle.getText(), corpsArticle.getText(), "http://localhost/desktop/uploads/" + imageName + ".jpg", connectedUser.getIdUser());
        ArticleDao adao = ArticleDao.getInstance();
        boolean isAdded = adao.add(a);

        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Personne insérée avec succés!");
            alert.show();
            titreArticle.setText("");
            descriptionArticle.setText("");
            corpsArticle.setText("");
            //imageArticle.setText("");
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
            imageArticle = afef;

            pic.setImage(image);
        }
    }

}
