package com.teamcore.manageapp.service.service.utils;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
    final String hostMail;
    final String fromEmail;
    final String fromFullname;
    final String emailUser;
    final String fullnameUser;
    final String username;
    final String password;
    final String subject;
    final String body;

    public EmailSender(String hostMail, String fromEmail, String fromFullname, String emailUser, String fullnameUser, String username, String password,
                       String subject, String body) {
        this.hostMail = hostMail;
        this.fromEmail = fromEmail;
        this.fromFullname = fromFullname;
        this.emailUser = emailUser;
        this.fullnameUser = fullnameUser;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.body = body;

    }

    public void sendMail() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", hostMail);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.debug", "1");
        Session session_m = Session.getDefaultInstance(props, null);

        try {
            MimeMessage message = new MimeMessage(session_m);
            message.setFrom(new InternetAddress(fromEmail, fromFullname));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailUser, fullnameUser));

            message.setSubject(subject);


            message.setText(body);
            message.setHeader("Content-Type", "text/plain;charset=windows-1251");

            SMTPTransport t = (SMTPTransport) session_m.getTransport("smtp");
            t.connect(hostMail, username, password);
            t.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
