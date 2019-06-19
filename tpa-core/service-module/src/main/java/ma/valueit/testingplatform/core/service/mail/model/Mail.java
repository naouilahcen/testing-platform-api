package ma.valueit.testingplatform.core.service.mail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@ToString(exclude = "model")
public class Mail {
    @Getter
    @Setter
    private String from;

    @Getter
    @Setter
    private String to;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    @JsonIgnore
    private Map<String, Object> model;


    public Mail() {
    }

    public Mail(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
}
