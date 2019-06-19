package ma.valueit.testingplatform.core.manger.user.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;

import java.util.Date;

/**
 * Created by valueit-mac-yelansari on 4/2/18.
 */
public class UserListItemDto extends DtoWithID<Integer> {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String firstname;

    @Getter @Setter
    private String lastname;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private boolean admin;

    @Getter @Setter
    private boolean enabled;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String service;

    @Getter @Setter
    private String profile;
}
