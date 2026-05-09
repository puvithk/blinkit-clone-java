package inventory.exception;

public class InvalidPincode extends RuntimeException {
    public InvalidPincode(String message) {
        super(message);
    }
}
