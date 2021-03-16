/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.GUI;

import Don.Services.BesoinObjetServices;
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
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacem
 */
public class AjouterBesoinObjetController implements Initializable {

    @FXML
    private TextField titre = new TextField();
    @FXML
    private TextField user = new TextField();
    private TextField maisonR = new TextField();
    private TextField dispo = new TextField();
    private TextField type = new TextField();
    @FXML
    private Button btnAjouter = new Button();
    @FXML
    private AnchorPane AnchorPane = new AnchorPane();
    @FXML
    private Button supprimer = new Button();
     @FXML
    private Button retourAjouBO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        consulterBesoinObjet();
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(titre.textProperty(),
                        user.textProperty(),
                        maisonR.textProperty(),
                        dispo.textProperty(),
                        type.textProperty()
                );
            }

            @Override
            protected boolean computeValue() {
                return (titre.getText().isEmpty()
                        || dispo.getText().isEmpty()
                        || type.getText().equals(""));
            }
        };

        type.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!newValue.matches("\\d*")) {
                        type.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    if (Integer.parseInt(newValue.toString()) == 0 || newValue.startsWith(String.valueOf(0))) {
                        type.setStyle(" -fx-border-color : red");
                    } else {
                        type.setStyle(" -fx-border-color : transparent");
                    }
                } catch (Exception e) {

                }
            }
        });

        maisonR.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!newValue.matches("\\d*")) {
                        maisonR.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    if (Integer.parseInt(newValue.toString()) == 0 || newValue.startsWith(String.valueOf(0))) {
                        maisonR.setStyle(" -fx-border-color : red");
                    } else {
                        maisonR.setStyle(" -fx-border-color : transparent");
                    }
                } catch (Exception e) {

                }
            }
        });
        
        retourAjouBO.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/CompagneDon.fxml"));
                retourAjouBO.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});

       // btnAjouter.disableProperty().bind(bb);

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ajouterBesoinObjet();
            }
        });
    }

    public void ajouterBesoinObjet() {
        String titreB = titre.getText();
        String userB = user.getText();
        int dispoB = Integer.parseInt(dispo.getText());
        String typeB = type.getText();

        BesoinObjetServices bs = new BesoinObjetServices();
        if (!maisonR.getText().equals("") && !userB.equals("")) {
            int idMaison = Integer.parseInt(maisonR.getText());
            BesoinObjet o = new BesoinObjet(idMaison, titreB, userB, dispoB, typeB);
            bs.ajouterBesoinObjet(o);
        }
        if (!maisonR.getText().equals("") && userB.equals("")) {
            int idMaison = Integer.parseInt(maisonR.getText());
            BesoinObjet o = new BesoinObjet(idMaison, titreB, dispoB, typeB);
            bs.ajouterBesoinObjet(o);
        }
        if (maisonR.getText().equals("") && !userB.equals("")) {
            BesoinObjet o = new BesoinObjet(titreB, userB, dispoB, typeB);
            bs.ajouterBesoinObjet(o);
        }

        dispo.setText("");
        maisonR.setText("");
        type.setText("");
        titre.setText("");
        user.setText("");
    }

    private void consulterBesoinObjet() {

        //Code columns
        BesoinObjetServices bs = new BesoinObjetServices();

        JFXTreeTableColumn<BesoinObjet, String> idMaisonRetraite = new JFXTreeTableColumn<>("Maison Retraite");
        idMaisonRetraite.setPrefWidth(100);
        idMaisonRetraite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getIdMaisonRetraite())));

        JFXTreeTableColumn<BesoinObjet, String> Titre = new JFXTreeTableColumn<>("Titre");
        Titre.setPrefWidth(100);
        Titre.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getTitreBesoinObjet())));

        JFXTreeTableColumn<BesoinObjet, String> UserInNeed = new JFXTreeTableColumn<>("User");
        UserInNeed.setPrefWidth(100);
        UserInNeed.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getUserInNeed())));

        JFXTreeTableColumn<BesoinObjet, String> disponibilite = new JFXTreeTableColumn<>("disponibilite");
        disponibilite.setPrefWidth(100);
        disponibilite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getDisponibilite())));

        JFXTreeTableColumn<BesoinObjet, String> typeBesoinObjet = new JFXTreeTableColumn<>("Type Besoin");
        typeBesoinObjet.setPrefWidth(100);
        typeBesoinObjet.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinObjet, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getTypeBesoinObjet())));

        idMaisonRetraite.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinObjet, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setIdMaisonRetraite(Integer.parseInt(event.getNewValue()));
            bs.modifierBesoinObjet("idMaisonRetraite", newValue, id);
        });

        idMaisonRetraite.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        Titre.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinObjet, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setTitreBesoinObjet(event.getNewValue());
            bs.modifierBesoinObjet("titreBesoinObjet", newValue, id);
        });

        Titre.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        UserInNeed.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinObjet, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setUserInNeed(event.getNewValue());
            bs.modifierBesoinObjet("idUserInNeed", newValue, id);
        });

        UserInNeed.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        disponibilite.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinObjet, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setDisponibilite(Integer.parseInt(event.getNewValue()));
            bs.modifierBesoinObjet("disponibiliteBesoin", newValue, id);
        });

        disponibilite.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        typeBesoinObjet.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinObjet, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setDisponibilite(Integer.parseInt(event.getNewValue()));
            bs.modifierBesoinObjet("montantBesoin", newValue, id);
        });
        typeBesoinObjet.setCellFactory((TreeTableColumn<BesoinObjet, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        //instance mtaa service
        List<BesoinObjet> myList = bs.consulterBesoinObjet();
        System.out.println(myList);
        ObservableList<BesoinObjet> BObjets = FXCollections.observableArrayList();
        myList.forEach(p -> BObjets.add(p));
        JFXTreeTableView<BesoinObjet> treeview = new JFXTreeTableView<>();
        final TreeItem<BesoinObjet> root = new RecursiveTreeItem<BesoinObjet>(BObjets, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(idMaisonRetraite, Titre, UserInNeed, disponibilite, typeBesoinObjet);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);
        treeview.getStylesheets().add(getClass().getResource("afficherbesoinobjet.css").toExternalForm());
        treeview.setLayoutX(316);
        treeview.setLayoutY(163);
        treeview.setPrefHeight(214);
        treeview.setPrefWidth(497);

        JFXTextField search = new JFXTextField();
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<BesoinObjet>>() {
                    @Override
                    public boolean test(TreeItem<BesoinObjet> t) {
                        boolean flag = false;
                        if (t.getValue().getTitreBesoinObjet().contains(newValue)) {
                            flag = t.getValue().getTitreBesoinObjet().contains(newValue);
                        }
                        if (t.getValue().getUserInNeed().contains(newValue)) {
                            flag = t.getValue().getUserInNeed().contains(newValue);
                        }

                        return flag;
                    }
                }
                );
            }
        });
        search.setPromptText("Rechercher un besoin objet ..");
        search.setPrefHeight(50);
        search.setPrefWidth(301);
        search.setLayoutX(489);
        search.setLayoutY(394);
        AnchorPane.getChildren().add(search);
        AnchorPane.getChildren().add(treeview);

        supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (treeview.getSelectionModel().getSelectedItem() == null) {

                } else {
                    bs.supprimerBesoinObjet(treeview.getSelectionModel().getSelectedItem().getValue());
                    consulterBesoinObjet();
                }
            }
        });
    }

}
