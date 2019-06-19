package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.UserErrorCode;

import javax.ws.rs.WebApplicationException;

public class UnAuthorizedException extends WebApplicationException {
    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public UnAuthorizedException() {
        super();

        this.errorCode = UserErrorCode.UNAUTHORIZED;
    }

    public UnAuthorizedException(String message) {
        super(message);

        this.errorCode = UserErrorCode.UNAUTHORIZED;
    }

    public UnAuthorizedException(BusinessErrorCode errorCode) {
        super(errorCode.getValue());

        this.errorCode = errorCode;
    }

}