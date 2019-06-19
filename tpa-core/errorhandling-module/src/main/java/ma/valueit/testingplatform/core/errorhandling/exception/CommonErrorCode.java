package ma.valueit.testingplatform.core.errorhandling.exception;

import lombok.Getter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessErrorCode;

public enum CommonErrorCode  implements BusinessErrorCode {
    ENTITY_ALREADY_IN_USE("commons.entity-already-in-use"),
    INVALID_PAYLOAD_REQUEST("commons.invalid-payload-request"),
    ID_IS_MISSING("commons.id-is-missing"),
    FIELD_REQUIRED("commons.field-required"),
    INVALID_NUMBER("commons.invalid-number"),
    NO_CONTENT("commons.no-content"),
    TRYING_TO_EDIT_ANOTHER_ENTITY("commons.trying-to-edit-another-entity"),
    TRYING_TO_DELETE_ANOTHER_ENTITY("commons.trying-to-delete-another-entity"),
    KEYWORD_IS_MISSING("commons.keyword-is-missing"),
    ENTITY_IS_MISSING("commons.entity-is-missing"),
    ENTITY_NOT_FOUND("commons.entity-not-found");

    @Getter
    private final String value;

    private CommonErrorCode(String value) {
        this.value = value;
    }
}
