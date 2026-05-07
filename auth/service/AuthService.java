package auth.service;

import auth.dao.OtpSessionDao;
import auth.dao.impl.InMemoryOtpSessionDao;
import auth.dto.AuthResponseDto;
import auth.dto.OtpRequestDto;
import auth.dto.OtpVerificationRequestDto;
import auth.exceptions.InvalidPhoneNumber;
import auth.exceptions.OtpSessionAlreadyPresent;
import auth.model.OtpSession;
import common.exception.ServerError;
import common.response.CustomResponse;
import user.dao.InMemoryUserDaoImpl;
import user.dao.UserDao;
import user.exceptions.UserNotFound;
import user.model.User;
import user.service.UserService;

import java.time.LocalDateTime;
import java.util.Random;

public class AuthService {
    //Declare and initialize the userService
    private final UserService userService = new UserService();
    // Create a OTP session DAo
    private final OtpSessionDao otpSessionDao = new InMemoryOtpSessionDao();
    // Use an random generator object
    private final Random rad = new Random();

    public CustomResponse<String> sendOpt(OtpRequestDto loginRequest) {
        // Check weather the user with phone number is present
        User user = null;
        // If present Create a Otp Session and send the session id
        // if not present create a users and send the session id
        try{
            // To check user Call userService find user by phone number
            user = userService.getUserByPhone(loginRequest.getPhone());
        }catch (UserNotFound userNotFound){
            user = userService.createUser(loginRequest.getPhone());
        }
        // Response object
        OtpSession otpSession = new OtpSession(user.getId());
        otpSession.setSessionId(createSessionId(user.getPhoneNumber()));
        otpSession.setOpt(createOtp());
        otpSession.setExpireBy(LocalDateTime.now().plusSeconds(30));
        int excecutionCount = 0 ;
        while(excecutionCount <= 3 ) {
            try {
                createOtpSession(otpSession);
                if (excecutionCount == 3) {
                    throw new ServerError("Server error try later ");
                }
            } catch (OtpSessionAlreadyPresent error) {
                otpSession.setSessionId(createSessionId(user.getPhoneNumber()));
                otpSession.setOpt(createOtp());
                excecutionCount++;
            }
        }
        System.out.println(otpSession.getOpt());
        return new CustomResponse<>(true , "Sent Otp successfully" , otpSession.getSessionId());
    }

    private void createOtpSession(OtpSession otpSession) {
        OtpSession session = otpSessionDao.findBySessionAndOtp(otpSession.getOpt() , otpSession.getSessionId()) ;
        if(session !=null){
            throw  new OtpSessionAlreadyPresent("OTP session already present");
        }
        otpSessionDao.createOtpSession(otpSession);
    }

    private String createOtp() {
        return String.valueOf(rad.nextInt(9999));
    }

    private String createSessionId(String phoneNumber) {
        // Return a session id based on the phone number date and a random number
        return phoneNumber + LocalDateTime.now() + rad.nextInt(9999);

    }


    public CustomResponse<AuthResponseDto> verifyOtp(OtpVerificationRequestDto requestDto) {
        return null;
    }

    public CustomResponse<String> reSendOtp(String sessionId) {
    return  null;
    }
}
