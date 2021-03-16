/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.doctor;

import Admin.home.MenuAdminController;
import controller.manager.FXMLAjouterCentrelController;
import dao.LikeArticleDao;
import entity.LikeArticle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class StatArticlesController implements Initializable {

    @FXML
    private BarChart<String, Number> chart;

    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         container.getChildren().addAll(new MenuAdminController());
        chart.setTitle("Les Meilleurs Articles");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Article");
        yAxis.setLabel("likes");

      

        ArrayList<LikeArticle> list = LikeArticleDao.getInstance().getTopArticles();
        System.out.println("list: " + list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Article: " + list.get(i).getArticle().getTitle());
            System.out.println("nbLikes: " + list.get(i).getNbLikesPerArticle());

            XYChart.Series serie = new XYChart.Series();
            serie.setName(list.get(i).getArticle().getTitle());
            serie.getData().add(new XYChart.Data(list.get(i).getArticle().getTitle(), list.get(i).getNbLikesPerArticle()));
            Data data = new XYChart.Data(list.get(i).getArticle().getTitle(), list.get(i).getNbLikesPerArticle());
            chart.getData().add(serie);
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
            Logger.getLogger(FXMLAjouterCentrelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
