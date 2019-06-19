package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerUploadException extends FileManagerException {
	private static final long serialVersionUID = -830464371611210134L;

	public FileManagerUploadException() {
		super();
	}

	public FileManagerUploadException(String message) {
		super(message);
	}

	public FileManagerUploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerUploadException(Throwable cause) {
		super(cause);
	}

}
