package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CustomOneWayAbstractListConverter<T1, T2> implements CustomOneWayListConverter<T1, T2> {

    /**
     * Convert from a model model (entity) list to the businessexception model (dto) list
     *
     * @param source
     *            list of entities
     * @return target list of dtos
     */
    public List<T2> convertTo(Collection<T1> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<T2>();
        }

        List<T2> target = new ArrayList<T2>();

        source.forEach(clazz -> target.add(this.convertTo(clazz)));
        return target;

    }
}