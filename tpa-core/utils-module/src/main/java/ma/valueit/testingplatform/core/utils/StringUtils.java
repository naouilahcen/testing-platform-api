package ma.valueit.testingplatform.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class StringUtils extends org.springframework.util.StringUtils {
    private static String emailRegEx ;

    @Value("${regex.email}")
    private void setEmailRegEx(String regEx){
        emailRegEx = regEx;
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str) || str.toString().trim().length() == 0);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || "".contentEquals(cs) || cs.toString().trim().length() == 0;
    }

    public static String normalize(String string) {
        return normalize(string, true);
    }

    public static String normalize(String string, boolean removeSpace) {
        String result = string.replaceAll("[^a-zA-Z0-9 /._-]+", "").replaceAll(" {2}", " ").replaceAll("//", "/").trim();

        if (removeSpace) {
            result = result.replace(" ", "_");
        }

        return result;
    }

    public static boolean isValidEmail(String emailStr) {
        Pattern emailPattern = Pattern.compile(emailRegEx, Pattern.CASE_INSENSITIVE);

        Matcher matcher = emailPattern.matcher(emailStr);

        return matcher.find();
    }
}