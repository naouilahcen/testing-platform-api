package ma.valueit.testingplatform.core.manger.mapper.converters;

public  interface CustomConverter<T1, T2> extends CustomOneWayConverter<T1, T2> {

    /**
     * Convert from a businessexception model (dto) to the model model (entity)
     *
     * @param source
     *            dto
     * @return target entity
     */
    public T1 convertFrom(T2 source);
}