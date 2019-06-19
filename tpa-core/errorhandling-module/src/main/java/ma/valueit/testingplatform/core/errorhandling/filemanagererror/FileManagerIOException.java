package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerIOException extends FileManagerException {
	private static final long serialVersionUID = -3092780409132734482L;

	public FileManagerIOException() {
		super();
	}

	public FileManagerIOException(String message) {
		super(message);
	}

	public FileManagerIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerIOException(Throwable cause) {
		super(cause);
	}

}
