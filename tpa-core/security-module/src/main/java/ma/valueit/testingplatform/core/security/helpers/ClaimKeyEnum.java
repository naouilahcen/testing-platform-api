package ma.valueit.testingplatform.core.security.helpers;

/**
 * Created by yelansari on 1/28/18.
 */
public enum ClaimKeyEnum {
    CLAIM_KEY_USERID("id"),
    CLAIM_KEY_USERNAME("username"),
    CLAIM_KEY_CREATED("created"),
    CLAIM_KEY_NAME("name"),
    CLAIM_KEY_ADMIN("admin"),
    CLAIM_KEY_AUTH_TYPE("auttype"),
    CLAIM_KEY_EMAIL("email");

    private final String value;


    private ClaimKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
