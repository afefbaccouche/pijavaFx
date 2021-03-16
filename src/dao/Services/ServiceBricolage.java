/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;

import entity.Comments;
import entity.Loisir.Bricolage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pirateos
 */
public class ServiceBricolage implements IService<Bricolage> {
 
 private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public void add(Bricolage s) {
        String req="insert into Bricolage(client_bri_id,url_bri,tire_bri,description_bri) values('"+s.getClient_bri_id()+"','"+s.getUrl_bri()+"','"+s.getTire_bri()+"','"+s.getDescription_bri()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    public void addComment(int bId,String C, int uId) {
        String req="INSERT INTO `comment_bri`( `bricolage_id`, `Comment`, `date`, `userid_id`) values('"+bId+"','"+C+"',CURRENT_DATE ,'"+uId+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

     
    public void delete(Bricolage s) {
           String req="delete from Bricolage where id = '"+s.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer ce video !!\n"+ex.getMessage());
        }      
       }
  
 
    
    
    public void updateComment(String S,int i) {
  
       String req = "UPDATE comment_bri SET comment=? where id = ?";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
        ps.setString(1, S);
        ps.setInt(2, i);
        
        

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("Commentaire modifie");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }
  }
    
    public void update(Bricolage s) {
  
       String req = "UPDATE Service SET url_bri=?, tire_bri=?, description_bri=? where id = '"+s.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
        ps.setString(1, s.getUrl_bri());
        ps.setString(2, s.getTire_bri());
        ps.setString(3, s.getDescription_bri());
        

        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("Le bricolage a été modifier avec succès");
          }
          } catch (SQLException e) {
                     System.out.println("Erreur"+e.getMessage());
 
          }
  }
    public List getAll() {
 List<Bricolage> list = new ArrayList<Bricolage>();
        String req="select * from Bricolage";
         
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int id=rs.getInt("id");
               String url= rs.getString("url_bri");
               String titre =rs.getString("tire_bri");
               String desc =rs.getString("description_bri");
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Bricolage s = new Bricolage( url, titre ,desc);
               s.setId(id);
               list.add(s);
              
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        
         return list;   
    
    }
    
    
    public List getAllComments(int i) {
 List<Comments> list = new ArrayList<Comments>();
        String req="SELECT C.id,C.comment,C.date,U.username from comment_bri C, user U where C.userid_id=U.id and C.bricolage_id='"+i+"' order by C.date desc";
         
        try {
            ste=connection.createStatement();
            
            rs=ste.executeQuery(req);
            while(rs.next()){
                int id= rs.getInt("id");
               String comment= rs.getString("comment");
               String date =rs.getString("date");
               String username =rs.getString("username");
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
               Comments C = new Comments(id, comment, date ,username);
                              list.add(C);
              
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        
         return list;   
    
    }
    
    
    
    
    public int counte() {
        String req="select count(*) as qte from Bricolage";
      int x=0;
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int num=rs.getInt("qte");
        return num;
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        
          
    return 0;
    }

    @Override
    public Bricolage getById(int id) {
        String req="select * from Bricolage where id = '"+id+"'";
           Bricolage s = new Bricolage();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               String url= rs.getString("url_bri");
               String titre =rs.getString("tire_bri");
               String desc =rs.getString("description_bri");
               
              
                //list.add(new Societe(Id , nom, tel ,adresse,image));
             
               s.setId(id);
               s.setTire_bri(titre);
               s.setDescription_bri(desc);
               s.setUrl_bri(url);
            
               
        }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }

        return s;
    }
    
    
    
}
