package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import java.util.Collection;
import java.util.List;

/**
 * Created by yelansari on 1/28/18.
 */
public interface OneWayListConverter<ENTITY extends CustomAuditable, DTO> extends OneWayConverter<ENTITY, DTO> {

    /**
     * Convert from a model model (entity) list to the businessexception model (dto) list
     *
     * @param source
     *            list of entities
     * @return target list of dtos
     */
    public List<DTO> convertTo(Collection<ENTITY> source);

    public List<Integer> convertToIds(Collection<ENTITY> source);
}
