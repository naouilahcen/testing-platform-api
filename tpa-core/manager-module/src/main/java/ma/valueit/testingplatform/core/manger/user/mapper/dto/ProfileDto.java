package ma.valueit.testingplatform.core.manger.user.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import ma.valueit.testingplatform.core.errorhandling.businessexception.AdvancedBusinessException;
import ma.valueit.testingplatform.core.errorhandling.validator.ValidationMessage;
import ma.valueit.testingplatform.core.manger.exception.CommonManagementErrorCode;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.core.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfileDto extends DtoWithID<Integer> {
    private static final long serialVersionUID = 8709267987108254348L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private List<AuthorityDto> authorities = new ArrayList<>();


    @Override
    public void validate() throws AdvancedBusinessException {
        super.validate();

        if(title == null){
            this.validationMessages.add(new ValidationMessage(
                    "title",
                    null,
                    "ProfileDto",
                    CommonManagementErrorCode.FIELD_REQUIRED
            ));
        }

        if(CollectionUtils.isEmpty(authorities)){
            this.validationMessages.add(new ValidationMessage(
                    "authorities",
                    null,
                    "ProfileDto",
                    CommonManagementErrorCode.FIELD_REQUIRED
            ));
        }

        if (!CollectionUtils.isEmpty(this.validationMessages)) {
            throw new AdvancedBusinessException(validationMessages);
        }
    }
}
