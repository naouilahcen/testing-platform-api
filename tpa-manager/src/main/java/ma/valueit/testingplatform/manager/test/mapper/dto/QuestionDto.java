package ma.valueit.tpa.manager.test.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.AdvancedBusinessException;
import ma.valueit.testingplatform.core.errorhandling.validator.ValidationMessage;
import ma.valueit.testingplatform.core.manger.exception.CommonManagementErrorCode;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import ma.valueit.testingplatform.core.utils.StringUtils;

public class QuestionDto extends DtoWithID<Integer> {


    @Getter @Setter

    private Integer id ;

    @Getter
    @Setter
    private String enonce;

    @Getter
    @Setter
    private String description;

/*
    @Getter @Setter
    private String response;
*/




    @Override
    public void validate() throws AdvancedBusinessException {
        super.validate();

        if (StringUtils.isEmpty(enonce)) {

            this.validationMessages.add(new ValidationMessage(
                    "enonce",
                    null,
                    "QuestionDto",
                    CommonManagementErrorCode.FIELD_REQUIRED

            ));
        }
        if (StringUtils.isEmpty(description)) {

            this.validationMessages.add(new ValidationMessage(
                    "description",
                    null,
                    "QuestionDto",
                    CommonManagementErrorCode.FIELD_REQUIRED

            ));
        }

        if (!CollectionUtils.isEmpty(this.validationMessages)) {
            throw new AdvancedBusinessException(this.validationMessages, "invalid user test payload");
        }

    }


}
