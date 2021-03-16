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
public class AjouterBesoinArgentController implements Initializable {

    @FXML
    private TextField titre = new TextField();
    @FXML
    private TextField user = new TextField();
    @FXML
    private TextField maisonR = new TextField();
    @FXML
    private TextField dispo = new TextField();
    @FXML
    private TextField montant = new TextField();
    @FXML
    private Button btnAjouter = new Button();
    @FXML
    private AnchorPane AnchorPane = new AnchorPane();
    @FXML
    private Button supprimer = new Button();
    @FXML
    private Button retourAjouBA = new Button();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        consulterBesoinArgent();
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(titre.textProperty(),
                        user.textProperty(),
                        maisonR.textProperty(),
                        dispo.textProperty(),
                        montant.textProperty()
                );
            }

            @Override
            protected boolean computeValue() {
                return (titre.getText().isEmpty()
                        || dispo.getText().isEmpty()
                        || montant.getText().equals("0"));
            }
        };

        montant.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!newValue.matches("\\d*")) {
                        montant.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                    if (Integer.parseInt(newValue.toString()) == 0 || newValue.startsWith(String.valueOf(0))) {
                        montant.setStyle(" -fx-border-color : red");
                    } else {
                        montant.setStyle(" -fx-border-color : transparent");
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

        btnAjouter.disableProperty().bind(bb);

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ajouterBesoinArgent();
            }
        });
        
        retourAjouBA.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/CompagneDon.fxml"));
                retourAjouBA.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
    }

    public void ajouterBesoinArgent() {
        String titreB = titre.getText();
        String userB = user.getText();
        int dispoB = Integer.parseInt(dispo.getText());
        Double montantB = Double.parseDouble(montant.getText());

        BesoinArgentServices bs = new BesoinArgentServices();
        if (!maisonR.getText().equals("") && !userB.equals("")) {
            int idMaison = Integer.parseInt(maisonR.getText());
            BesoinArgent o = new BesoinArgent(idMaison, titreB, userB, dispoB, montantB);
            bs.ajouterBesoinArgent(o);
        }
        if (!maisonR.getText().equals("") && userB.equals("")) {
            int idMaison = Integer.parseInt(maisonR.getText());
            BesoinArgent o = new BesoinArgent(idMaison, titreB, dispoB, montantB);
            bs.ajouterBesoinArgent(o);
        }
        if (maisonR.getText().equals("") && !userB.equals("")) {
            BesoinArgent o = new BesoinArgent(titreB, userB, dispoB, montantB);
            bs.ajouterBesoinArgent(o);
        }

        dispo.setText("");
        maisonR.setText("");
        montant.setText("");
        titre.setText("");
        user.setText("");
        
        
    }

    private void consulterBesoinArgent() {

        //Code columns
        BesoinArgentServices bs = new BesoinArgentServices();

        JFXTreeTableColumn<BesoinArgent, String> idMaisonRetraite = new JFXTreeTableColumn<>("Maison Retraite");
        idMaisonRetraite.setPrefWidth(100);
        idMaisonRetraite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getIdMaisonRetraite())));

        JFXTreeTableColumn<BesoinArgent, String> Titre = new JFXTreeTableColumn<>("Titre");
        Titre.setPrefWidth(100);
        Titre.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getTitreBesoinArgent())));

        JFXTreeTableColumn<BesoinArgent, String> UserInNeed = new JFXTreeTableColumn<>("User");
        UserInNeed.setPrefWidth(100);
        UserInNeed.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getUserInNeed())));

        JFXTreeTableColumn<BesoinArgent, String> disponibilite = new JFXTreeTableColumn<>("disponibilite");
        disponibilite.setPrefWidth(100);
        disponibilite.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getDisponibilite())));

        JFXTreeTableColumn<BesoinArgent, String> montant_argent = new JFXTreeTableColumn<>("Montant Argent");
        montant_argent.setPrefWidth(100);
        montant_argent.setCellValueFactory((TreeTableColumn.CellDataFeatures<BesoinArgent, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getMontantArgent())));

        idMaisonRetraite.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinArgent, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setIdMaisonRetraite(Integer.parseInt(event.getNewValue()));
            bs.modifierBesoinArgent("idMaisonRetraite", newValue, id);
        });

        idMaisonRetraite.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        Titre.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinArgent, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setTitreBesoinArgent(event.getNewValue());
            bs.modifierBesoinArgent("titreBesoinArgent", newValue, id);
        });

        Titre.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        UserInNeed.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinArgent, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setUserInNeed(event.getNewValue());
            bs.modifierBesoinArgent("idUserInNeed", newValue, id);
        });

        UserInNeed.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        disponibilite.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinArgent, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setDisponibilite(Integer.parseInt(event.getNewValue()));
            bs.modifierBesoinArgent("disponibiliteBesoin", newValue, id);
        });

        disponibilite.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        montant_argent.setOnEditCommit((TreeTableColumn.CellEditEvent<BesoinArgent, String> event) -> {
            int id = event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = event.getNewValue();
            event.getTreeTableView()
                    .getTreeItem(event.getTreeTablePosition().getRow())
                    .getValue().setDisponibilite(Integer.parseInt(event.getNewValue()));
            bs.modifierBesoinArgent("montantBesoin", newValue, id);
        });
        montant_argent.setCellFactory((TreeTableColumn<BesoinArgent, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        //instance mtaa service
        List<BesoinArgent> myList = bs.consulterBesoinArgent();
        System.out.println(myList);
        ObservableList<BesoinArgent> BArgents = FXCollections.observableArrayList();
        myList.forEach(p -> BArgents.add(p));
        JFXTreeTableView<BesoinArgent> treeview = new JFXTreeTableView<>();
        final TreeItem<BesoinArgent> root = new RecursiveTreeItem<BesoinArgent>(BArgents, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(idMaisonRetraite, Titre, UserInNeed, disponibilite, montant_argent);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);
        treeview.getStylesheets().add(getClass().getResource("afficherbesoinargent.css").toExternalForm());
        treeview.setLayoutX(316);
        treeview.setLayoutY(163);
        treeview.setPrefHeight(214);
        treeview.setPrefWidth(497);

        JFXTextField search = new JFXTextField();
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<BesoinArgent>>() {
                    @Override
                    public boolean test(TreeItem<BesoinArgent> t) {
                        boolean flag = false;
                        if (t.getValue().getTitreBesoinArgent().contains(newValue)) {
                            flag = t.getValue().getTitreBesoinArgent().contains(newValue);
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
        search.setPromptText("search ..");
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
                    bs.supprimerBesoinArgent(treeview.getSelectionModel().getSelectedItem().getValue());
                    consulterBesoinArgent();
                }
            }
        });
    }

}
