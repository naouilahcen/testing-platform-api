package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerSyntaxException extends FileManagerException {
	private static final long serialVersionUID = 8209677270758886311L;

	public FileManagerSyntaxException() {
		super();
	}

	public FileManagerSyntaxException(String message) {
		super(message);
	}

	public FileManagerSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerSyntaxException(Throwable cause) {
		super(cause);
	}

}
