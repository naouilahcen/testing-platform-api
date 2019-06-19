package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerConfigException extends FileManagerException {
	private static final long serialVersionUID = 3353184611612119883L;

	public FileManagerConfigException() {
		super();
	}

	public FileManagerConfigException(String message) {
		super(message);
	}

	public FileManagerConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerConfigException(Throwable cause) {
		super(cause);
	}

}
