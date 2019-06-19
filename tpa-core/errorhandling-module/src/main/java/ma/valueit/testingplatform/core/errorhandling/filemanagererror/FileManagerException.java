package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerException extends RuntimeException{
	private static final long serialVersionUID = 6930122570175261392L;

	public FileManagerException() {
		super();
	}

	public FileManagerException(String message) {
		super(message);
	}

	public FileManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerException(Throwable cause) {
		super(cause);
	}
}

