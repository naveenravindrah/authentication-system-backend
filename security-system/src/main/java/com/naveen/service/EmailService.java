package com.naveen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;

    public void sendWelcomeEmail(String toEmail, String name){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Welcome to Secured System By Naveen!");

        mailMessage.setText("Hello " + name + ",\n\n"
                + "Welcome to our Secured Platform! We're excited to have you with us.\n\n"
                + "Thank you for registering. If you need any help, feel free to reach out!\n\n"
                + "Best regards,\n"
                + "Secured System By Naveen Team");

        javaMailSender.send(mailMessage);
    }

    public void sendResetOtpEmail(String toEmail, String name, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        message.setSubject("Password Reset OTP");
        message.setText("Hi " + name + "!\n\n"
                + "We received a request to reset your password. Your one-time password (OTP) is:\n\n"
                + otp + "\n\n"
                + "Use this OTP to proceed with resetting your password. Please note that it will expire in 10 minutes for security reasons.\n\n"
                + "If you did not request this, you can safely ignore this email.\n\n"
                + "Thank you,\n"
                + "Secured System By Naveen Support Team");
        javaMailSender.send(message);
    }

    public void sendOtpEmail(String toEmail, String name, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        message.setSubject("Account Verification OTP");
        message.setText("Hi "+name+"!\n\nYour OTP is : "+otp+". Verify your account using this OTP.\n\nThank you");
        javaMailSender.send(message);
    }

}
