package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidPayloadException extends RuntimeException {
    private static final long serialVersionUID = -3377325407569060574L;

    @Getter
    @Setter
    private HttpStatus status;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public InvalidPayloadException() {
        super();

        this.errorCode = CommonErrorCode.INVALID_PAYLOAD_REQUEST;
    }

    public InvalidPayloadException(String message) {
        super(message);

        this.errorCode = CommonErrorCode.INVALID_PAYLOAD_REQUEST;
    }

}
