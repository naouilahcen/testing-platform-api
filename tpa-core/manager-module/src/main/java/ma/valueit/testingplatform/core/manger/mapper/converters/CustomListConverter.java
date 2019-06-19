package ma.valueit.testingplatform.core.manger.mapper.converters;

import java.util.Collection;
import java.util.List;

public interface CustomListConverter<T1, T2> extends CustomOneWayListConverter<T1, T2>, CustomConverter<T1, T2> {
    /**
     * Convert from a businessexception model (dto) list to the model model (entity) list
     *
     * @param source
     *            list of dtos
     * @return target list of entities
     */
    public List<T1> convertFrom(Collection<T2> source);
}