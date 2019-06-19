package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;

public class IntegrityConstraintViolationException extends RuntimeException {
    private static final long serialVersionUID = -3377325407569060574L;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public IntegrityConstraintViolationException() {
        super();

        this.errorCode = CommonErrorCode.ENTITY_ALREADY_IN_USE;
    }

    public IntegrityConstraintViolationException(String message) {
        super(message);

        this.errorCode = CommonErrorCode.ENTITY_ALREADY_IN_USE;
    }

}
