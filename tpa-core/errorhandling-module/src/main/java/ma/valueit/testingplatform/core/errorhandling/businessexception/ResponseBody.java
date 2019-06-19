package ma.valueit.testingplatform.core.errorhandling.businessexception;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

/**
 * Created by yelansari on 3/4/18.
 */
public class ResponseBody<T> {
    @Getter
    @Setter
    private Locale language = Locale.ENGLISH;

    @Getter
    @Setter
    private T data = null;

    @Getter
    @Setter
    private String message = null;

    public ResponseBody() {
    }

    public ResponseBody(T data) {
        this.data = data;
    }

    public ResponseBody(Locale language, T data) {
        this.language = language;
        this.data = data;
    }

    public ResponseBody(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public ResponseBody(Locale language, T data, String message) {
        this.language = language;
        this.data = data;
        this.message = message;
    }
}
