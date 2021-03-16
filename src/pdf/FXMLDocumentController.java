/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controller.client.HomePageController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author louka
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    
    @FXML
    private Button button;
    @FXML
    private Button button1;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        Document doc=new Document();

        try {
            PdfWriter.getInstance(doc,new FileOutputStream("document.pdf"));
            doc.open();
            Paragraph p1=new Paragraph();
            Paragraph p2=new Paragraph();
            Paragraph p3=new Paragraph();
            Paragraph p4=new Paragraph();
            Paragraph p5=new Paragraph();
            Paragraph p6=new Paragraph();
            Paragraph p7=new Paragraph();
            Paragraph p8=new Paragraph();
            Paragraph p9=new Paragraph();
            Paragraph p10=new Paragraph();
            Paragraph p11=new Paragraph();
            Paragraph p12=new Paragraph();
            Paragraph p13=new Paragraph();
            Paragraph p14=new Paragraph();
            Paragraph p15=new Paragraph();
            
            p1.add("                                                               Certificat de participation \n \n \n");
            p2.add("le "+date);
            p3.add("à "+time);
            p4.add("     Cher(e) "+cin.getText().toString()+" ,");
            p5.add("Nous vous adressons nos remerciements les plus sincères \n pour votre participation à la compagne de don '"+
                    nom.getText().toString()+"'"+"\n \n  Grâce à vos dons de toutes natures, \n vous allez faciliter la vie pour plusieurs personnes âgées."+
                            "\nC'est grâce à nos fidèles donateurs \n que nous pouvons accompagner nos chères âgées \n à retrouver une vie quasi normale.");
            p6.add(" \n \n \n \n \n \n \n \n Signature \n Equipe Happy Olds");
         
            doc.add(p1);
            
            doc.add(p4);
            doc.add(p5);
            doc.add(p6);
            doc.add(p2);
            doc.add(p3);
            
            doc.close();
            } catch (Exception ex) {
            ex.printStackTrace();
            doc.close();
        }
        
        System.out.println("done");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        button1.setOnAction(e -> {

            
            try {
                
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/CompagneDonUser.fxml"));
                button1.getScene().setRoot(root);
                
                
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
    }    
    
}
