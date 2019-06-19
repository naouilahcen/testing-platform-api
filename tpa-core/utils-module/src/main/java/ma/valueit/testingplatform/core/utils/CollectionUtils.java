package ma.valueit.testingplatform.core.utils;

import java.util.Collection;

/**
 * Created by yelansari on 3/21/18.
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {
    public static boolean containsAll(Collection<?> source, Collection<?> candidates) {
        if (isEmpty(source) || isEmpty(candidates)) {
            return false;
        }
        for (Object candidate : candidates) {
            if (!source.contains(candidate)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return (iterable == null || !iterable.iterator().hasNext());
    }

    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

}
