package auth.exceptions;

public class OtpSessionAlreadyPresent extends RuntimeException {
    public OtpSessionAlreadyPresent(String message) {
        super(message);
    }
}
