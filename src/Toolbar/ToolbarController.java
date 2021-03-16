package Toolbar;


import controller.client.HomePageController;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ToolbarController implements Initializable {
//
//  associations
//don 
//consommation
//services 
//sante 
//loisirs 
//events


   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    @FXML
//    private void AfficherReclamations(ActionEvent event) throws IOException {
//        
//        
//        
//      
//       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//
//        
//         loadWindow(getClass().getClassLoader().getResource("/view/client/Profil.fxml"), "Profil", stage);
//    }
     @FXML
    private void AfficherProfil(ActionEvent event) throws IOException {
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        
        loadWindow(getClass().getResource("/view/client/Profil.fxml"), "Profil", stage);
    }

    @FXML
    private void AfficherLoisirs(ActionEvent event) {
        loadWindow(getClass().getResource("/view/Loisir/FXML_addBri.fxml"), "Loisirs", null);
    }

//    @FXML
//    private void AfficherFactures(ActionEvent event) {
//        loadWindow(getClass().getResource("/library/assistant/ui/listmember/member_list.fxml"), "Member List", null);
//    }

public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try  {
            
        
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = FXMLLoader.load(loc);   

            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return controller;
    }    
    
    
    
    
    
    
}
