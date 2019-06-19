package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerUnallowedException extends FileManagerException {
    private static final long serialVersionUID = 4199024126003281207L;

    private static final String DEFAULT_MESSAGE = "not allowed";

    public FileManagerUnallowedException() {
        super(DEFAULT_MESSAGE);
    }

    public FileManagerUnallowedException(String message) {
        super(message);
    }

    public FileManagerUnallowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileManagerUnallowedException(Throwable cause) {
        super(cause);
    }

}
