/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Roles;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author esprit
 */
public class Navigation {

    public static String getHomePagePath(String userRole) {
        String viewPath = "";
        if (userRole.equals(Roles.Client.toString())) {
            viewPath = "/view/client/FXMLHomePage.fxml";
      
  
        } else if (userRole.equals(Roles.super_admin.toString())) {
            viewPath = "/view/superAdmin/FXMLSuperAdmin.fxml";
        }
        else if (userRole.equals(Roles.Admin.toString())) {
            viewPath = "/Admin/home/Home.fxml";
        }
         else if (userRole.equals(Roles.Avocat.toString())) {
            viewPath = "/view/Services/AvocatRendezVous.fxml";
        }
          else if (userRole.equals(Roles.Doctor.toString())) {
            viewPath = "/view/article/FXMLUpdateDelArticle.fxml";
        } else if (userRole.equals(Roles.Manager.toString())) {
            viewPath = "/view/manager/FXMLCentre.fxml";
        }
        
        return viewPath;

    }


}
