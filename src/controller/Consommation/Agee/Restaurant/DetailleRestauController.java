/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee.Restaurant;

import ConnectionBaseDonn.connect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import dao.Consommation.CommentaireService;
import dao.Consommation.ListData;
import dao.Consommation.ListDataComment;
import dao.Consommation.RatingServices;
import dao.Consommation.RestaurentServices;
import dao.UserDao;
import entity.Consommation.Commentaire;
import entity.Consommation.RatingEntity;
import entity.Consommation.Restaurants;
import entity.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class DetailleRestauController implements Initializable {
    ///////commentaire
    
    @FXML
    private JFXButton btnComment;
    
    
@FXML
    private TextArea commentArea;
    
///rating  
    
    
      @FXML
    private Rating BtnRating;
      
         @FXML
    private Label msg;
         @FXML
    private Button btnRetour;
    
    
        @FXML
    private JFXListView<Commentaire> ListComment;
        
    @FXML
    private Label DnomRestau;
      @FXML
    private Label DaddressR;

    @FXML
    private Label DtelR;
    
    @FXML  
    private Label DemailR;
    
    
        @FXML
    private ImageView DimageRestau;
   ///////////// reserver 
        @FXML
    private JFXButton btnReserver;
    

 
    private Connection connection;
     private Statement ste;
     
     private PreparedStatement pst;

     private ResultSet rs; 
    private ListData listdata = new ListData();

       // private ListDataComment listdataComment = new ListDataComment();

      
    JFileChooser  chosser;
    File file;
     String locat,format;
     
        private Restaurants selectRestau;
    
             Image image;
       public void RetriveData(Restaurants restaurant){
            image= new Image("/pidev/"+restaurant.getImagRestau());
               
        selectRestau=restaurant;
        //restaurant.setIdRestaurant(Integer.parseInt(idRUP.getText()));
      int res=restaurant.getIdRestaurant();
           DnomRestau.setText(restaurant.getNomRestaurant());
              DaddressR.setText(restaurant.getAdressRestaurant());
               DtelR.setText(restaurant.getNumRestaurant());
                DemailR.setText(restaurant.getEmailRestaurant());
                DimageRestau.setImage(image);
             }
           
     
       
       
    
     public DetailleRestauController(){
    
       connection =connect.getInstance().getCnx(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
         
        
        ///////// rating
        		   
      RestaurentServices ResI = RestaurentServices.getInstance();
      Restaurants Res = new Restaurants();
	
        
        
       
         btnReserver.setOnAction(e->{
               try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/ReservationRestau.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
////                
                ReservationRestauController controllerRestau = loader.getController();
               controllerRestau.RetriveData(selectRestau);
                  
                
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DetailleRestauController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
        
         btnComment.setOnAction(e->{
        //     EventCategorieDao catdao = new EventCategorieDao();
            UserDao userdao = new UserDao();
             
        Commentaire comment = new Commentaire();
             CommentaireService cS=new CommentaireService();
       
//             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//   LocalDateTime now = LocalDateTime.now(); 
             

       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
       Date date = new Date();  
    
        comment.setBody(commentArea.getText());
        comment.setCreatedDate(date);
        comment.setRestauId(ResI.rechercherRestauByNom(selectRestau.getNomRestaurant()));
        
        comment.setUserId(userdao.displayById(session.Session.getConnectedUser().getIdUser()));
        cS.add(comment);
         });
         
         
         
         
         
         //////////// liste de commentaire
         
         
////         ListComment.setCellFactory(new Callback<ListView<Commentaire>,ListCell<Commentaire>>(){
////            @Override
////            public ListCell<Commentaire> call(ListView<Commentaire> param) {
////            ListCell<Commentaire> cell= new ListCell<Commentaire>(){
////                
////            @Override
////            protected void updateItem(Commentaire comment,boolean btn1)
////            {
////                super.updateItem(comment, btn1);
////                
////                if(comment!=null)
////                {
////// 
////                    setText(comment.getBody());
////               }
////            }
////                   
////            };
////                    return cell;
////            }
////           
////       });
////           
////       
////      ListComment.setItems(listdataComment.getCommentaire());
      
      
      
      
      ///// rating function
      BtnRating.ratingProperty().addListener(new ChangeListener<Number>() {
            
          
             UserDao userdao = new UserDao();
             
        RatingEntity rating = new RatingEntity();
             RatingServices RS=new RatingServices();
       
//             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//   LocalDateTime now = LocalDateTime.now(); 
             

            @Override 
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              msg.setText("Rating : "+ t1.toString());
              
              SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
       Date date = new Date();  
       
       Restaurants ress=new Restaurants();
       ress=ResI.rechercherRestauByNom(selectRestau.getNomRestaurant());
       
      //int userr;
      // userr=userdao.displayById(session.Session.getConnectedUser().getIdUser());
    
//       if(((RS.rechercherRatingByUser(session.Session.getConnectedUser().getIdUser()))
//               ||( RS.rechercherRatingByRestau(selectRestau.getIdRestaurant()))))
//       {
//           System.out.println("hhhhhhhhhhhh");

//
//        rating.setRating(t1);
//        rating.setDateRating(date);
//        rating.setRestauIdR(ResI.rechercherRestauByNom(selectRestau.getNomRestaurant()));
//        
//        rating.setUserIdR(userdao.displayById(session.Session.getConnectedUser().getIdUser()));
//        RS.update(rating);
        
        
//       }
       
//       else{

       rating.setRating(t1);
        rating.setDateRating(date);
        rating.setRestauIdR(ResI.rechercherRestauByNom(selectRestau.getNomRestaurant()));
        
        rating.setUserIdR(userdao.displayById(session.Session.getConnectedUser().getIdUser()));
        RS.add(rating);
          }
//            }
            
        });
      
      btnRetour.setOnAction(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficherReastau.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
////                
                               
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DetailleRestauController.class.getName()).log(Level.SEVERE, null, ex);
            }
      });
      
    }
    
  
    
    
}
