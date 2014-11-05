package com.hehua.commons.utils;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by liuweiwei on 14-9-18.
 */
public class EmailSender {

    private final String host = "smtp.163.com";
    private final int port = 25;
    private final String user = "hehuaqinzi@163.com";
    private final String password = "hehuaqinzi123";

    private static final EmailSender instance = new EmailSender();

    private com.sun.mail.util.MailLogger log;

    public static EmailSender getInstance() {
        return instance;
    }

    private Properties getProperty() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", this.host);
        properties.put("mail.smtp.port", this.port);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.from", this.user);
        return properties;
    }

    public void sendText(String to, String subject, String body) throws MessagingException {
        Properties properties = this.getProperty();
        MyAuthenticator authentication = new MyAuthenticator(this.user, this.password);
        Session session = Session.getDefaultInstance(properties, authentication);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(subject);
        mimeMessage.setText(body);
        Transport.send(mimeMessage);
    }

    public void sendHtml(String to, String subject, String body) throws MessagingException {
        Properties properties = this.getProperty();
        MyAuthenticator authentication = new MyAuthenticator(this.user, this.password);
        Session session = Session.getDefaultInstance(properties, authentication);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(body, "text/html; charset=utf-8");
        Transport.send(mimeMessage);
    }

    private class MyAuthenticator extends Authenticator {

        private String username;
        private String password;

        public MyAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
        }
    }

    public static void main(String[] args) {
        EmailSender emailUtil = EmailSender.getInstance();
        try {
            emailUtil.sendHtml("yixiao@hehuababy.com", "subject", "<h1>lala</h1><p>hello world</p><hr />");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
