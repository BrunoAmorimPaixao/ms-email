package com.ms.email.services;

import com.ms.email.enums.StatusEmailEnum;
import com.ms.email.models.Email;
import com.ms.email.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender emailSender;

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e){
            email.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            //futuramente fazer um metodo caso o email tenha dado erro tentar de novo
            return emailRepository.save(email);
        }
    }
}
