package giybat.uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {

    @Value( "${spring.mail.username}")
    private String fromAccount;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationEmail(String email, String verificationCode) {
        String subject = "Registration Email";
        String body = "Your registration link: http://localhost:8080/auth/registration/verification/" + verificationCode;
        sendEmail(email, subject, body);
    }

    private void sendEmail(String email, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAccount);
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);

    }
}
