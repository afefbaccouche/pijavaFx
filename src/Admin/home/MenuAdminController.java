/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class MenuAdminController extends VBox {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnSante;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnConsoma;
    @FXML
    private Button btnService;
    @FXML
    private Button btnLoisir;
    @FXML
    private Button btnDon;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;

    public MenuAdminController() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Admin/home/MenuAdmin.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleClicks(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/superadmin/AfficherEventAdmin.fxml"));
            btnEvent.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToSociete(ActionEvent event) {
    }

    @FXML
    private void evenementAction(ActionEvent event) {
      
    }

}
