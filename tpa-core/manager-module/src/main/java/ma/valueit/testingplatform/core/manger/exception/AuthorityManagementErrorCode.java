package ma.valueit.testingplatform.core.manger.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum AuthorityManagementErrorCode implements BusinessErrorCode {
    AUTHORITY_NOT_FOUND("authorities.not-found");

    @Getter
    private final String value;

    private AuthorityManagementErrorCode(String value) {
        this.value = value;
    }
}
