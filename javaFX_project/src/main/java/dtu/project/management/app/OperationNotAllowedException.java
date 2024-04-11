package dtu.project.management.app;

public class OperationNotAllowedException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationNotAllowedException(String errorMessage) {
        super(errorMessage);
    }

} 