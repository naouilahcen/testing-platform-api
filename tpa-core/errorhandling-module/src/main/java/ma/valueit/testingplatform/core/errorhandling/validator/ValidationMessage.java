package ma.valueit.testingplatform.core.errorhandling.validator;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

import java.io.Serializable;

/**
 * Created by yelansari on 3/12/18.
 */
public class ValidationMessage implements Serializable {
    private static final long serialVersionUID = 3842164250372125789L;

    @Getter
    @Setter
    private String field;

    @Getter
    @Setter
    private String rejectedValue;

    @Getter
    @Setter
    private String objectName;

    @Getter
    @Setter
    private BusinessErrorCode errorCode;

    public ValidationMessage() {
    }

    public ValidationMessage(String field, String rejectedValue, String objectName, BusinessErrorCode errorCode) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.objectName = objectName;
        this.errorCode = errorCode;
    }
}
