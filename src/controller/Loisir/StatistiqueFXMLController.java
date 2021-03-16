/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Loisir;

import dao.Services.ServiceBricolage;
import entity.Loisir.Bricolage;
import java.awt.Color;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pirateos
 */
public class StatistiqueFXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceBricolage sb=new ServiceBricolage();
        List<Bricolage>lb=sb.getAll();
        List<String>ls=lb.stream().map(x->x.getTire_bri()).collect(Collectors.toList());
        
int i=0;
        PieChart pieChart = new PieChart();
 ls.stream().forEach(x->{
        PieChart.Data ss = new PieChart.Data(x, i+1);
        pieChart.getData().add(ss);   
    });
     
        
        pieChart.setPrefSize(400, 300);
 
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setStartAngle(30);
 
        final Label caption = new Label("");
     //   caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");
 
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
 
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
 
      //  rootPane.setTitle("JavaFX PieChart (o7planning.org)");
        
        

         rootPane.getChildren().addAll(pieChart, caption);
    }    
    
}
