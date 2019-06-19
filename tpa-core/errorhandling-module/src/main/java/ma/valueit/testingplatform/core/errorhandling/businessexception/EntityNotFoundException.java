package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;

import javax.ws.rs.WebApplicationException;

public class EntityNotFoundException extends WebApplicationException {
    private static final long serialVersionUID = 5391146990874781853L;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public EntityNotFoundException() {
        super();

        this.errorCode = CommonErrorCode.ENTITY_NOT_FOUND;
    }

    public EntityNotFoundException(String message) {
        super(message);

        this.errorCode = CommonErrorCode.ENTITY_NOT_FOUND;
    }
}
