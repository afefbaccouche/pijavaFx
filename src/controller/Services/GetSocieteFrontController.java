/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dao.Services.CrudLikeSociete;
import dao.Services.CrudTableSociete;
import entity.Services.Service;
import entity.Services.Societe;
import entity.Services.like__societe;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import session.Session;
//import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class GetSocieteFrontController implements Initializable {
    static Societe   soc ;
    static int hello;
  
    @FXML
    private  JFXListView<Societe> listView;
  
    @FXML
    private JFXTextField recherche;

    CrudTableSociete crudtableSociete;
    
    List <Societe> societes ;
    
    @FXML
    Label label1 ;
      
     
 
     
    private Service ss;
     
    @FXML
    private ImageView back;
    ObservableList<Societe> lista2;
    
  @FXML
    private JFXButton accueil;

    @FXML
    private JFXButton logout;
      

 @FXML
    private void goToAccueil(ActionEvent event) throws IOException {
      Parent AjouteParent =  FXMLLoader.load(getClass().getResource("/view/client/FXMLHomePage.fxml"));
      
      Scene AjoutScene = new Scene (AjouteParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
      window.hide(); //optional
       window.setScene(AjoutScene);
       window.show(); 
    
       
    }

   
      @FXML
    private void logOut(ActionEvent event) {
        try {
            Session.logOut();
            Parent root = FXMLLoader.load(getClass().getResource("/view/login/FXMLLogin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GetSocieteFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
     
        CrudTableSociete crudTableSociete = new CrudTableSociete();
                List<Societe> listeSoc= new ArrayList<Societe>();
           
                listeSoc=crudTableSociete.getById(GetAllServicesFrontController.ss.getId());
                
      
       lista2 = FXCollections.observableArrayList(listeSoc);
        
      
        listView.setCellFactory(new Callback<ListView<Societe>, ListCell<Societe>>() {
            @Override
            public ListCell<Societe> call(ListView<Societe> param) {
               
                return new AssociationListCell();
                
            }
        });
         listView.setItems(lista2);
        

               List<String> listc = new ArrayList<>();
        
            for(Societe s:listeSoc){
                listc.add(s.getNom());
                listc.add(s.getAdress());
                listc.add(s.getTel());
            }
            
        TextFields.bindAutoCompletion(recherche,listc);
        
        search();
        
        /*****/
        
         back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          Parent ServiceParent = null;
         try {
             ServiceParent = FXMLLoader.load(getClass().getResource("/view/Services/GetAllServicesFront.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(AjoutServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      Scene SocieteScene = new Scene (ServiceParent);
      Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
      
       window.hide(); //optional
       window.setScene(SocieteScene);
       window.show(); 
         
     });
    }

  private void search() {
        FilteredList<Societe> filterdata = new FilteredList<>(lista2, e -> true);
        recherche.setOnKeyReleased((event) -> {
            System.out.println(recherche.getText());
            
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
              filterdata.setPredicate((Predicate<? super Societe>) type -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((type.getNom().contains(newValue)) || 
                            (type.getNom().toLowerCase().contains(newValue))
                            ||(type.getAdress().contains(newValue)) || 
                            (type.getAdress().toLowerCase().contains(newValue))
                            ||(type.getTel().contains(newValue))
                            
                            ){
                        return true;
                    }
                    return false;
                }) ;        
            });

            SortedList<Societe> sorteddata = new SortedList<>(filterdata);         
            lista2= sorteddata;  
            listView.setItems(lista2);
             
        });
    }
     
   
    private static class AssociationListCell extends ListCell<Societe> {
        CrudLikeSociete crudLikeSociete = new CrudLikeSociete();
        private HBox content;
        private Text nom;
        private Text tel;
        private Text adres;
        private Text service;
        
        private Label label1;
        private Label label2; 
        private Button demande;
       
        private JFXButton jaime;
        private Image icon;
        ImageView imageView;
        private ImageView imge;
        javafx.scene.image.Image im;
        public AssociationListCell() {
            super();
            
            jaime= new JFXButton("J'aime");
            demande = new Button("Demande Service");

            
            nom = new Text();
            tel = new Text();
            adres= new Text();
            service= new Text();
            
            imge = new ImageView();
            imge.setFitHeight(150);
            imge.setFitWidth(150);
            label1=new Label("Adresse : ");
            label2=new Label("Tel : ");
           VBox vBox = new VBox(jaime,new HBox(new Label("nom : "),nom),new HBox(label1,adres),new HBox(label2,tel));
    demande.setStyle("-fx-background-color:#545cee; -fx-background-radius: 5em; -fx-border-radius: 5em;-fx-text-fill: #ffffff");

           HBox hbox2= new HBox(demande);
            
                hbox2.setPadding(new Insets(50, 50, 20, 20));
                hbox2.setSpacing(10);
            
            
            vBox.setPrefWidth(250);

            content = new HBox(imge, vBox,hbox2);
            content.setSpacing(20);
            
            demande.setOnAction((event) -> {
                try {
                soc =getListView().getItems().get(getIndex()); 
                System.out.println("Wouhhhhhhhh"+soc);
                hello =soc.getId();
                 Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Services/AjoutDemandeServiceFront.fxml"));
                demande.getScene().setRoot(root);
                
               
            } catch (IOException ex) {
                Logger.getLogger(GetSocieteFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
            
            
            
        }
        
        
        

        dropBox conf = new dropBox();
        @Override
        protected void updateItem(Societe item, boolean empty) 
        {
            super.updateItem(item, empty);
            if (item != null && !empty) { 
               
                nom.setText(item.getNom());
                tel.setText(item.getTel()+"");
                adres.setText(item.getAdress());
                service.setText(item.getService().getNom());
              
                if(item.getImage().indexOf("/")==0){
                    String path=conf.downloadImage(item.getImage());                   
                    final String imageURI = new File(path).toURI().toString(); 
                    im = new javafx.scene.image.Image(imageURI);
                }else 
                     im =new Image("http://localhost/inlovewithsymfony/web/image1/"+item.getImage());
    
                System.out.println(item.getImage()+"111");
     
                
               imge.setImage(im);
               setGraphic(content);
               
               
               
               
               if(crudLikeSociete.islike(item)) 
               {icon = new javafx.scene.image.Image("http://localhost/inlovewithsymfony/web/image1/e.png"); 
                   System.out.println("like");
               }else
               {icon = new javafx.scene.image.Image("http://localhost/inlovewithsymfony/web/image1/i.png"); 
                   System.out.println("dislike");
               }
        imageView= new ImageView(icon);
        imageView.setFitWidth(30);
	imageView.setFitHeight(30);
        jaime.setGraphic(imageView);
	
               
               jaime.setText(crudLikeSociete.nblike(item)+"J'aime");
               
               
               jaime.setOnAction((event) -> {
                   if(!crudLikeSociete.islike(item)){
                        crudLikeSociete.add(new like__societe(session.Session.getConnectedUser(),item));
                         icon = new javafx.scene.image.Image("http://localhost/inlovewithsymfony/web/image1/e.png");
                   }else
                   {    crudLikeSociete.desLike(item);
                    icon = new javafx.scene.image.Image("http://localhost/inlovewithsymfony/web/image1/i.png");
//                   System.out.println(session.Session.getConnectedUser()
                   }
                   imageView= new ImageView(icon);
        imageView.setFitWidth(30);
	imageView.setFitHeight(30);
        jaime.setGraphic(imageView);
                   jaime.setText(crudLikeSociete.nblike(item)+"J'aime");
               });
            } else {
                setGraphic(null);
            }
        }
        
        
    }
    
}

        
                
           
            
          