package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.core.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yelansari on 1/28/18.
 */
public abstract class AbstractListConverter<ENTITY extends CustomAuditable, DTO> extends OneWayAbstractListConverter<ENTITY, DTO> implements ListConverter<ENTITY, DTO> {

    /**
     * Convert from a list of businessexception model (dto) to a list of model model (entity)
     *
     * @param source
     *            list of dtos
     * @return target list of entities
     */
    public List<ENTITY> convertFrom(Collection<DTO> source) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        List<ENTITY> target = new ArrayList<ENTITY>();

        source.forEach(clazz -> target.add(this.convertFrom(clazz)));

        return target;
    }
}
