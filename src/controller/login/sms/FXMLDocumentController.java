/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author louka
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField tel;
    @FXML
    private Button button;
    @FXML
    private Button retourSMS;
    
    private String executeGet(final String http_url) {
        String res = null;
        try {
            URL url = new URL(http_url);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                res += strTemp;
            }
        } catch (Exception ex) {
        }
        return res;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
      String x="https://rest.nexmo.com/sms/json?api_key=4d9af6b4&api_secret=XMeZt9eHm1LAYPex&from=NEXMO&to=216"+tel.getText()+"&text=HappyOlds"; 
      String response = executeGet(x);
            if (response != null) {
                System.out.println("sent");
            } else {
                System.out.println("not sent");
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        retourSMS.setOnAction(e-> { try {
                    Parent root;
                root=FXMLLoader.load(getClass().getResource("/Don/GUI/CompagneDonUser.fxml"));
                retourSMS.getScene().setRoot(root);
            
                } catch (IOException io) {
                    System.out.print("aaa");
                }});
    }    
    
}
