package ma.valueit.testingplatform.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by yelansari on 8/29/17.
 */
public class NumberUtils {
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static int random(int length) {
        int rand = 0;

        for (int i = 0; i < length; i++) {
            rand = rand * 10 ^ i + randBetween(1, 9);
        }

        return rand;
    }

    public static boolean isNullOrNegative(Integer number) {
        return (number == null || number < 0);
    }

    public static boolean isNullOrNegative(Double number) {
        return (number == null || number < 0);
    }

    public static boolean isPositiveStrict(Double number) {
        return (number != null && number > 0);
    }

    public static boolean isNegative(Double number) {
        return number != null && number < 0;
    }

    public static boolean isPositive(Double number) {
        return !isNullOrNegative(number);
    }

    public static boolean isNull(Double number) {
        return number == null;
    }

    public static String toDecimalFormat(Double number) {
        NumberFormat formatter = new DecimalFormat("#0.00");

        return formatter.format(number);
    }

    public static boolean isNumeric(String str) {
        return StringUtils.isNumeric(str);
    }
}
