/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvenementDao;
import entity.Evenement;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import javafx.util.Callback;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AccueilEventController implements Initializable {

    @FXML
    private GridPane gridEvents;
    @FXML
    private AnchorPane container;

    
    @FXML
    private TextField chercherTxt;
    @FXML
    private Button chercherBtn;
    
    
    EvenementDao edao= EvenementDao.getInstance();
    Image image;
    GridPane item;
    @FXML
    private Button allBtn;

    public GridPane initItem(Evenement ev) {
        GridPane gridItem = new GridPane();

        //image= new Image("/view/events/images/event1.jpg");
        image = new Image("file:C:/wamp64/www/imageUploads/" + ev.getImageProperty().get());
        ImageView imageview1 = new ImageView();
        imageview1.setImage(image);
        imageview1.setFitWidth(300);
        imageview1.setFitHeight(150);

        Label nom = new Label(ev.getNomEvent());
        nom.setFont(Font.font("Cambria", 28));
        nom.setMaxWidth(Double.MAX_VALUE);
        nom.setAlignment(Pos.CENTER);

        Label date = new Label(ev.getDateDebut() + " - " + ev.getDateFin());
        date.setFont(Font.font("Cambria", 18));
        date.setMaxWidth(Double.MAX_VALUE);
        date.setAlignment(Pos.CENTER);

        GridPane.setConstraints(imageview1, 0, 0);
        GridPane.setConstraints(nom, 0, 1);
        GridPane.setConstraints(date, 0, 2);

        gridItem.getChildren().addAll(imageview1, nom, date);

        gridItem.setPrefWidth(350);
        gridItem.setPrefHeight(220);
        gridItem.setCenterShape(true);
        gridItem.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/AfficherDetailsEvent.fxml"));
                    Parent tableViewParent = loader.load();
                    Scene scene = new Scene(tableViewParent);

                    AfficherDetailsEventController controller = loader.getController();
                    controller.initData(ev);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AccueilEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return gridItem;
    }
    
    public void remplirGrid(ObservableList<Evenement> list){
        int k = -1;
        int n;
        Evenement ev;
        for (int i = 0; i <= 1; i++) {
            n = list.size() % 2 == 0 ? (list.size() / 2) - 1 : list.size() / 2;
            for (int j = 0; j <= n; j++) {

                k++;
                if (k < list.size()) {
                    ev = list.get(k);

                    item = initItem(ev);

                    GridPane.setConstraints(item, i, j);
                    gridEvents.getChildren().add(item);
                }

            }
        }
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        container.getChildren().add(new MenuComponentEventController());

        
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        list = (ObservableList<Evenement>) edao.displayAll();

        remplirGrid(list);

    }

    @FXML
    private void chercherAction(ActionEvent event) {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        list = (ObservableList<Evenement>) edao.displayAllByNom(chercherTxt.getText());

        gridEvents.getChildren().clear();
        remplirGrid(list);
        
    }


    @FXML
    private void allEventAction(ActionEvent event) {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        list = (ObservableList<Evenement>) edao.displayAll();

        gridEvents.getChildren().clear();
        remplirGrid(list);
    }

}
