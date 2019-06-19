package ma.valueit.testingplatform.core.manger.mapper.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yelansari on 3/12/18.
 */
public class IdValuePairDto extends DtoWithID<Integer> {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String value;

    public IdValuePairDto() {
    }

    public IdValuePairDto(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
