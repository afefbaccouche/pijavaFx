package controller.Services;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dao.Services.CrudTableDemandeService;
import entity.Services.DemandeService;
import entity.Services.Societe;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import session.Session;


/**
 * FXML Controller class
 *
 * @author amani
 */
public class AjoutDemandeServiceFrontController implements Initializable {
  @FXML
   private JFXButton btnAdd;

    @FXML
    private ImageView back;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXDatePicker dp;
    
   
    
   Societe societe ;
   User user ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/GetSocieteFront.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
       window.hide(); //optional
       window.setScene(SocieteScene);
       window.show(); 
         
     });
       btnAdd.setOnAction((ActionEvent e) -> {
           try{
           LocalDate localDate = dp.getValue();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
String formattedString = localDate.format(formatter);

        DemandeService demandeservice = new DemandeService(description.getText()
                ,formattedString
                ,GetSocieteFrontController.soc
                ,Session.getConnectedUser() );
           CrudTableDemandeService demande = new CrudTableDemandeService();
           demande.add(demandeservice);
           
           description.setText("");
           dp.setValue(null);
           System.out.println("avant");
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/view/Services/GetAllDemandeServiceFront.fxml"));                    
           Parent tableViewParent= loader.load();         
           Scene scene = new Scene(tableViewParent);
           GetAllDemandeServiceFrontController controller = loader.getController();
           Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.show();
           
           }catch (IOException ex){
           
           }
           
       });
       


// System.out.println("l'id ajout demande service "+GetSocieteFrontController.hello);
      //  System.out.println("looll"+Session.getConnectedUser());
      //  societe =GetSocieteFrontController.soc;
      // user =Session.getConnectedUser();
      //System.out.println(societe+"hhhhh"+user);
              
 
        
        
    }    
    
   
}
