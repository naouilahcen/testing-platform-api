package ma.valueit.testingplatform.core.manger.mapper.converters;

import com.google.common.base.Enums;
import ma.valueit.testingplatform.core.manger.mapper.dto.FilterSortDto;
import ma.valueit.testingplatform.core.manger.mapper.dto.OrderDto;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yelkarkouri-valueit on 21/12/18
 */
@Component
public class FilterSortConverter extends CustomAbstractListConverter<FilterSortDto, Sort> {

    private Sort defaultSort = Sort.by(
            new Sort.Order(Sort.Direction.DESC, "lastModifiedDate").ignoreCase(),
            new Sort.Order(Sort.Direction.DESC, "createdDate").ignoreCase()
    );

    @Override
    public Sort convertTo(FilterSortDto source) {
        Sort target;
        if (source == null || source.getSort() == null || CollectionUtils.isEmpty(source.getSort().getOrders())) {
            target = defaultSort;
        } else if (source.getSort() != null && !CollectionUtils.isEmpty(source.getSort().getOrders())) {
            List<Sort.Order> orders = new ArrayList<>();
            for (OrderDto orderDto : source.getSort().getOrders()) {
                if (Enums.getIfPresent(Sort.Direction.class, orderDto.getDirection()).isPresent()) {
                    Sort.Order order = new Sort.Order(Sort.Direction.valueOf(orderDto.getDirection()), orderDto.getProperty());
                    if (orderDto.isIgnoreCase()) {
                        order = order.ignoreCase();
                    }
                    orders.add(order);
                }
            }

            if (!CollectionUtils.isEmpty(source.getSort().getOrders())) {
                target = Sort.by(orders);
            } else {
                target = defaultSort;
            }
        } else {
            target = defaultSort;
        }

        return target;
    }

    @Override
    public FilterSortDto convertFrom(Sort source) {
        return null;
    }
}

