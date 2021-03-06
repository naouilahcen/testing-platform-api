package ma.valueit.testingplatform.core.manger.user.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;

public class AuthorityCategoryDto extends DtoWithID<Integer> {
    private static final long serialVersionUID = 8709267987108254348L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;
}
