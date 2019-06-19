package ma.valueit.testingplatform.core.manger.mapper.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yelkarkouri-valueit on 21/12/18
 */
public class OrderDto {
    @Getter
    @Setter
    private String direction;

    @Getter
    @Setter
    private String property;

    @Getter
    @Setter
    private boolean ignoreCase;
}