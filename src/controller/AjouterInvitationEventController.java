/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EventInvitationDao;
import dao.UserDao;
import entity.Evenement;
import entity.EventInvitation;
import entity.User;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import session.Session;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class AjouterInvitationEventController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private TextField chercherText;
    @FXML
    private Button chercherBtn;
    @FXML
    private GridPane userGridPane;
    @FXML
    private Button inviterBtn;
    @FXML
    private Text nomEventLabel;
    @FXML
    private Button allUserBtn;

    Image image;
    GridPane item;
    Evenement selectedEvent;
    User selectedUser;
    User connectedUser;
    UserDao userdao = UserDao.getInstance();
    
    ObservableList<User> userList;
    

    public GridPane initItem(User u) {
        GridPane gridItem = new GridPane();

        image = new Image("/pidev/images/userIcon.png");
        ImageView imageview1 = new ImageView();
        imageview1.setImage(image);
        imageview1.setFitWidth(100);
        imageview1.setFitHeight(100);

        Label nom = new Label(u.getPrenomUser() + " " + u.getNomUser());
        nom.setFont(Font.font("Cambria", 20));
        nom.setMaxWidth(Double.MAX_VALUE);
        nom.setAlignment(Pos.CENTER);

        GridPane.setConstraints(imageview1, 0, 0);
        GridPane.setConstraints(nom, 0, 1);
        GridPane.setHalignment(imageview1, HPos.CENTER);
        GridPane.setValignment(imageview1, VPos.CENTER);

        gridItem.getChildren().addAll(imageview1, nom);

        gridItem.setPrefWidth(350);
        gridItem.setPrefHeight(220);
        gridItem.setCenterShape(true);
        gridItem.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                nom.setStyle("-fx-background-color:  #6c757d; ");
                nom.setTextFill(Color.web("white"));
                selectedUser = u;

            }
        });

        return gridItem;
    }

    public void initData(Evenement evenement) {
        selectedEvent = evenement;
        nomEventLabel.setText("Qui voulez vous inviter à l'événement " + selectedEvent.getNomEvent() + " ?");

    }
    
    public void remplirGrid(ObservableList<User> userList){
        int k = -1;
        int n;
        User u;
        n = userList.size() % 4 == 0 ? (userList.size() / 4) - 1 : userList.size() / 4;
        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= 3; j++) {

                k++;

                if (k < userList.size()) {
                    u = userList.get(k);

                    item = initItem(u);

                    GridPane.setConstraints(item, j, i);

                    userGridPane.getChildren().add(item);
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
        connectedUser = new User();
        connectedUser = userdao.displayById(Session.getConnectedUser().getIdUser());

        userList = FXCollections.observableArrayList();
        userList = (ObservableList<User>) userdao.displayAllByRole(connectedUser);
        
        remplirGrid(userList);

        
    }

    @FXML
    private void inviterAction(ActionEvent event) {

        EventInvitationDao invidao = EventInvitationDao.getInstance();
        Date date = new Date();
        EventInvitation invit = new EventInvitation(connectedUser, selectedUser, selectedEvent, false, new java.sql.Date(date.getTime()));
        
        invidao.add(invit);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Votre invitation a été envoyer");
        alert.show();
        
        String messageText=connectedUser.getPrenomUser()+ " "+connectedUser.getNomUser()+" vous a invité à l'événement "+selectedEvent.getNomEvent();
        
        controller.EmailEvent.send(messageText, "nadabk06@gmail.com");
    }

    @FXML
    private void chercherAction(ActionEvent event) {
        
        userList=(ObservableList<User>) userdao.displayAllByPrenom(chercherText.getText());
        userGridPane.getChildren().clear();
        remplirGrid(userList);
    }

    @FXML
    private void allUserAction(ActionEvent event) {
        
        userList=(ObservableList<User>) userdao.displayAllByRole(connectedUser);
        userGridPane.getChildren().clear();
        remplirGrid(userList);
    }

}
