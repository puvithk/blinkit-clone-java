package auth.dao;

import auth.model.OtpSession;

public interface OtpSessionDao {
    void createOtpSession(OtpSession otpSession);

    OtpSession findBySessionAndOtp(String opt, String sessionId);

    OtpSession findBySessionId(String currentSessionId);

    void updateOtpVerification(OtpSession currentOtpSession);

    void deleteOtpSessionBySessionId(OtpSession otpSession);
}
