package dao.Services;
import entity.Services.RendezVous;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author amani
 */
public class CrudTableRendezVous implements IRendezVous<RendezVous>{
    
    private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
    
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    
    
    
 
    
    public User getAvocat () {
         User user = new User();
        String req="select * from user where role LIKE 'Avocat' ";
        
        
          try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String nom=rs.getString("username");
             
               user.setIdUser(Id);
               user.setUserName(nom);
               System.out.println("avocat ed test"+user.getUserName());
                System.out.println("avocat ed test"+user.getIdUser());
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
       
        return user;
    
    
    }
      
    @Override
    public void add(RendezVous RV) {
 //System.out.println(getAvocat ().getIdUser()); 
        
         String req="insert into rendez_vous (description,time,date,reponseAvocat,idAge,idAvocat,etat) values('"
                 
                 +RV.getDescription()+"','"
                 +RV.getTime()+"','"
                 +sdf.format(RV.getDate())+"','"
                 +RV.getReponseAvocat()+"','"
                 +Session.getConnectedUser().getIdUser()+"','"
                 +getAvocat().getIdUser()+"','"
                 +RV.getEtat()+
                 "')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        
            } 
    
    @Override
    public void delete(RendezVous RV) {

         String req="delete from rendez_vous where id = '"+RV.getId()+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer une Societe\n"+ex.getMessage());
        }  

    }

    @Override
    public void update(RendezVous RV) {
  String req = "UPDATE rendez_vous SET id=?,reponseAvocat=?,etat=? where id ='"+RV.getId()+"'";
 
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
        
        ps.setInt(1, RV.getId());
        ps.setString(2, RV.getReponseAvocat());
       ps.setInt(3, RV.getEtat());

       
        int rowsUpdated = ps.executeUpdate();
          if (rowsUpdated > 0) {
            System.out.println("La Societe a été modifier avec succès");
          }
          } catch (SQLException e) {
              System.out.println("ya une erreur");
                     System.out.println("Erreur"+e.getMessage());
 
          }    
       
        
        
        
    }

    @Override
    public RendezVous getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<RendezVous> getAll() {
     
        List<RendezVous> listRendezVous = new ArrayList<RendezVous>();
        User user =new User(); 
        user =getAvocat();
        System.out.println("com on user "+Session.getConnectedUser().getIdUser());
        System.out.println("com on lowyer"+user.getIdUser());
       String req="SELECT * FROM rendez_vous WHERE idAge=" +Session.getConnectedUser().getIdUser()+" and idAvocat ="+user.getIdUser();
   
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               System.out.println("*****");  
               int Id= rs.getInt("id");
               String desc =rs.getString("description");
               Time time =rs.getTime("time");
               Date date =rs.getDate("date");
               String rep =rs.getString("reponseAvocat");
             int etat =rs.getInt("etat") ;
             
             


              RendezVous Rv = new RendezVous(Id,desc,time,date,rep,user,etat);
                //System.out.println(Rv);
          
          listRendezVous.add(Rv);
             
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println("crud table rendez vous "+listRendezVous);
         System.out.println(listRendezVous.size());
         return listRendezVous; 


    
    }

  
     
        public List<RendezVous> getAllAvocat() {
     
        List<RendezVous> listRendezVous = new ArrayList<RendezVous>();
        User user =new User(); 
        user =getAvocat();
        System.out.println("com on user "+Session.getConnectedUser().getIdUser());
        System.out.println("com on lowyer"+user.getIdUser());
       String req="SELECT * FROM rendez_vous WHERE idAvocat="+user.getIdUser();
   
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
               System.out.println("*****");  
               int Id= rs.getInt("id");
               String desc =rs.getString("description");
               Time time =rs.getTime("time");
               Date date =rs.getDate("date");
               String rep =rs.getString("reponseAvocat");
             int etat =rs.getInt("etat") ;
             
             


              RendezVous Rv = new RendezVous(Id,desc,time,date,rep,user,etat);
                //System.out.println(Rv);
          
          listRendezVous.add(Rv);
             
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println("crud table rendez vous "+listRendezVous);
         System.out.println(listRendezVous.size());
         return listRendezVous; 


    
    }

        
     
    }
     
   
    
    
    
   
 
   
    

