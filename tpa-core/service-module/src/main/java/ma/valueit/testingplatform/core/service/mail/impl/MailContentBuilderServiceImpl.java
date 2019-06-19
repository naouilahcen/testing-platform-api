package ma.valueit.testingplatform.core.service.mail.impl;

import ma.valueit.testingplatform.core.service.mail.MailContentBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by yelansari on 2/23/18.
 */
@Service
public class MailContentBuilderServiceImpl implements MailContentBuilderService {
    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilderServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
