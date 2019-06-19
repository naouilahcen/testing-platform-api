package ma.valueit.testingplatform.core.manger.mapper.converters;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.core.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yelansari on 1/28/18.
 */
public abstract class OneWayAbstractListConverter<ENTITY extends CustomAuditable, DTO> implements OneWayListConverter<ENTITY, DTO> {

    /**
     * Convert from a model model (entity) list to the businessexception model (dto) list
     *
     * @param source
     *            list of entities
     * @return target list of dtos
     */
    public List<DTO> convertTo(Collection<ENTITY> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<DTO>();
        }
        List<DTO> target = new ArrayList<DTO>();

        source.forEach(clazz -> target.add(this.convertTo(clazz)));
        return target;

    }

    public List<DTO> subEntityTo(Collection<? extends ENTITY> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<DTO>();
        }
        List<DTO> target = new ArrayList<DTO>();

        source.forEach(clazz -> target.add(this.convertTo(clazz)));
        return target;

    }

    public List<Integer> convertToIds(Collection<ENTITY> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }

        List<Integer> target = new ArrayList<>();

        for (CustomAuditable entity: source) {
            target.add((Integer) entity.getId());
        }

        return target;
    }
}
