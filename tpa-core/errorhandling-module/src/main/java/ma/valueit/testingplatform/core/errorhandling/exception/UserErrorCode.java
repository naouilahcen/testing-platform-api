package ma.valueit.testingplatform.core.errorhandling.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

public enum UserErrorCode implements BusinessErrorCode {
    UNAUTHORIZED("users.unauthorized"),
    PROFILE_IS_MISSING("users.profile-is-missing"),
    NO_USER_FOUND_BY_USERNAME("users.no-user-found-by-username"),
    USERNAME_IS_MISSING("users.username-is-missing"),
    CODE_IS_MISSING("users.code-is-missing"),
    EMAIL_IS_MISSING("users.email-is-missing"),
    USERNAME_OR_PASSWORD_IS_INCORRECT("users.username-or-password-is-incorrect"),
    YOUR_ACCOUNT_IS_DISABLED("users.your-account-is-disabled"),
    RESET_TOKEN_IS_MISSING("users.reset-token-is-missing");

    @Getter
    private final String value;

    private UserErrorCode(String value) {
        this.value = value;
    }
}
