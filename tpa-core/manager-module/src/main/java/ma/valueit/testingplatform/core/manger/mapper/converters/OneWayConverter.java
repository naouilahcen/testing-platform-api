package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

/**
 * Created by yelansari on 1/28/18.
 */
public interface OneWayConverter<ENTITY extends CustomAuditable, DTO> {

    /**
     * Convert from a model model (entity) to the businessexception model (dto)
     *
     * @param source
     *            entity
     * @return target dto
     */
    public DTO convertTo(ENTITY source);
}
