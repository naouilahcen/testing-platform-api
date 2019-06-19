package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

/**
 * Created by yelansari on 1/28/18.
 */
public  interface Converter<ENTITY extends CustomAuditable, DTO> extends OneWayConverter<ENTITY, DTO> {

    /**
     * Convert from a businessexception model (dto) to the model model (entity)
     *
     * @param source
     *            dto
     * @return target entity
     */
    public ENTITY convertFrom(DTO source);
}
