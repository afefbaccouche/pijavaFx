
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author soumaya ch
 */
public class User {
    
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String sold;
    private String adressUser;
    private String numUser;
    private String emailUser;
    private String passwordUser;
    private String role;
    private String userName;
    private String usernameCanonical;
    private String emailCanonical;
   private String pieceJustificatif;
     private boolean enabled = false;
    public User() {
    }

    public User(String nomUser, String prenomUser, String sold, String adressUser, String numUser, String emailUser, String passwordUser, String role, String userName, String usernameCanonical, String emailCanonical) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.sold = sold;
        this.adressUser = adressUser;
        this.numUser = numUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.role = role;
        this.userName = userName;
        this.usernameCanonical = usernameCanonical;
        this.emailCanonical = emailCanonical;
    }

    public User(int idUser, String nomUser, String prenomUser, String sold, String adressUser, String numUser, String emailUser, String passwordUser, String role, String userName, String usernameCanonical, String emailCanonical) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.sold = sold;
        this.adressUser = adressUser;
        this.numUser = numUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.role = role;
        this.userName = userName;
        this.usernameCanonical = usernameCanonical;
        this.emailCanonical = emailCanonical;
    }

    public User(int idUser, String nomUser, String prenomUser, String adressUser, String emailUser, String userName, String usernameCanonical, String emailCanonical) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.adressUser = adressUser;
        this.emailUser = emailUser;
        this.userName = userName;
        this.usernameCanonical = usernameCanonical;
        this.emailCanonical = emailCanonical;
    }

    
    
    
    
   
    public int getIdUser() {
        return idUser;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getAdressUser() {
        return adressUser;
    }

    public void setAdressUser(String adressUser) {
        this.adressUser = adressUser;
    }

    public String getNumUser() {
        return numUser;
    }

    public void setNumUser(String numUser) {
        this.numUser = numUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }
        public String getPieceJustificatif() {
        return pieceJustificatif;
    }

    public void setPieceJustificatif(String pieceJustificatif) {
        this.pieceJustificatif = pieceJustificatif;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", sold=" + sold + ", adressUser=" + adressUser + ", numUser=" + numUser + ", emailUser=" + emailUser + ", passwordUser=" + passwordUser + ", role=" + role + ", userName=" + userName + ", usernameCanonical=" + usernameCanonical + ", emailCanonical=" + emailCanonical + '}';
    }

    
    
  
    
  
}