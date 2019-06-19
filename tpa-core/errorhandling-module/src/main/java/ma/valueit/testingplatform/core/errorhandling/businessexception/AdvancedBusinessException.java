package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.validator.ValidationMessages;
import org.springframework.http.HttpStatus;

public class AdvancedBusinessException extends BusinessException {
    private static final long serialVersionUID = -3377325407569060574L;

    @Getter
    @Setter
    private HttpStatus status;

    @Getter
    @Setter
    private ValidationMessages validationMessages;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public AdvancedBusinessException(ValidationMessages validationMessages) {
        super("Invalid payload");
        this.setValidationMessages(validationMessages);
    }

    public AdvancedBusinessException(ValidationMessages validationMessages, String message) {
        super(message);
        this.setValidationMessages(validationMessages);
    }

    public AdvancedBusinessException(ValidationMessages validationMessages, BusinessErrorCode errorCode) {
        super(errorCode.getValue());
        this.setValidationMessages(validationMessages);
    }
}