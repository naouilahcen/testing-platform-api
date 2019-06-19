package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.ws.rs.WebApplicationException;

public class BusinessException extends WebApplicationException {
    private static final long serialVersionUID = -3377325407569060574L;

    @Getter
    @Setter
    private HttpStatus status;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(BusinessErrorCode errorCode, String message) {
        super(message);

        this.setErrorCode(errorCode);
    }

    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getValue());

        this.setErrorCode(errorCode);
    }
}
