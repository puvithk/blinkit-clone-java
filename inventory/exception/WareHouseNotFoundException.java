package inventory.exception;

public class WareHouseNotFoundException extends RuntimeException {
    public WareHouseNotFoundException(String message) {
        super(message);
    }
}
