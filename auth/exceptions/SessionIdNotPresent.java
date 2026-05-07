package auth.exceptions;

public class SessionIdNotPresent extends RuntimeException {
    public SessionIdNotPresent(String message) {
        super(message);
    }
}
