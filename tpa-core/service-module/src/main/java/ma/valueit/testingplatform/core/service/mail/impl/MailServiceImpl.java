package ma.valueit.testingplatform.core.service.mail.impl;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessException;
import ma.valueit.testingplatform.core.service.exception.MailBusinessErrorCode;
import ma.valueit.testingplatform.core.service.mail.MailService;
import ma.valueit.testingplatform.core.service.mail.model.Mail;
import ma.valueit.testingplatform.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by yelansari on 2/23/18.
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailSenderThread mailSenderThread;

    @Override
    public void send(SimpleMailMessage simpleMailMessage) {
        if (simpleMailMessage == null) {
            throw new BusinessException(MailBusinessErrorCode.MAIL_MESSAGE_IS_MISSING);
        }

        try {
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
        }
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) {
        if (mimeMessagePreparator == null) {
            throw new BusinessException(MailBusinessErrorCode.MAIL_MESSAGE_IS_MISSING);
        }

        try {
            mailSender.send(mimeMessagePreparator);
        } catch (MailException e) {
            throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
        }
    }

    @Override
    public void send(Mail mail, String templateFileName) throws MessagingException, IOException {
        mailSenderThread.setMail(mail);

        mailSenderThread.setTemplateFileName(templateFileName);

        mailSenderThread.run();
    }

    @Component
    public class MailSenderThread extends Thread {

        @Getter
        @Setter
        private Mail mail;

        @Getter
        @Setter
        private String templateFileName;

        private Context context = new Context();

        @Autowired
        private JavaMailSender mailSender;

        public void run() {
            try {
                MimeMessage message = mailSender.createMimeMessage();

                Context context = new Context();
                context.setVariables(mail.getModel());
                String html = templateEngine.process(templateFileName, context);

                MimeMessageHelper helper = new MimeMessageHelper(
                        message,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name()
                );

                helper.setTo(mail.getTo());
                helper.setText(html, true);
                helper.setSubject(mail.getSubject());

                if (!StringUtils.isEmpty(mail.getFrom())) {
                    helper.setFrom(mail.getFrom());
                }
                mailSender.send(message);
            } catch (MailException | MessagingException e) {
                throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
            }
        }
    }
}
