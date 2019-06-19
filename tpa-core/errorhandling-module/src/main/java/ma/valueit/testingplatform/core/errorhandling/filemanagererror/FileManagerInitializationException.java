package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerInitializationException extends RuntimeException {
	private static final long serialVersionUID = 3841185931455230944L;

	public FileManagerInitializationException() {
		super();
	}

	public FileManagerInitializationException(String message) {
		super(message);
	}

	public FileManagerInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerInitializationException(Throwable cause) {
		super(cause);
	}

}
