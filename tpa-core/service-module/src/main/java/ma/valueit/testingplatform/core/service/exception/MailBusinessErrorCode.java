package ma.valueit.testingplatform.core.service.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum MailBusinessErrorCode implements BusinessErrorCode {
    AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL("mails.an-error-occurred-while-sending-the-mail"),
    MAIL_MESSAGE_IS_MISSING("mails.mail-message-is-missing");

    @Getter
    private final String value;

    private MailBusinessErrorCode(String value) {
        this.value = value;
    }
}
