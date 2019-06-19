package ma.valueit.testingplatform.core.manger.mapper.converters;

import java.util.Collection;
import java.util.List;

public interface CustomOneWayListConverter<T1, T2> extends CustomOneWayConverter<T1, T2> {

    /**
     * Convert from a model model (entity) list to the businessexception model (dto) list
     *
     * @param source
     *            list of entities
     * @return target list of dtos
     */
    public List<T2> convertTo(Collection<T1> source);
}
