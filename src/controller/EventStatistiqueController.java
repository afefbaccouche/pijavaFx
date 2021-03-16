/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvenementDao;
import dao.EventCategorieDao;
import dao.EventParticipationDao;
import entity.Evenement;
import entity.EventCategorie;
import entity.EventParticipation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Baklouti
 */
public class EventStatistiqueController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Button accueilBtn;

    public void pieChart() {
        EventCategorieDao catdao = EventCategorieDao.getInstance();
        List<EventCategorie> categories = catdao.displayAll();
        List<Integer> nbrEventList = new ArrayList<>();
        categories.forEach(cat -> {
            nbrEventList.add(new EvenementDao().displayAllByCategorie(cat.getId()).size());
        });

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < categories.size(); i++) {
            pieChartData.addAll(
                    new PieChart.Data(categories.get(i).getNomCategorie(), nbrEventList.get(i))
            );
        }
        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 18 arial;");
        final PieChart chart = new PieChart(pieChartData);
        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        double total = 0;
                        for (PieChart.Data d : chart.getData()) {
                            total += d.getPieValue();
                        }
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
                        caption.setText(text);
                    }
            );
        }
        chart.setTitle("Evénement par catégorie");
        container.getChildren().addAll(chart, caption);
    }

    public void graphicStatics() {
        ObservableList<Integer> nbrParticipList = FXCollections.observableArrayList();
        List<Evenement> events = new EvenementDao().displayAll();

        List<String> eventNames = events.stream().map(e -> e.getNomEvent()).collect(Collectors.toList());

        events.forEach(e -> {
            nbrParticipList.add(new EventParticipationDao().displayAllByEvent(e.getId()).size());
        });

        ObservableList<String> eventNamesObservable= FXCollections.observableArrayList(eventNames);
        

        xAxis.setCategories(eventNamesObservable);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < nbrParticipList.size(); i++) {
            series.getData().add(new XYChart.Data<>(eventNames.get(i), nbrParticipList.get(i)));
        }

        barChart.getData().add(series);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pieChart();

        graphicStatics();

        //System.out.println("particip=" + new EventParticipationDao().displayAllByEvent(34));
    }

    @FXML
    private void accueilAction(ActionEvent event) {
        
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/Admin/home/Home.fxml"));
            accueilBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(EventStatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
