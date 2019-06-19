package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.UserErrorCode;

import javax.ws.rs.WebApplicationException;

public class UserNotFoundException extends WebApplicationException {
    private static final long serialVersionUID = 5391146990874781853L;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public UserNotFoundException() {
        super();

        this.errorCode = UserErrorCode.NO_USER_FOUND_BY_USERNAME;
    }

    public UserNotFoundException(String message) {
        super(message);

        this.errorCode = UserErrorCode.NO_USER_FOUND_BY_USERNAME;
    }
}
