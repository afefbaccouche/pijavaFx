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
public enum VideoNumber {
    
     INSTANCE;
     
     private int C;
     VideoNumber(){};
     
     public int getVideo() {
      return C; 
   }
     public void setVideo(int C) {
      this.C=C;
   }


}
