package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CustomAbstractListConverter<T1, T2> extends CustomOneWayAbstractListConverter<T1, T2> implements CustomListConverter<T1, T2> {

    /**
     * Convert from a list of businessexception model (dto) to a list of model model (entity)
     *
     * @param source
     *            list of dtos
     * @return target list of entities
     */
    public List<T1> convertFrom(Collection<T2> source) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        List<T1> target = new ArrayList<T1>();

        source.forEach(clazz -> target.add(this.convertFrom(clazz)));

        return target;
    }
}