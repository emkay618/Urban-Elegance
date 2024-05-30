package com.moshao.model;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class JavaMailUtil {

    public static void sendHtmlMail(String recepient, String subject, String message, String invoiceContent) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "kutloano.moshao@bothouniversity.com";
        String password = "RC433698~mt20000";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message emailMessage = prepareHtmlMessage(session, myAccountEmail, recepient, subject, message, invoiceContent);
        Transport.send(emailMessage);
        System.out.println("Message sent successfully");
    }

    private static Message prepareHtmlMessage(Session session, String myAccountEmail, String recepient, String subject, String message, String invoiceContent) {
        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(myAccountEmail));
            emailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            emailMessage.setSubject(subject);

            // Create multipart message
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");

            // Create multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(invoiceContent, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            emailMessage.setContent(multipart);

            return emailMessage;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
