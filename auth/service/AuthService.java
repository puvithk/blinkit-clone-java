package auth.service;

import auth.dao.OtpSessionDao;
import auth.dao.impl.InMemoryOtpSessionDao;
import auth.dto.AuthResponseDto;
import auth.dto.OtpRequestDto;
import auth.dto.OtpVerificationRequestDto;

import auth.exceptions.InvalidOtp;
import auth.exceptions.InvalidPhoneNumber;
import auth.exceptions.OtpSessionAlreadyPresent;
import auth.exceptions.SessionIdNotPresent;
import auth.model.OtpSession;
import common.exception.ServerError;
import common.response.CustomResponse;

import common.security.SecurityContext;
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

        // Check weather the phone number is valid
        if(!loginRequest.getPhone().matches("^\\+91[6-9]\\d{9}$")){
            throw new InvalidPhoneNumber("Invalid Phone Number ");
        }
        // Check weather the user with phone number is present
        User user ;
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
        return phoneNumber.substring(3) + LocalDateTime.now() + rad.nextInt(9999);

    }


    public CustomResponse<AuthResponseDto> verifyOtp(OtpVerificationRequestDto requestDto) {
        // Get the session Id
        String currentSessionId = requestDto.getSessionId();
        // Get the otp session based on the session id
        OtpSession currentOtpSession = otpSessionDao.findBySessionId(currentSessionId);
        // Check weather opt is expired
        boolean isOtpExpired = currentOtpSession.getExpireBy().isBefore(LocalDateTime.now());

        if (isOtpExpired) {
            throw new InvalidOtp("OTP expired", false);
        }
        // Check weather the otp matchs
        boolean isOtpCorrect = currentOtpSession.getOpt().equals(requestDto.getOpt());

        if (!isOtpCorrect) {
            throw new InvalidOtp("Invalid OTP", true);
        }
        // Updated the verified
        currentOtpSession.setVerified(true);
        // update the database
        otpSessionDao.updateOtpVerification(currentOtpSession);
        // Get the users
        User user = userService.getUserByUserId(currentOtpSession.getUserId());
        // Updated the autoresponse DTO
        AuthResponseDto authResponseDto = new AuthResponseDto();

        authResponseDto.setUserId(currentOtpSession.getUserId());
        // Check weather the profileIs completed or not
        if (user.getUsername() == null) {
            authResponseDto.setProfileCompleted(false);
        } else {
            authResponseDto.setUsername(user.getUsername());
            authResponseDto.setProfileCompleted(true);
        }

        return new CustomResponse<>(
                true,
                "OTP verified successfully",
                authResponseDto
        );
    }

    public CustomResponse<String> reSendOtp(String sessionId) {
        // Delete the Otp session of id session id

        OtpSession newSession = otpSessionDao.findBySessionId(sessionId);
        if(newSession == null){
            throw new SessionIdNotPresent("Session not present ");
        }



        otpSessionDao.deleteOtpSessionBySessionId(newSession);
        // Get the user details
        OtpSession otpSession = new OtpSession(newSession.getUserId());
        // Get the user details using session
        User user;
        try {
            user = userService.getUserByUserId(otpSession.getUserId());
        }catch (UserNotFound userNotFound){
            throw new UserNotFound("User not found ");
        }
        otpSession.setSessionId(createSessionId(user.getPhoneNumber()));
        otpSession.setOpt(createOtp());
        // Update and get new session and opt
        // Add expire by
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
        // The call verify opt


    }

    public void updateUser(User user){
        // Get the user by user id
        User currentUser = userService.getUserByUserId(user.getId());
        if(currentUser == null){
            throw new UserNotFound("user not found");
        }
        // Update the phone number in the user
        user.setPhoneNumber(currentUser.getPhoneNumber());
        // Update the user in database
        userService.updateUser(user);

    }


    public void updateSecurityContext(AuthResponseDto responseDto) {
        SecurityContext securityContext = SecurityContext.getContext();
        securityContext.setUsername(responseDto.getUsername());
        securityContext.setUserId(responseDto.getUserId());
    }

    public void updateSecurityContextWarehouse(Integer warehouseId) {
        SecurityContext context = SecurityContext.getContext();
        context.setWarehouseId(warehouseId);

    }
}
