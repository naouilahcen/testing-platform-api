package ma.valueit.testingplatform.core.manger.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum AuthenticationActivityManagementErrorCode implements BusinessErrorCode {
    CODE_IS_MISSING("authentication-activities.code-is-missing"),
    INVALID_EMAIL("authentication-activities.invalid-email");

    @Getter
    private final String value;

    private AuthenticationActivityManagementErrorCode(String value) {
        this.value = value;
    }
}
