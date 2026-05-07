package auth.dao.impl;

import auth.dao.OtpSessionDao;
import auth.model.OtpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryOtpSessionDao implements OtpSessionDao {
    List<OtpSession> otpSessions = new ArrayList<>(List.of());

    @Override
    public void createOtpSession(OtpSession otpSession) {
        int index ;
        // Check is the list is empty
        if(otpSessions.isEmpty()){
            index = 0;
        }else {
            index = otpSessions.getLast().getId()+1;
        }
        // Get the last index

        // Update the id
        otpSession.setId(index);

        // add new session into the list
        otpSessions.addLast(otpSession);
    }

    @Override
    public OtpSession findBySessionAndOtp(String opt, String sessionId) {
        return otpSessions.stream().filter(
                otpSession -> otpSession.getSessionId().equals(sessionId)
                && otpSession.getOpt().equals(opt)
        ).findFirst().orElse(null);
    }
}
