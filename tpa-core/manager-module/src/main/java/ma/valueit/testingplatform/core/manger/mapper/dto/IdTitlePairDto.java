package ma.valueit.testingplatform.core.manger.mapper.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yelansari on 3/12/18.
 */
public class IdTitlePairDto extends DtoWithID<Integer> {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    public IdTitlePairDto() {
    }

    public IdTitlePairDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
