package ma.valueit.testingplatform.core.manger.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by valueit-mac-yelansari on 1/28/18.
 */
public class FilterSortDto<T> implements Serializable {
    private static final long serialVersionUID = 6108974467666419649L;

    @Getter
    @Setter
    private SortDto sort;

    @Getter
    @Setter
    private T filter;
}
