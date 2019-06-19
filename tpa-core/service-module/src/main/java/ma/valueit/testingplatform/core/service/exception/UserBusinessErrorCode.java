package ma.valueit.testingplatform.core.service.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum UserBusinessErrorCode implements BusinessErrorCode {
    USER_ID_IS_MISSING("users.id-is-missing"),
    USERNAME_IS_MISSING("users.username-is-missing"),
    NO_USER_FOUND_BY_USERNAME("users.no-user-found-by-username"),
    PROFILE_IS_MISSING("users.profile-is-missing");

    @Getter
    private final String value;

    private UserBusinessErrorCode(String value) {
        this.value = value;
    }
}
