/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.GUI;

import Don.Services.BesoinObjetServices;
//import Don.entities.BesoinObjet;
import Don.entities.BesoinObjet;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller clasimport javafx.scene.layout.FlowPane; s
 *
 * @author jacem
 */
public class AfficherBesoinObjetController implements Initializable {

    //@FXML
    //private FlowPane flowPane;
    @FXML
    private Button DASHBOARDAAA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
       ////flowPane = new FlowPane();
        consulterBesoinObjet();
        
        DASHBOARDAAA.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/CompagneDon.fxml"));
                DASHBOARDAAA.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
    }

    private void consulterBesoinObjet() {

        //Code columns
        JFXTreeTableColumn<BesoinObjet, String> Titre = new JFXTreeTableColumn<>("Titre");
        Titre.setPrefWidth(200);
        Titre.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getTitreBesoinObjet())));
        
        
        JFXTreeTableColumn<BesoinObjet, String> idMaisonRetraite = new JFXTreeTableColumn<>("Maison Retraite");
        idMaisonRetraite.setPrefWidth(200);
        idMaisonRetraite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getId())));

        

        JFXTreeTableColumn<BesoinObjet, String> UserInNeed = new JFXTreeTableColumn<>("User");
        UserInNeed.setPrefWidth(200);
        UserInNeed.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getUserInNeed())));

        JFXTreeTableColumn<BesoinObjet, String> disponibilite = new JFXTreeTableColumn<>("disponibilite");
        disponibilite.setPrefWidth(200);
        disponibilite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getDisponibilite())));

        JFXTreeTableColumn<BesoinObjet, String> typeBesoinObjet = new JFXTreeTableColumn<>("Description");
        typeBesoinObjet.setPrefWidth(200);
        typeBesoinObjet.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getTypeBesoinObjet())));

        idMaisonRetraite.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        Titre.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        UserInNeed.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        disponibilite.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        typeBesoinObjet.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        //instance mtaa service
        BesoinObjetServices bs = new BesoinObjetServices();

        List<BesoinObjet> myList = bs.consulterBesoinObjet();
        ObservableList<BesoinObjet> BObjets = FXCollections.observableArrayList();
        myList.forEach(p -> BObjets.add(p));
        JFXTreeTableView<BesoinObjet> treeview = new JFXTreeTableView<>();
        final TreeItem<BesoinObjet> root = new RecursiveTreeItem<BesoinObjet>(BObjets, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(idMaisonRetraite, Titre, UserInNeed, disponibilite, typeBesoinObjet);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.getStylesheets().add(getClass().getResource("afficherbesoinobjet.css").toExternalForm());
        treeview.setLayoutX(0);
        treeview.setLayoutY(20);
        treeview.setPrefHeight(400);
        treeview.setPrefWidth(900);
        FlowPane main = new FlowPane();
        main.setPadding(new Insets(10));
        JFXTextField search = new JFXTextField();
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<BesoinObjet>>() {
                    @Override
                    public boolean test(TreeItem<BesoinObjet> t) {
                        boolean flag = t.getValue().getTitreBesoinObjet().contains(newValue);
                        return flag;
                    }
                }
                );
            }
        });
        search.setPromptText("Chercher un besoin Objet ..");
        search.setLayoutX(20);
        search.setLayoutY(20);
        main.getChildren().add(0,search);
        main.getChildren().add(1,treeview);
        Scene scene = new Scene(main, 1050, 576);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
