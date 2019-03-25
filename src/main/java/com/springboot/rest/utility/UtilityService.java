package com.springboot.rest.utility;

import com.springboot.rest.customexception.CommonException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * SpringBootRestDemo 2019 Filename: UtilityService.java Description:
 * UtilityService class request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Service
public class UtilityService {
    
    private static final Logger LOGGER = LogManager.getLogger(UtilityService.class);

    UtilityService(){
        //do nothing constructor
    }
    /**
     * Method is used for send email to the user where there is one link which
     * user has to click for complete the authentication phase.
     *
     * @author Itmusketeers
     * @version 1.0
     * @Last modified 2019-03-07
     */
    public static void sendMail(String to, String subject, String emailBody, Environment environment) throws CommonException  {
        final String username = environment.getProperty("spring.mail.username");
        final String password = environment.getProperty("spring.mail.password");

        Properties props = new Properties();
        props.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", environment.getProperty("spring.mail.host"));
        props.put("mail.smtp.port", environment.getProperty("spring.mail.port"));

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(emailBody, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
    }

    public static String convertDateTOString(Date date) {
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String convertDateTOString(Date date,String formate) {
        String pattern = formate;
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
    
    public static Date convertStringTODate(String strDate) throws CommonException  {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strDate);
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
        return date;

    }
    
    public static Date currentDate(){
        return new Date();
    }

    public static String generateConfiramtionCode() {
        return UUID.randomUUID().toString();
    }

}
