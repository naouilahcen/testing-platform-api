package ma.valueit.testingplatform.manager.test.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;

public class ExampleDto extends DtoWithID<Integer> {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;
}
