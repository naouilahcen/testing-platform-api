package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import java.util.Collection;
import java.util.List;

/**
 * Created by yelansari on 1/28/18.
 */
public interface ListConverter<ENTITY extends CustomAuditable, DTO> extends OneWayListConverter<ENTITY, DTO>, Converter<ENTITY, DTO> {
    /**
     * Convert from a businessexception model (dto) list to the model model (entity) list
     *
     * @param source
     *            list of dtos
     * @return target list of entities
     */
    public List<ENTITY> convertFrom(Collection<DTO> source);
}
