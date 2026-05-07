package auth.exceptions;

public class InvalidOtp extends RuntimeException {

    private final boolean otpMisMatch;
    public InvalidOtp(String message , boolean otpMisMatch) {
        super(message);
        this.otpMisMatch = otpMisMatch;
    }

    public boolean isOtpMisMatch() {
        return otpMisMatch;
    }
}
