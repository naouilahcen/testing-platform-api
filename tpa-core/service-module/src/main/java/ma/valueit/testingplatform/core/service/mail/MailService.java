package ma.valueit.testingplatform.core.service.mail;

import ma.valueit.testingplatform.core.service.mail.model.Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by yelansari on 2/23/18.
 */
public interface MailService {
    void send(SimpleMailMessage simpleMailMessage);

    void send(MimeMessagePreparator mimeMessagePreparator);

    void send(Mail mail, String templateFileName) throws MessagingException, IOException;
}
