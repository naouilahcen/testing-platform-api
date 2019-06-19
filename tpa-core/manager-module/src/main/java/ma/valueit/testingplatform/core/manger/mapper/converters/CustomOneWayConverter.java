package ma.valueit.testingplatform.core.manger.mapper.converters;

public interface CustomOneWayConverter<T1, T2> {

    /**
     * Convert from a model model (entity) to the businessexception model (dto)
     *
     * @param source
     *            entity
     * @return target dto
     */
    public T2 convertTo(T1 source);
}