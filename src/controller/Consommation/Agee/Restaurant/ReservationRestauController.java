/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee.Restaurant;

import ConnectionBaseDonn.connect;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.Consommation.ReservationService;
import dao.Consommation.RestaurentServices;
import dao.UserDao;
import entity.Consommation.Reservation;
import entity.Consommation.Restaurants;
import entity.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author soumaya ch
 */
public class ReservationRestauController implements Initializable {
 
     @FXML
    private JFXTextField nombre;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXComboBox<String> description;

    @FXML
    private JFXTimePicker heure;

    @FXML
    private JFXTextField aunomde;

    @FXML
    private JFXButton reserver;
    
    @FXML
    private ImageView imageRR;

    @FXML
    private Label idRestauR;

    @FXML
    private Label addressRestau;

    @FXML
    private Label PhoneRestau;

    @FXML
    private Label emailRestau;

  

    @FXML
    private Label nameRestau;
    
    
    @FXML
    private Button btnAcc;

       private Connection connection;
        
       
         private Restaurants selectRestau;
    
           
           @FXML
    private ImageView imageReserv;
          Image image;
       public void RetriveData(Restaurants restaurant){
           image= new Image("/pidev/"+restaurant.getImagRestau());
        selectRestau=restaurant;
        //restaurant.setIdRestaurant(Integer.parseInt(idRUP.getText()));
      int res=restaurant.getIdRestaurant();
           nameRestau.setText(restaurant.getNomRestaurant());
              addressRestau.setText(restaurant.getAdressRestaurant());
               PhoneRestau.setText(restaurant.getNumRestaurant());
                emailRestau.setText(restaurant.getEmailRestaurant());
                 imageReserv.setImage(image);
                 imageRR.setImage(image);
             }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          description.getItems().addAll("Aucune Occasion","Occasion","Evènement","Anniversaire");
          
          
          btnAcc.setOnAction(e->{
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/Accueil.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(ReservationRestauController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
          
//          book.setOnAction(e->{
           //   reserver();
//          });


    
    


//    @FXML
//    private void reserver(ActionEvent event)throws IOException{
    reserver.setOnAction(e->{
        
    
       connection = connect.getInstance().getCnx(); 
      User user1=new User();
      user1.getIdUser();
      
            LocalDate nDate= date.getValue();
            LocalTime nTime= heure.getValue();
            LocalDateTime dateTime = nDate.atTime(nTime);
          
            Instant instant = nTime.atDate(LocalDate.of(nDate.getYear(), nDate.getMonth(),
                    nDate.getDayOfMonth())).
            atZone(ZoneId.systemDefault()).toInstant();
           
             java.util.Date date_util =java.util.Date.from(dateTime.atZone(ZoneId.systemDefault())
      .toInstant());
             java.sql.Timestamp dateF = new java.sql.Timestamp(date_util.getTime());
             System.out.println(date_util);
//         


 RestaurentServices ResI = RestaurentServices.getInstance();
      Restaurants Res11 = new Restaurants();
           
           ReservationService reserve =new ReservationService();
           
             UserDao userdao = new UserDao();
              Date datee = new Date();  
           
      Reservation Res = new Reservation();
      
      
      if(dateF.before(datee))
      {
      
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("date dija etablie!");
      alert.show();
       aunomde.setText("");
      nombre.setText("");
     
      }
      else
      {
      Res.setAunomdeReserv(aunomde.getText());
      Res.setNombreReserv(Integer.parseInt(nombre.getText()));
      Res.setDescriptionReserv(description.getValue());
      Res.setDateReserv(dateF);
     Res.setIdrestaurant(ResI.rechercherRestauByNom(selectRestau.getNomRestaurant()));
      Res.setIduser(userdao.displayById(session.Session.getConnectedUser().getIdUser()));
      reserve.add(Res);
      
      Notifications notification=Notifications.create()
                    .title("reservation")
                    .text("succée")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("click");
                }
                        
                    });
            notification.showConfirm();
      }
      
           

            
             /////////// pdf
             Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
            document.open();
            Paragraph p1 = new Paragraph("Hello ! you have reservation"+dateF+"   in retaurant   "+(selectRestau.getNomRestaurant())+" de prix 10D");
            
            Paragraph p2 = new Paragraph("Examsmyantra.com is committed to provide you valuable information and tutorials on various technologies.");
	    document.add(p1);
            document.add(p2);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        document.close();
            
            ////// mail
            try{
            String host ="smtp.gmail.com" ;
            String user = "userjava64@gmail.com";
            String pass = "userjava2019";
            String to ="soumaya.chemkhi@esprit.tn"; //session.Session.getConnectedUser().getEmailUser();
            String from = "userjava64@gmail.com";
            String subject = "you have a reservation "+dateF+"   in retaurant   "+(selectRestau.getNomRestaurant());
            String messageText = "Your Is Test Email :";
            boolean sessionDebug = false;

            Properties props = System.getProperties(); /// set # type of properties

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            //// securiry
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};//// @ of sender
            msg.setRecipients(Message.RecipientType.TO, address);//// @ reciver email
            msg.setSubject(subject); msg.setSentDate(new Date());//// message send date
            msg.setText(messageText); //// actual message

            
            /// send pdf
//           String name="HelloWorld.pdf";
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            Multipart multipart = new MimeMultipart();
//            messageBodyPart = new MimeBodyPart();
//          String file = "C://Users//soumaya ch//Desktop//"+name;
//                DataSource source = new FileDataSource(file);
//                messageBodyPart.setDataHandler(new DataHandler(source));
//               messageBodyPart.setFileName(name);
//                multipart.addBodyPart(messageBodyPart);
//                msg.setContent(multipart);
//                ///////////////////////
            
            
           Transport transport=mailSession.getTransport("smtp"); /// server
           transport.connect(host, user, pass);/// auth sender email et pwd
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           
           
           
           
           
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
            
          }); 
    
            
           // payment();
    }
    
    
/*    public void payment(){
        
            UserDao userdao = new UserDao();
             
        Reservation rating = new Reservation();
        
        User user1=new User();
       int pay;
       user1=userdao.displayById(session.Session.getConnectedUser().getIdUser());
    pay=(int)(user1.getSold())-10;
    
    user1.setSold(pay);
    
        System.out.println(pay);
        }
    
   */
}