/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;
import entity.Loisir.Bricolage;
import entity.Loisir.Prefere;
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

public class ServicePrefere implements IService<Prefere>  {

 
    private Connection connection=ConnectionBaseDonn.connect.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement ps;
    private ResultSet rs;
       @Override
    public void add(Prefere t) {
        String req="insert into Prefere(client_pref_id,bri_pref_id) values('"+t.getClient_pref_id()+"','"+t.getBri_pref_id()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CrudTableSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void delete(Prefere t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void deletewish(int idbr) {
        String req="delete from Prefere where bri_pref_id = '"+idbr+"'";

          try {
             ste=connection.createStatement();
            ste.executeUpdate(req);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Impossible de supprimer ce video !!\n"+ex.getMessage());
        }      
    }

    @Override
    public void update(Prefere t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Bricolage> getPref(int k) {
       List<Bricolage> list = new ArrayList<Bricolage>();
        String req="SELECT * FROM `Bricolage` INNER JOIN `Prefere` ON bricolage.id=Prefere.bri_pref_id where client_pref_id='"+k+"'";
         
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
    public int counte(int k) {
        String req="select count(*) as qte from Bricolage INNER JOIN `Prefere` ON bricolage.id=Prefere.bri_pref_id where client_pref_id='"+k+"'";
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
    public Prefere getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prefere> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       }

   
