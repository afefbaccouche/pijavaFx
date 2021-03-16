/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import controller.article.FXMLUpdateDelArticleController;
import static controller.client.DetailsCentreController.selectedCentre;
import dao.CentreDao;
import dao.PromotionDao;

import dao.UserDao;
import entity.Centre;
import entity.Promotion;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import session.Session;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLListCentreController implements Initializable {

    public static Centre selectedCentre;

    @FXML
    private TableView<Centre> centreTable;
    @FXML
    private TextField searchField;

    private ArrayList<Centre> listCentres = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        TableColumn descCol = new TableColumn("Description");
        descCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        TableColumn prixCol = new TableColumn("prix/heure(DT)");
        prixCol.setCellValueFactory(
                new PropertyValueFactory<>("prix"));

        TableColumn nbrPlaceCol = new TableColumn("nombre de place");
        nbrPlaceCol.setCellValueFactory(
                new PropertyValueFactory<>("nbrPlace"));

        TableColumn addressCol = new TableColumn("adresse");
        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        TableColumn telCol = new TableColumn("Telephone");
        telCol.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));

        TableColumn promoCol = new TableColumn("En Promotion");
        promoCol.setCellValueFactory(
                new PropertyValueFactory<>("enPromotion"));

        //colorPromotionRows(nomCol, descCol, prixCol, nbrPlaceCol, addressCol, telCol);
        centreTable.getColumns().addAll(nomCol, descCol, prixCol, nbrPlaceCol, addressCol, telCol,promoCol);
        refreshTable();

    }

    @FXML
    private void Reservation(ActionEvent event) {
        Centre selected = centreTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Please choose Centre");
            errorAlert.show();
        } else {
            //ReserverCentreController.selectedCentre = selected;
            DetailsCentreController.selectedCentre = selected;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/client/FXMLDetailsCentre.fxml"));
                Parent content = loader.load();

                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void refreshTable() {

        System.out.println("Connected User from Centre Controller: " + Session.getConnectedUser().getIdUser());
        User fake = UserDao.getInstance().findById(Session.getConnectedUser().getIdUser());
        System.out.println("Fake: " + fake);
        listCentres = CentreDao.getInstance().getAll();
        for (int i = 0; i < listCentres.size(); i++) {
            ArrayList<Promotion> promos = PromotionDao.getInstance().getByCentre(listCentres.get(i).getId());
            if (!promos.isEmpty()) {

                Date currentDate = new Date();
                Date dateDebutPromotion = promos.get(0).getDateDebut();
                Date dateFinPromotion = promos.get(0).getDateFin();

                boolean isBetween = (currentDate.before(dateFinPromotion)) && (currentDate.after(dateDebutPromotion));
                System.out.println("is between: " + isBetween);

                if (isBetween) {
                    listCentres.get(i).setEnPromotion("oui");
                }

            }
        }

        System.out.println(listCentres);
        System.out.println(Session.getConnectedUser().getIdUser());

        ObservableList<Centre> data
                = FXCollections.observableArrayList(listCentres);

        centreTable.setItems(data);
        centreTable.refresh();
    }

    @FXML
    private void searchCentre(ActionEvent event) {
        String key = searchField.getText();
        ArrayList<Centre> listSearchCentre = new ArrayList();
        listSearchCentre = CentreDao.getInstance().searchCentre(key);
        ObservableList<Centre> data
                = FXCollections.observableArrayList(listSearchCentre);

        centreTable.setItems(data);
        centreTable.refresh();

    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/client/FXMLHomeSante.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUpdateDelArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void colorPromotionRows(TableColumn col1, TableColumn col2, TableColumn col4, TableColumn col5, TableColumn col6, TableColumn col7) {
        col1.setCellFactory(column -> {
            return new TableCell<Centre, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Centre auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (auxPerson.getEnPromotion().equals("oui")) {
                            setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: yellow"); //The background of the cell in yellow

                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                setTextFill(Color.WHITE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });

        /*
         col2.setCellFactory(column -> {
            return new TableCell<Centre, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Centre auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (auxPerson.getEnPromotion().equals("oui")) {
                            setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: yellow"); //The background of the cell in yellow

                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                setTextFill(Color.WHITE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });

        col4.setCellFactory(column -> {
            return new TableCell<Centre, Float>() {
                @Override
                protected void updateItem(Float item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item.toString()); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Centre auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (auxPerson.getEnPromotion().equals("oui")) {
                            setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: yellow"); //The background of the cell in yellow

                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                setTextFill(Color.WHITE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });

        col5.setCellFactory(column -> {
            return new TableCell<Centre, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item.toString()); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Centre auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (auxPerson.getEnPromotion().equals("oui")) {
                            setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: yellow"); //The background of the cell in yellow

                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                setTextFill(Color.WHITE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });

        col6.setCellFactory(column -> {
            return new TableCell<Centre, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Centre auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (auxPerson.getEnPromotion().equals("oui")) {
                            setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: yellow"); //The background of the cell in yellow

                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                setTextFill(Color.WHITE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });

        col7.setCellFactory(column -> {
            return new TableCell<Centre, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item.toString()); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Centre auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (auxPerson.getEnPromotion().equals("oui")) {
                            setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: yellow"); //The background of the cell in yellow

                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                setTextFill(Color.WHITE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };
        });
         */
    }
}
