package ma.valueit.testingplatform.core.manger.user.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by valueit-mac-yelansari on 1/28/18.
 */
public class UserFilterFieldsDto implements Serializable {
    private static final long serialVersionUID = 6108974467666419649L;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Integer profile;

    @Getter
    @Setter
    private Date createdDate;

    @Getter
    @Setter
    private Boolean enabled;
}
