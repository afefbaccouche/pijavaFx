/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.maladie;

import Admin.home.MenuAdminController;
import controller.article.FXMLUpdateDelArticleController;
import dao.MaladieDao;
import dao.QuestionDao;
import entity.Maladie;
import entity.Question;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AjoutQuestionController implements Initializable {

    @FXML
    private TextArea question;

    public static String nomMaladie = "";
    public static ArrayList<String> listQuestions = new ArrayList();
    @FXML
    private Label labelQuestion;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getChildren().addAll(new MenuAdminController());
        // TODO
        int index = listQuestions.size() + 1;
        String label = "Question: " + index;
        labelQuestion.setText(label);
    }

    @FXML
    private void goBack(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLAjoutMaladie.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void next(ActionEvent event) {
        String questionText = question.getText();
        if (questionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller entrer une question!");
            alert.show();
        } else {
            try {
                listQuestions.add(questionText);

                Parent root = FXMLLoader.load(getClass().getResource("/view/maladie/FXMLAjoutQuestion.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void finish(ActionEvent event) {
        String questionText = question.getText();
        if (questionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller entrer une question!");
            alert.show();
        } else {
            listQuestions.add(questionText);

            int idMaladieGenerated = MaladieDao.getInstance().add(new Maladie(nomMaladie, Session.getConnectedUser()));
            if (idMaladieGenerated == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("error maladie!");
                alert.show();
            } else {
                boolean isAllAdded = true;

                QuestionDao qDao = QuestionDao.getInstance();
                for (int i = 0; i < listQuestions.size(); i++) {
                    Maladie addedMaladie = MaladieDao.getInstance().findById(idMaladieGenerated);
                    boolean isQuestionAdded = qDao.add(new Question(listQuestions.get(i), Session.getConnectedUser(), addedMaladie));
                    if (!isQuestionAdded) {
                        isAllAdded = false;
                    }
                }

                if (isAllAdded) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Questionss insérée avec succés!");
                    alert.show();

                    try {

                        Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLMaladies.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("erreur!");
                    alert.show();
                }
            }

        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLMaladies.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
