package sample;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class mail {
    //Not working yet

    public void sendMail(String password,String email){
        Properties pros = new Properties();
        pros.put("mail.smtp.starttls.enable", "true");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.host","smtp.gmail.com");
        pros.put("mail.smtp.port","587");

        Session session = Session.getInstance(pros, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(email,password);
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("applicationhkr@outlook.com"));
            message.setSubject("Account registration");
            message.setText("Info");

            Transport.send(message);

        }catch(MessagingException e){
            e.printStackTrace();
        }
    }
}
