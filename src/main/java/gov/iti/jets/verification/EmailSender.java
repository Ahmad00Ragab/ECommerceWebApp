package gov.iti.jets.verification;

import gov.iti.jets.exceptions.CannotSendMessageException;
import gov.iti.jets.user.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class EmailSender {


    // aaa.iti44@outlook.com
    // aaaiti2024
    public String getRandom(){
        Random rand = new Random();
        int number = rand.nextInt(999999);

        return String.valueOf(number);
    }

    public boolean sendEmail(User user){
        boolean test = false;
        String toEmail = user.getEmail();
        String fromEmail ="aaa.iti44@outlook.com";
        String password="aaaiti2024";

        try{

            String host = "smtp.office365.com";
            Properties properties = getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");



            Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("abdulmajeed.ghndy9@outlook.com", password);
                }
            });

            Message message = getMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Email Verification Code");
            message.setText("Successfully verified you Email"+"\n"+ "Your verification code is: " + user.getVerificationCode());


            test = true;


        } catch (MessagingException e) {
            throw new CannotSendMessageException();
        }

        return  test;

    }


    private Message getMessage(Session session) throws MessagingException {
        return new MimeMessage(session);
    }

    private Properties getProperties(){
        return new Properties();
    }
}
