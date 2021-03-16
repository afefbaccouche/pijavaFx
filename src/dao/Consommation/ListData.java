/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;



import entity.Consommation.Restaurants;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Restaurants> restaurant=FXCollections.observableArrayList();
    
    

    public ListData() {
        
        RestaurentServices res=RestaurentServices.getInstance();
        restaurant= res.getAll();
        System.out.println(restaurant);
    }
    
    public ObservableList<Restaurants> getRestaurant(){
        return restaurant;
    }
   
}
