/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Loisir;
       



/**
 *
 * @author fahdj
 */
public enum VideoChoice {
     INSTANCE;
     
     private String C;
     VideoChoice(){};
     
     public String getVideo() {
      return C; 
   }
     public void setVideo(String C) {
      this.C=C;
   }
}
