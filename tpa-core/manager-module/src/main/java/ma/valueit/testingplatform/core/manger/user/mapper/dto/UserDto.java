package ma.valueit.testingplatform.core.manger.user.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.errorhandling.businessexception.AdvancedBusinessException;
import ma.valueit.testingplatform.core.errorhandling.validator.ValidationMessage;
import ma.valueit.testingplatform.core.manger.exception.CommonManagementErrorCode;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import ma.valueit.testingplatform.core.utils.StringUtils;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserDto extends DtoWithID<Integer> {
    private static final long serialVersionUID = -3059359873780896164L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private boolean enabled;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String mobile;


    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String avatar;

    @Getter
    @Setter
    private Integer profileId;

    @Getter
    @Setter
    private boolean admin;

    @Getter
    @Setter
    private AuthTypeEnum authType;

    @Getter
    @Setter
    private Date lastPasswordResetDate;

    @Getter
    @Setter
    private Set<Integer> excludedAuthoritiesIds = new LinkedHashSet<>();

    @Override
    public void validate() throws AdvancedBusinessException {
        super.validate();

        if (StringUtils.isEmpty(mobile)) {
            this.validationMessages.add(new ValidationMessage(
                    "mobile",
                    firstname,
                    "UserDto",
                    CommonManagementErrorCode.FIELD_REQUIRED
            ));
        }

        if (StringUtils.isEmpty(firstname)) {
            this.validationMessages.add(new ValidationMessage(
                    "firstname",
                    firstname,
                    "UserDto",
                    CommonManagementErrorCode.FIELD_REQUIRED
            ));
        }

        if (StringUtils.isEmpty(lastname)) {
            this.validationMessages.add(new ValidationMessage(
                    "lastname",
                    lastname,
                    "UserDto",
                    CommonManagementErrorCode.FIELD_REQUIRED
            ));
        }

        if (StringUtils.isEmpty(username)) {
            this.validationMessages.add(new ValidationMessage(
                    "username",
                    username,
                    "UserDto",
                    CommonManagementErrorCode.FIELD_REQUIRED
            ));
        }

        if (!CollectionUtils.isEmpty(this.validationMessages)) {
            throw new AdvancedBusinessException(validationMessages);
        }
    }
}
