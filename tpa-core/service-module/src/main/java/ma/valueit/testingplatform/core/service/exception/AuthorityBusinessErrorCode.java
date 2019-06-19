package ma.valueit.testingplatform.core.service.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

/**
 * Created by yelansari on 3/6/18.
 */
public enum AuthorityBusinessErrorCode implements BusinessErrorCode {
    AUTHORITY_ID_IS_MISSING("authorities.id-is-missing"),
    CATEGORY_ID_IS_MISSING("authorities.category-is-missing");

    @Getter
    private final String value;

    private AuthorityBusinessErrorCode(String value) {
        this.value = value;
    }
}
