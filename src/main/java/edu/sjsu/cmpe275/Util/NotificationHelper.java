package edu.sjsu.cmpe275.Util;


import edu.sjsu.cmpe275.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class NotificationHelper {
//    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(String mess){

//        public void sendEmail() {
            Mail mail = new Mail();
            mail.setMailFrom("sakethreddy.333@gmail.com");
            mail.setMailTo("sakethreddy.banda@sjsu.edu");
            mail.setMailSubject("Spring Boot - Email Example");
            mail.setMailContent("Learn How to send Email froms spring boot");
            MailConfiguration mailConfiguration = new MailConfiguration();
            mailSender = mailConfiguration.getMailSender();
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            try {

//                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//
//                mimeMessageHelper.setSubject(mail.getMailSubject());
//                mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "sakethreddy.333@gmail.com"));
//                mimeMessageHelper.setTo(mail.getMailTo());
//                mimeMessageHelper.setText(mail.getMailContent());
                SimpleMailMessage message = new SimpleMailMessage();

                message.setFrom(mail.getMailFrom());
                message.setTo(mail.getMailTo());
                message.setSubject("This is a plain text email");
                message.setText("Hello guys! This is a plain text email.");

                mailSender.send(message);
//                mailSender.send(mimeMessageHelper.getMimeMessage());

            }
            catch (Exception e){
                e.printStackTrace();
            }
//            catch (MessagingException e) {
//                e.printStackTrace();
//            }
//            catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
