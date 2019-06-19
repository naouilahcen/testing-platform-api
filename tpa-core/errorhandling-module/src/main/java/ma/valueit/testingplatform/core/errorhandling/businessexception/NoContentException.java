package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;

import javax.ws.rs.WebApplicationException;

public class NoContentException extends WebApplicationException {
    private static final long serialVersionUID = 5391146990874781853L;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;
    public NoContentException() {
        super();

        this.errorCode = CommonErrorCode.NO_CONTENT;
    }

    public NoContentException(String message) {
        super(message);

        this.errorCode = CommonErrorCode.NO_CONTENT;
    }

    public NoContentException(Throwable cause) {
        super(cause);
    }

    public NoContentException(BusinessErrorCode errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public NoContentException(BusinessErrorCode errorCode) {
        super(errorCode.getValue());

        this.errorCode = errorCode;
    }
}
