package org.ebuy.service;

import org.ebuy.model.TokenMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Ozgur Ustun on May, 2020
 */

@Service
public class EmailService {

    public final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRegisterMail(TokenMail receiveMailDto) {
        String subject = "Please verify your email address...";
        String message = "To confirm registration, please click the link: " + " http://localhost:8080/registerservice/api/registrationConfirm?t=" + receiveMailDto.getToken();
        SimpleMailMessage email = generateMailMessage(receiveMailDto.getEmail(), subject, message);
        emailSender.send(email);
    }

    public void sendPasswordResetMail(TokenMail mailDto) {
        String subject = "Please reset your password...";
        String message = "To reset your password, please click the link: " + " http://localhost:8080/registerservice/api/resetPassword?t=" + mailDto.getToken();
        SimpleMailMessage email = generateMailMessage(mailDto.getEmail(), subject, message);
        emailSender.send(email);
    }

    private SimpleMailMessage generateMailMessage(String userEmail, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(userEmail);
        email.setSubject(subject);
        email.setText(message);
        return email;
    }
}
