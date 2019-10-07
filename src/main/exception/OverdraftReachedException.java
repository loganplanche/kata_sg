package exception;

public class OverdraftReachedException extends Throwable {

    public OverdraftReachedException() {
        super();
    }

    public OverdraftReachedException(String message) {
        super(message);
    }
}
