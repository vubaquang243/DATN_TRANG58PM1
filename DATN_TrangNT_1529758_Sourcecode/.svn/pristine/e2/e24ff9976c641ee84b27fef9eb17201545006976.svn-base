package com.seatech.framework.email;

import java.io.UnsupportedEncodingException;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.activation.*;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

public class TPCPEMail {
    private String host;
    private String username;
    private String pwd;
    private Session session;

    public TPCPEMail(String host, String username, String pwd) {
        this.host = host;
        this.username = username;
        this.pwd = pwd;
        this.session = getSession();
    }

    public void sendMailForCustomer(String fromName, String fromEMailAddress,
                                    String toEmailAddressList,
                                    String ccEmailAddressList, String subject,
                                    String emailContent,
                                    DataSource[] attachments) throws Exception {
        MimeMessage message =
            getMimeMessage(fromName, fromEMailAddress, toEmailAddressList,
                           ccEmailAddressList, subject, emailContent,
                           attachments);

        Transport.send(message);
    }

    private MimeMessage getMimeMessage(String fromName,
                                       String fromEMailAddress,
                                       String toEmailAddressList,
                                       String ccEmailAddressList,
                                       String subject, String emailContent,
                                       DataSource[] attachments) throws AddressException,
                                                                        MessagingException,
                                                                        UnsupportedEncodingException {
        int attachLen;
        String toAddrList[] = toEmailAddressList.split(";");
        int toAddrListLen = toAddrList.length;
        InternetAddress toInternetAddress[] =
            new InternetAddress[toAddrListLen];

        for (int i = 0; i < toAddrListLen; ++i) {
            toInternetAddress[i] = new InternetAddress(toAddrList[i]);
        }
        // Define message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEMailAddress, fromName));
        message.addRecipients(Message.RecipientType.TO, toInternetAddress);
        if (ccEmailAddressList != null) {
            String ccAddrList[] = ccEmailAddressList.split(";");
            int ccAddrListLen = ccAddrList.length;
            InternetAddress ccInternetAddress[] =
                new InternetAddress[ccAddrListLen];

            for (int i = 0; i < toAddrListLen; ++i) {
                toInternetAddress[i] = new InternetAddress(toAddrList[i]);
            }
            for (int i = 0; i < ccAddrListLen; ++i) {
                ccInternetAddress[i] = new InternetAddress(ccAddrList[i]);
            }
            message.addRecipients(Message.RecipientType.CC, ccInternetAddress);
        }

        message.setSubject(subject, "UTF-8");

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        messageBodyPart.setContent(emailContent, "text/html; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (null != attachments) {
            attachLen = attachments.length;
            // Part of attachments
            for (int i = 0; i < attachLen; ++i) {
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(attachments[i]));
                messageBodyPart.setFileName(attachments[i].getName());
                multipart.addBodyPart(messageBodyPart);
            }
        }
        // Put parts in message
        message.setContent(multipart);

        return message;
    }

    public void sendEMail(String fromName, String fromEMailAddress,
                          String toEmailAddressList, String ccEmailAddressList,
                          String subject, String emailContent,
                          DataSource[] attachments) throws Exception {
        Message message =
            getMessage(fromName, fromEMailAddress, toEmailAddressList,
                       ccEmailAddressList, subject, emailContent, attachments);

        Transport.send(message);
    }

    private Session getSession() {
        Authenticator auth = new Authenticator(username, pwd);
        Properties props = new Properties();
        props.setProperty("mail.smtp.submitter",
                          auth.getPasswordAuthentication().getUserName());
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "25");
        Session session = Session.getInstance(props, auth);

        return session;
    }

    private Message getMessage(String fromName, String fromEMailAddress,
                               String toEmailAddressList,
                               String ccEmailAddressList, String subject,
                               String emailContent,
                               DataSource[] attachments) throws Exception {

        int attachLen;
        String toAddrList[] = toEmailAddressList.split(";");
        int toAddrListLen = toAddrList.length;
        InternetAddress toInternetAddress[] =
            new InternetAddress[toAddrListLen];

        for (int i = 0; i < toAddrListLen; ++i) {
            toInternetAddress[i] = new InternetAddress(toAddrList[i]);
        }

        //        Session session = getSession();

        // Define message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEMailAddress, fromName));
        message.addRecipients(Message.RecipientType.TO, toInternetAddress);
        if (ccEmailAddressList != null) {
            String ccAddrList[] = ccEmailAddressList.split(";");
            int ccAddrListLen = ccAddrList.length;
            InternetAddress ccInternetAddress[] =
                new InternetAddress[ccAddrListLen];

            for (int i = 0; i < toAddrListLen; ++i) {
                toInternetAddress[i] = new InternetAddress(toAddrList[i]);
            }
            for (int i = 0; i < ccAddrListLen; ++i) {
                ccInternetAddress[i] = new InternetAddress(ccAddrList[i]);
            }
            message.addRecipients(Message.RecipientType.CC, ccInternetAddress);
        }
        message.setSubject(subject);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        messageBodyPart.setContent(emailContent, "text/html; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (null != attachments) {
            attachLen = attachments.length;
            // Part of attachments
            for (int i = 0; i < attachLen; ++i) {
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(attachments[i]));
                messageBodyPart.setFileName(attachments[i].getName());
                multipart.addBodyPart(messageBodyPart);
            }
        }
        // Put parts in message
        message.setContent(multipart);

        return message;
    }

    public ByteArrayDataSource createxmlDataSource(byte[] XMLContent,
                                                   String name) {
        ByteArrayDataSource XMLDataSource =
            new ByteArrayDataSource(XMLContent, "application/xml");
        XMLDataSource.setName(name);
        return XMLDataSource;
    }

    public ByteArrayDataSource createPDFDataSource(byte[] PDFContent,
                                                   String name) {
        ByteArrayDataSource PDFDataSource =
            new ByteArrayDataSource(PDFContent, "application/pdf");
        PDFDataSource.setName(name);
        return PDFDataSource;
    }


    private class Authenticator extends javax.mail.Authenticator {
        private PasswordAuthentication authentication;

        public Authenticator(String username, String pwd) {
            authentication = new PasswordAuthentication(username, pwd);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
