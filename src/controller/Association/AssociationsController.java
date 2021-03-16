/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Association;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.Loisir.AlertMaker;
import dao.Services.ServiceAssociation;
import dao.Services.ServicePart;
import entity.Association;
import entity.Part;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import session.Session;


/**
 * FXML Controller class
 *
 * @author fahdj
 */
public class AssociationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TableColumn<Association, Integer> id;

    @FXML
    private TableView<Association> TableAssociations;

    @FXML
    private TableColumn<Association, String> nom;

    @FXML
    private TableColumn<Association, String> description;

    @FXML
    private TableColumn<Association, Integer> nbreClients;

    @FXML
    private TableColumn<Association, String> type;
    
        @FXML
    private JFXTextField TFNomAss;

    @FXML
    private JFXTextField TFDescriptionAss;
@FXML
    private AnchorPane mainContainer;
 @FXML
    private StackPane rootPane;

    @FXML
    private JFXComboBox<String > CBType;
     @FXML
    private JFXTextField rech;
    @FXML
    private JFXButton imprimer;
    private int CurrentSelected;
  
    
       @FXML
    private JFXTextField TFpfdf;

    @FXML
    private JFXButton Btnpdf;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       buildTableviewAssociationData();
        CBType.getItems().add("animaux");
        CBType.getItems().add("nature");
        CBType.getItems().add("olds");
       buildTableviewAssociationData();
       collapse();
    TFpfdf.setVisible(false);
    Btnpdf.setVisible(false);
    
       TableAssociations.setRowFactory( (TableView<Association> tv) -> {
    TableRow<Association> row = new TableRow<>();
     
    row.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            Association C = row.getItem();
            show();
            TFNomAss.setText(C.getName());
            TFDescriptionAss.setText(C.getDescription());
           
                  
//                CBSexe.getSelectionModel().select(C.getSexe());  
                    
                    
                    
        }
    });
    return row ;
});   

    }    
    
    public void show()
    {
    TFNomAss.setVisible(true);
    TFDescriptionAss.setVisible(true);
    CBType.setVisible(true);
    }
    public void collapse()
    {
     TFNomAss.setVisible(false);
    TFDescriptionAss.setVisible(false);
    CBType.setVisible(false);
    }
    
    
   
    private void buildTableviewAssociationData() {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        nbreClients.setCellValueFactory(new PropertyValueFactory<>("nbr"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<Association> P;
        ServiceAssociation cs = new ServiceAssociation();
        P = cs.getAll();
//P.stream().forEach(System.out::println);
        TableAssociations.setItems(P);

    }
     public void buildTableviewRecherche() {

        if (rech.getText().equals(" ")) {
            buildTableviewAssociationData();
            System.out.println("null");
        } else {
            ServiceAssociation sc = new ServiceAssociation();

            ObservableList<Association> data = sc.listerRecherche(rech.getText());

            TableAssociations.setItems(data);

        }
    }
    @FXML
    void RechercheAssociation(KeyEvent event) {
buildTableviewRecherche();
    }

    
      @FXML
    void SupprimerAssociation(ActionEvent event) {
ServiceAssociation sa= new ServiceAssociation();
 Association A = TableAssociations.getSelectionModel().getSelectedItem();
     sa.delete(A);
       buildTableviewAssociationData();
         AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Suppression", "Association supprimee avec succes");
  collapse();
    }
    
     @FXML
    void ModifierAssociation(ActionEvent event) {

    if(TFNomAss.getText().isEmpty() || TFDescriptionAss.getText().isEmpty())
    {
         AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Modification", "Veuillez remplir les champs");
         collapse();
         return;
    }
      else{  
        ServiceAssociation sa= new ServiceAssociation();

 Association A = TableAssociations.getSelectionModel().getSelectedItem();
 A.setName(TFNomAss.getText());
 A.setDescription(TFDescriptionAss.getText());
 sa.update(A);
  buildTableviewAssociationData();
 AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Modification", "Association modifiee avec succes");
  collapse();
    }}
    @FXML
    void ExportPDF(ActionEvent event) throws FileNotFoundException, DocumentException {
 ServiceAssociation sa= new ServiceAssociation();
         Association c = TableAssociations.getSelectionModel().getSelectedItem();
        CurrentSelected = c.getId();
        System.out.println("CurrentSelected "+CurrentSelected+" c.getId() "+c.getId());
          
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File ("C:\\wamp64\\www\\pijava-masterLaste\\src\\participation\\"+TFpfdf.getText()+".pdf")));
        document.open();
        document.add(new Paragraph("vous avez participer à cette association Votre participation est :"));
        document.add(new Paragraph(sa.afficher(c, CurrentSelected,Session.getConnectedUser().getIdUser())));
        document.close();
        TFpfdf.setVisible(false);
    Btnpdf.setVisible(false);
    }
 
    
    @FXML
    void AjouterAssociation(ActionEvent event) {
  try {
                Parent root;
                root=FXMLLoader.load(getClass().getResource("/view/Associations/AjouterAssociation.fxml"));
                TFNomAss.getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void imprimer(ActionEvent event) {
       
        if(AlertMaker.showChoiceAlert("PdF","voulez vous imprimer La participation en PDF ?" )){ 
       TFpfdf.setVisible(true);
    Btnpdf.setVisible(true);
    }
        else{
              AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Participation", "Vous avez participer avec Succées");
       
        }
        ServiceAssociation sa= new ServiceAssociation();
         Association c = TableAssociations.getSelectionModel().getSelectedItem();
        CurrentSelected = c.getId();
         System.out.println(c.getId());
        Part pa;
          pa = new Part(Session.getConnectedUser().getIdUser(),c.getId());
       
        ServicePart Ppa=new ServicePart();
        Ppa.add(pa);
        c.setNbr(c.getNbr()-1);
        sa.decrementd(c);
        buildTableviewAssociationData();
        System.out.println(c.getNbr());
    }
    }
    

