package auth.dao;

import auth.model.OtpSession;

public interface OtpSessionDao {
    void createOtpSession(OtpSession otpSession);

    OtpSession findBySessionAndOtp(String opt, String sessionId);
}
