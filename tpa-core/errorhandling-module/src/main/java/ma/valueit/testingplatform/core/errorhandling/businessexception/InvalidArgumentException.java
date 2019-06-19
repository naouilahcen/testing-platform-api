package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yelansari on 3/23/18.
 */
public class InvalidArgumentException extends RuntimeException {
    private static final long serialVersionUID = 7830064295968918115L;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public InvalidArgumentException(BusinessErrorCode errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public InvalidArgumentException(BusinessErrorCode errorCode) {
        super(errorCode.getValue());

        this.errorCode = errorCode;
    }
}
