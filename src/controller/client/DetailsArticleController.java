/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import controller.manager.FXMLAjouterCentrelController;
import dao.LikeArticleDao;
import entity.Article;
import entity.LikeArticle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class DetailsArticleController implements Initializable {

    public static Article selectedArticle = null;
    @FXML
    private Label titreArticle;
    @FXML
    private Label dateArticle;
    @FXML
    private Label descArticle;
    @FXML
    private Label bodyArticle;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView likeImg;

    private boolean isLiked = false;
    @FXML
    private ImageView imgArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titreArticle.setText(selectedArticle.getTitle());
        descArticle.setText(selectedArticle.getDescription());
        bodyArticle.setText(selectedArticle.getBody());

        dateArticle.setText(selectedArticle.getCreatedAt().toString());
        
        
        try {
            imgArticle.setImage(new Image(selectedArticle.getImage()));
        } catch (Exception e) {

        }

        titreArticle.setWrapText(true);
        descArticle.setWrapText(true);
        bodyArticle.setWrapText(true);

        ArrayList<LikeArticle> listLikes = LikeArticleDao.getInstance().getByUserArticle(Session.getConnectedUser().getIdUser(), selectedArticle.getId());
        if (listLikes.isEmpty()) {
            Image image = new Image("Admin/images/not_liked.png");
            likeImg.setImage(image);
        } else {
            Image image = new Image("Admin/images/liked.png");
            likeImg.setImage(image);
            isLiked = true;
        }
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/client/FXMLListArticle.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void likeArticle(MouseEvent event) {
        if (!isLiked) {
            Boolean isAdded = LikeArticleDao.getInstance().add(Session.getConnectedUser().getIdUser(), selectedArticle.getId());
            if (isAdded) {
                Image image = new Image("Admin/images/liked.png");
                likeImg.setImage(image);
                isLiked = true;
            } else {

            }
        }
    }

}
