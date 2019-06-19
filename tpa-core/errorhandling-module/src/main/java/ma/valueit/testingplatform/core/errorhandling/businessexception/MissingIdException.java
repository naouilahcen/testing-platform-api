package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;

public class MissingIdException extends RuntimeException {
    private static final long serialVersionUID = -3377325407569060574L;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public MissingIdException() {
        super();

        this.errorCode = CommonErrorCode.ID_IS_MISSING;
    }

    public MissingIdException(String message) {
        super(message);

        this.errorCode = CommonErrorCode.ID_IS_MISSING;
    }

}
