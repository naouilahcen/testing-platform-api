package ma.valueit.testingplatform.core.service.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum ProfileBusinessErrorCode implements BusinessErrorCode {
    PROFILE_ID_IS_MISSING("profiles.id-is-missing");

    @Getter
    private final String value;

    private ProfileBusinessErrorCode(String value) {
        this.value = value;
    }
}
