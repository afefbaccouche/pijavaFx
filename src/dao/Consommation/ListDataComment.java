/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import entity.Consommation.Commentaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author soumaya ch
 */
public class ListDataComment {
      private ObservableList<Commentaire> commentaire=FXCollections.observableArrayList();
    
    

    public ListDataComment() {
        
        CommentaireService comm=CommentaireService.getInstance();
        commentaire= comm.getAll();
        System.out.println(commentaire);
    }
    
    public ObservableList<Commentaire> getCommentaire(){
        return commentaire;
    }
}
