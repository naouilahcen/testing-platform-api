package ma.valueit.testingplatform.core.manger.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

public enum UserManagementErrorCode implements BusinessErrorCode {
    AUTH_TYPE_MISSING("users.auth-type-missing"),
    EMAIL_MISSING("users.email-missing"),
    FIRSTNAME_IS_MISSING("users.firstname-is-missing"),
    LASTNAME_IS_MISSING("users.lastname-is-missing"),
    DUPLICATE_USERNAME("users.duplicate-username"),
    DUPLICATE_EMAIL("users.duplicate-email"),
    PASSWORD_IS_MISSING("users.password-is-missing"),
    ALREADY_DISABLED("users.already-disabled"),
    ALREADY_ENABLED("users.already-enabled"),
    NOT_FOUND("users.not-found"),
    INVALID_RESET_CODE("users.invalid-reset-code"),
    EXPIRED_TOKEN_ERROR("users.expired-token"),
    ALREADY_USED("users.already-in-use"),
    PROTECTED_ACCOUNT("users.protected-account"),
    PERMISSION_NOT_GRANTED("users.permission-not-granted");

    @Getter
    private String value;

    private UserManagementErrorCode(String value) {
        this.value = value;
    }
}
