/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Consommation.Agee;


import ConnectionBaseDonn.connect;
import dao.Consommation.ListDataProduit;
import dao.Consommation.ProduitServices;
import entity.Consommation.Panier;
import entity.Consommation.Produit;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import pidev.PiDev;


/**
 *
 * @author ASUS
 *
 *
 *
 *
 */
public class DetailController implements Initializable {

    private Connection connection;
    private Statement ste;

    private PreparedStatement pst;

    private ResultSet rs;
    private ListDataProduit listdata = new ListDataProduit();

    @FXML
    private ImageView imgview;

    @FXML
    private Label dprix;

    @FXML
    private Label dnom1;

    private Produit selectmatt;
    
    @FXML
    private Button btnretourP;

    Image img;
    @FXML
    private Label type;
    @FXML
    private ComboBox<String> qtemat;

  

    Produit materiel;
    ProduitServices smat;
    @FXML
    private Button panier;

    public void RetriveData(Produit m) {
        img = new Image("/pidev/" + m.getImageProduit());

        selectmatt = m;

        int res = m.getIdProduit();

        dnom1.setText(m.getNomProduit());

        dprix.setText(String.valueOf(m.getPrixProduit()));

        qtemat.getItems().addAll(
                "1",
                "2",
                "3",
                "4"
        );

        imgview.setImage(img);
    }

    public DetailController() {

       connection =connect.getInstance().getCnx();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProduitServices mat = ProduitServices.getInstance();
        Produit Res = new Produit();

//        btnretour.setOnAction(e -> {
//
//            try {
//                Parent page2 = FXMLLoader.load(getClass().getResource("Base.fxml"));
//                Scene scene = new Scene(page2);
//                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

        panier.setOnAction(e -> {

             Panier p = PiDev.panier;
    
                 
             selectmatt.setQuantiteProduit(Integer.parseInt(qtemat.getValue()));
            p.getProduits().add(selectmatt);
            try {

                Parent page2 = FXMLLoader.load(getClass().getResource("/view/Consommation/Agee/panier.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
//      


btnretourP.setOnAction(e->{
          try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Consommation/Agee/AfficheProduit.fxml"));
                Parent tableViewParent= loader.load();
                Scene scene = new Scene(tableViewParent);
                
                
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 } catch (IOException ex) {
                Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });
    }

    
}
