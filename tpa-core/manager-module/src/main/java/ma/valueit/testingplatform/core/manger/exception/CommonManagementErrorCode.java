package ma.valueit.testingplatform.core.manger.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum CommonManagementErrorCode implements BusinessErrorCode {
    ID_IS_MISSING("commons.id-is-missing"),
    INVALID_FORMAT("commons.invalid-format"),
    INVALID_DATE("commons.invalid-date"),
    FIELD_REQUIRED("commons.field-required"),
    INVALID_FIELD_DATA("commons.invalid-data");

    @Getter
    private final String value;

    private CommonManagementErrorCode(String value) {
        this.value = value;
    }

}
