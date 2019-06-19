package ma.valueit.testingplatform.core.errorhandling.filemanagererror;

/**
 * Created by yelansari on 3/12/18.
 */
public class FileManagerFileNotFoundException extends FileManagerException {
	private static final long serialVersionUID = -5190916378693008743L;

	public FileManagerFileNotFoundException() {
		super();
	}

	public FileManagerFileNotFoundException(String message) {
		super(message);
	}

	public FileManagerFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerFileNotFoundException(Throwable cause) {
		super(cause);
	}

}
