/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;



import entity.Consommation.Magasins;
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
public class ListDataMagasin {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Magasins> magasin=FXCollections.observableArrayList();
    
    

    public ListDataMagasin() {
        
        MagasinServices mag=MagasinServices.getInstance();
        magasin= mag.getAll();
        System.out.println(magasin);
    }
    
    public ObservableList<Magasins> getMagasin(){
        return magasin;
    }
   
}
