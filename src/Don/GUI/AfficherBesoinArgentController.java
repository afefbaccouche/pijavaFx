/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.GUI;

import Don.Services.BesoinArgentServices;
import Don.entities.BesoinArgent;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller clasimport javafx.scene.layout.FlowPane; s
 *
 * @author jacem
 */
public class AfficherBesoinArgentController implements Initializable {

    @FXML
    private FlowPane flowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        flowPane = new FlowPane();
        consulterBesoinArgent();
    }

    private void consulterBesoinArgent() {

        //Code columns
        JFXTreeTableColumn<BesoinArgent, String> idMaisonRetraite = new JFXTreeTableColumn<>("Maison Retraite");
        idMaisonRetraite.setPrefWidth(150);
        idMaisonRetraite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getId())));

        JFXTreeTableColumn<BesoinArgent, String> Titre = new JFXTreeTableColumn<>("Titre");
        Titre.setPrefWidth(150);
        Titre.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getTitreBesoinArgent())));

        JFXTreeTableColumn<BesoinArgent, String> UserInNeed = new JFXTreeTableColumn<>("User");
        UserInNeed.setPrefWidth(150);
        UserInNeed.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getUserInNeed())));

        JFXTreeTableColumn<BesoinArgent, String> disponibilite = new JFXTreeTableColumn<>("disponibilite");
        disponibilite.setPrefWidth(150);
        disponibilite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getDisponibilite())));

        JFXTreeTableColumn<BesoinArgent, String> montant_argent = new JFXTreeTableColumn<>("Montant Argent");
        montant_argent.setPrefWidth(150);
        montant_argent.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getMontantArgent())));

        idMaisonRetraite.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        Titre.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        UserInNeed.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        disponibilite.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        montant_argent.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        //instance mtaa service
        BesoinArgentServices bs = new BesoinArgentServices();

        List<BesoinArgent> myList = bs.consulterBesoinArgent();
        ObservableList<BesoinArgent> BArgents = FXCollections.observableArrayList();
        myList.forEach(p -> BArgents.add(p));
        JFXTreeTableView<BesoinArgent> treeview = new JFXTreeTableView<>();
        final TreeItem<BesoinArgent> root = new RecursiveTreeItem<BesoinArgent>(BArgents, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(idMaisonRetraite, Titre, UserInNeed, disponibilite, montant_argent);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.getStylesheets().add(getClass().getResource("afficherbesoinargent.css").toExternalForm());
        treeview.setLayoutX(0);
        treeview.setLayoutY(20);
        treeview.setPrefHeight(400);
        treeview.setPrefWidth(600);
        FlowPane main = new FlowPane();
        main.setPadding(new Insets(10));
        JFXTextField search = new JFXTextField();
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<BesoinArgent>>() {
                    @Override
                    public boolean test(TreeItem<BesoinArgent> t) {
                        boolean flag = t.getValue().getTitreBesoinArgent().contains(newValue);
                        return flag;
                    }
                }
                );
            }
        });
        search.setPromptText("search ..");
        search.setLayoutX(20);
        search.setLayoutY(20);
        main.getChildren().add(0,search);
        main.getChildren().add(1,treeview);
        Scene scene = new Scene(main, 819, 607);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
