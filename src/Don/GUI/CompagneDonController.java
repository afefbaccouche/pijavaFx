package Don.GUI;

import Don.Services.CompagneDonServices;
import Don.entities.CompagneDon;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jacem
 */
public class CompagneDonController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<CompagneDon> tableCompagne;
    private ObservableList obs;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button dashboardCD1;
    
    
    private TableColumn<CompagneDon, Integer> tabId;
    @FXML
    private TableColumn<CompagneDon, String> tabTitre;
    @FXML
    private TableColumn<CompagneDon, String> tabDescription;
    @FXML
    private TableColumn<CompagneDon, Date> tabDate;
    @FXML
    private TableColumn<CompagneDon, Integer> tabNBR;
    @FXML
    private Button ajouBesoinArgent;
    @FXML
    private Button ajouBesoinObjet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        date.setValue(LocalDate.now());
        CompagneDonServices c = new CompagneDonServices();
        obs = FXCollections.observableArrayList(c.consulterCompagneDon());
        tableCompagne.setItems(obs);
      //  this.tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tabTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        this.tabDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.tabDate.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        this.tabNBR.setCellValueFactory(new PropertyValueFactory<>("nbrParticipation"));
        ajouBesoinArgent.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/AjouterBesoinArgent.fxml"));
                ajouBesoinArgent.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
        
        dashboardCD1.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Admin/home/Home.fxml"));
                dashboardCD1.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
        
        ajouBesoinObjet.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/AjouterBesoinObjet.fxml"));
                ajouBesoinObjet.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
        
        
          
    } 
    
   @FXML
    public void ajouterCompagne(ActionEvent e){
        if(!(titre.getText().equals("") && description.getText().equals(""))){
        CompagneDonServices c = new CompagneDonServices(); 
        CompagneDon cdd= new CompagneDon(titre.getText(),description.getText());
        c.ajouterCompagneDon(cdd);
        tabRafresh();
        }
    }
    @FXML
    public void modifierCompagne(ActionEvent e){
      try{
        if(tableCompagne.getSelectionModel().getSelectedItem() !=null){
            CompagneDon d = tableCompagne.getSelectionModel().getSelectedItem();
        if((titre.getText().equals("") && description.getText().equals(""))){
            this.titre.setText(d.getTitre());
            this.description.setText(d.getDescription());
            date.setValue(LocalDate.now());
        }else{
            d.setTitre(titre.getText());
            d.setDescription(description.getText());
        CompagneDonServices c = new CompagneDonServices(); 
        c.modifierCompagneDon(d);
        tabRafresh();
        this.titre.setText("");
        this.description.setText("");
        }
        }
      }catch(Exception ex){
            Alert alert=new Alert(AlertType.INFORMATION);
          alert.setTitle("erreur");
          alert.setHeaderText(null);
          alert.setContentText("Selectioner une ligne du tableau");
          Optional<ButtonType> btn= alert.showAndWait();
      }
    }
    @FXML
    public void supprimerCompagne(ActionEvent e){
        if(tableCompagne.getSelectionModel().getSelectedItem() !=null){
          Alert alert=new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Confirmation de suppression");
          alert.setHeaderText(null);
          alert.setContentText("voulez vous supprimer la compagne Don ?");
          Optional<ButtonType> btn= alert.showAndWait();
          if(btn.get()==ButtonType.OK){
        CompagneDonServices c = new CompagneDonServices();  
        c.supprimerCompagneDon(tableCompagne.getSelectionModel().getSelectedItem());
        tabRafresh();
          }
        }
        
    }
    @FXML
    public void annuler(){ 
    }
    
    private void tabRafresh(){
        CompagneDonServices c = new CompagneDonServices();
        obs = FXCollections.observableArrayList(c.consulterCompagneDon());
        tableCompagne.setItems(obs);
    }

    private void goToaffBA(ActionEvent event) {
        
               
               // AfficherBesoinArgentController qr = loader.getController();
               
    }
    
  
    
}
