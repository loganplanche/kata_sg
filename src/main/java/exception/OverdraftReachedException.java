package exception;

public class OverdraftReachedException extends Throwable {

	private static final long serialVersionUID = 1L;
	
    public OverdraftReachedException() {
        super();
    }

    public OverdraftReachedException(String message) {
        super(message);
    }
}
