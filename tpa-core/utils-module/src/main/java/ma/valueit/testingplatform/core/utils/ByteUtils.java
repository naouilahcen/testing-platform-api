package ma.valueit.testingplatform.core.utils;

import org.apache.sis.internal.jdk7.StandardCharsets;

/**
 * Created by yelansari on 8/24/17.
 */
public class ByteUtils {
    public static String toString(Byte[] byteObjects) {
        if(byteObjects == null || byteObjects.length == 0) {
            return null;
        }

        byte[] bytes = toByte(byteObjects) ;

        return toString(bytes);
    }

    public static String toString(byte[] bytes) {
        if(bytes == null || bytes.length == 0) {
            return null;
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static Byte[] toByte(String str) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        return toByte(bytes);
    }

    public static byte[] toByte(Byte[] byteObjects) {
        if(byteObjects == null || byteObjects.length == 0) {
            return null;
        }

        byte[] bytes = new byte[byteObjects.length];

        int i = 0;

        for(Byte b : byteObjects) {
            bytes[i++] = b;
        }

        return bytes;
    }

    public static Byte[] toByte(byte[] bytes) {
        if(bytes == null || bytes.length == 0) {
            return null;
        }

        Byte[] byteObjects = new Byte[bytes.length];

        int i = 0;

        for(byte b : bytes) {
            byteObjects[i++] = b;
        }

        return byteObjects;
    }
}
