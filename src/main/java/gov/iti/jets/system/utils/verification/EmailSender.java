package gov.iti.jets.system.utils.verification;

import gov.iti.jets.models.User;
import gov.iti.jets.system.exceptions.CannotSendMessageException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class EmailSender {

    // aaa.iti44@outlook.com
    // aaaiti2024

    private static final String SMTP_HOST =  "smtp.office365.com";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_AUTH_USER = "abdulmajeed.ghndy9@outlook.com";
    private static final String password ="";


    public static String getRandom(){
        Random rand = new Random();
        int number = rand.nextInt(999999);

        return String.valueOf(number);
    }
    public static void sendVerificationCode(String recipientEmail, String verificationCode) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.trust", SMTP_HOST);


        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, password);
            }
        });


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SMTP_AUTH_USER));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Email Verification Code");
        message.setText("Dear User,\n\nYour verification code is: " + verificationCode + "\n\nThank you!");


        Transport.send(message);
        System.out.println("Verification email sent to " + recipientEmail);
    }






}
