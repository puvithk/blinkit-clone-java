package auth.model;

import java.time.LocalDateTime;

public class OptSession {
    // Opt Session Id
    private Integer id ;

    // One time password
    private String opt ;

    // Session Id for different sessions
    private String sessionId ;

    // Opt expiring
    private LocalDateTime expireBy;

    // Opt Status
    private boolean verified = false;

    // Mapping to user
    private Integer userId ;

    public OptSession(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getExpireBy() {
        return expireBy;
    }

    public void setExpireBy(LocalDateTime expireBy) {
        this.expireBy = expireBy;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
