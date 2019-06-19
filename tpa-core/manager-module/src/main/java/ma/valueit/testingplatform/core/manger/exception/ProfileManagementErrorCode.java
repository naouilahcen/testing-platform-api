package ma.valueit.testingplatform.core.manger.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum ProfileManagementErrorCode implements BusinessErrorCode {
    ALREADY_USED("profiles.profile-already-used"),
    NOT_FOUND("profiles.not-found");

    @Getter
    private final String value;

    private ProfileManagementErrorCode(String value) {
        this.value = value;
    }
}
