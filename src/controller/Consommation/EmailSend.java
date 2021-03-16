/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */

package controller.Consommation;

/**
 *
 * @author Naveen
 */
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
//import session.Session;
public class EmailSend {

    public static void main(String args[]){
        try{
            String host ="smtp.gmail.com" ;
            String user = "userjava64@gmail.com";
            String pass = "userjava2019";
            String to ="robot.sh2018@gmail.com"; //session.Session.getConnectedUser().getEmailUser();
            String from = "userjava64@gmail.com";
            String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
            String messageText = "Your Is Test Email :";
            boolean sessionDebug = false;

            Properties props = System.getProperties(); /// set # type of properties

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            //// securiry
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};//// @ of sender
            msg.setRecipients(Message.RecipientType.TO, address);//// @ reciver email
            msg.setSubject(subject); msg.setSentDate(new Date());//// message send date
            msg.setText(messageText); //// actual message

            
            /// send pdf
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            Multipart multipart = new MimeMultipart();
//            messageBodyPart = new MimeBodyPart();
//          //  String file = "C://Users//soumaya ch//Desktop//sprintJava//"+name;
//            //    DataSource source = new FileDataSource(file);
//              //  messageBodyPart.setDataHandler(new DataHandler(source));
//                //messageBodyPart.setFileName(name);
//                multipart.addBodyPart(messageBodyPart);
//                msg.setContent(multipart);
//                ///////////////////////
            
            
           Transport transport=mailSession.getTransport("smtp"); /// server
           transport.connect(host, user, pass);/// auth sender email et pwd
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
}
