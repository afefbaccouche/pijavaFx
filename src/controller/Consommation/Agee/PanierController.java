/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Consommation.Panier;
import entity.Consommation.Produit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidev.PiDev;

/**
 *
 * @author soumaya ch
 */
public class PanierController implements Initializable{
    @FXML
    private Button conachat;
    @FXML
    private Text pan;

        @FXML
    private Button commande;
    /**
     * Initializes the controller class.
     */
        
       private Produit m ;
       
        public void initialize(URL url, ResourceBundle rb) {
         Panier p = PiDev.panier;

        float prixT = 0;
        
        for (  Produit m  : p.getProduits()) {

            prixT = prixT + (m.getPrixProduit()*m.getQuantiteProduit());

        }
        
         pan.setText(prixT + "");

        conachat.setOnAction(e -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/Consommation/Agee/AfficheProduit.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
//         commande.setOnAction(e->{
//            
//             /////////// pdf
//          
//             Document document = new Document() {};
//        try{
//            
//            PdfWriter.getInstance(document, new FileOutputStream("../../HelloWorld.pdf"));
//            document.open();
//            
////                
//          Paragraph p2 = new Paragraph("facture.");
//	  
//          
//          
//          
//      
//            document.add(p2);
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//        document.close();
//            
//        });
        
        
        }
}
