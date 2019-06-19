package ma.valueit.testingplatform.core.manger.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.AdvancedBusinessException;
import ma.valueit.testingplatform.core.errorhandling.validator.ValidationMessages;
import ma.valueit.testingplatform.core.errorhandling.validator.Validator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class DtoWithID<ID extends Serializable> implements Serializable, Validator {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private ID id;

    @Getter
    @Setter
    private LocalDate createdDate;

    @Getter
    @Setter
    private LocalDate lastModifiedDate;

    @Getter
    @Setter
    private String createdBy;

    @Getter
    @Setter
    private String lastModifiedBy;

    @Getter
    @JsonIgnore
    protected ValidationMessages validationMessages = new ValidationMessages();

    @Override
    public void validate() throws AdvancedBusinessException {

    }
}
