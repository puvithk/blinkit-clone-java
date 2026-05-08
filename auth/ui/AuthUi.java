package auth.ui;


import auth.controller.AuthController;
import auth.dto.AuthResponseDto;
import auth.dto.OtpRequestDto;
import auth.dto.OtpVerificationRequestDto;
import auth.exceptions.InvalidOtp;
import auth.exceptions.InvalidPhoneNumber;

import common.exception.ServerError;
import common.exception.UserInterrupt;
import common.response.CustomResponse;
import common.security.SecurityContext;
import user.exceptions.UserNotFound;
import user.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class AuthUi {
    // Declare and initialize a object of AuthService
    private final AuthController authController = new AuthController();
    // Create a object of logger
    private final Logger logger = Logger.getLogger(AuthUi.class.getName());
    // Declare a object of Scanner
    private final Scanner scanner = new Scanner(System.in);

    // Session Id
    // Usually  this will be stored in local host hence Didn't do a object of it
    private String sessionId ;
    //  Function to send OTP and get the Session Id
    private void sendOtp() {
        // Ask the Phone number
        // Create object of LoginRequestDto
        OtpRequestDto otpRequestDto = new OtpRequestDto();
        // Get the Phone number using the object
        logger.info("Enter phone number : (without +91)");
        otpRequestDto.setPhone("+91" + scanner.nextLine());

        // Send the phone number on loginRequestDto into the controller
        while(true){
            try {
                // will return a session id
                CustomResponse<String> result =   authController.sendOtp(otpRequestDto);
                if(result.isSuccess()){
                    sessionId =  result.getData();
                    verifyOtpAndLogin();
                    break;
                }else {
                    // Due to server error
                    throw new ServerError("Unaccepted server Error");
                }

            }catch (InvalidPhoneNumber invalidPhoneNumber){
                // Verify if the Phone number i valid
                logger.info("Invalid Phone number ");
                // Preference of the user
                logger.info("Enter any choice : \n 1) ReEnter phone : \n 2) Exit.. ");

                // Get the user choice
                int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice==1){
                    // Reenter the user choice
                    logger.info("Enter phone number : (without +91)");
                    otpRequestDto.setPhone("+91" + scanner.nextLine());
                }else {
                    throw new UserInterrupt("Operation aborted based on user request");
                }

            }catch (ServerError serverError){
                // Logger the error info
                logger.info("Unexpected Server error :");
                logger.info("Try After some time .... Exiting");
                // End the program
                System.exit(0);
            }
        }




    }


    private void verifyOtpAndLogin() {

            // Create an object of Opt verification object
            OtpVerificationRequestDto requestDto = new OtpVerificationRequestDto();
            // check is Session id is present


            // otp Will be entered
            logger.info("Enter the OTP : ");
            requestDto.setOpt(scanner.nextLine());
            requestDto.setSessionId(sessionId);
            while(true){
                try{
                    // send session id and otp
                   CustomResponse<AuthResponseDto> authResponseDto = authController.verifyOtp(requestDto);
                   // Check is response is null server error
                   if(authResponseDto == null){
                       throw new ServerError("Internal server error");
                   }
                   AuthResponseDto responseDto = authResponseDto.getData();
                   // IF user profile is not filled
                   if(!responseDto.isProfileCompleted()){
                       // Update the User profile in the database
                       completeUserProfile(responseDto);
                   }
                    // If success update the security context with the user data
                   updateSecurityContext(responseDto);
                    System.out.println(SecurityContext.getContext().toString());
                   break;
                }catch (ServerError serverError){
                    // Logger the error info
                    logger.info("Unexpected Server error : Try After some time .... Exiting ");

                    // End the program
                    System.exit(0);
                } catch (InvalidOtp invalidOtp){
                    // if wrong renter the opt if time sent was more than 30 sec or Resent the phone number to controller
                    if(invalidOtp.isOtpMisMatch()){
                        // enter opt again
                        logger.info("Otp not matching ....");
                        logger.info("Enter the OTP : ");
                        requestDto.setOpt(scanner.nextLine());
                        continue;
                    }


                    // it means times Up hence resend  opt
                    logger.info("OTP is expired Resend the opt :  ");
                    logger.info("Enter any choice : \n 1) Resend OTP : \n 2) Exit.. ");

                    // Get the user choice
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == 1) {
                        reSendOtp();
                        // Set the session id
                        requestDto.setSessionId(sessionId);
                    } else {
                        throw new UserInterrupt("Operation aborted based on user request");
                    }
                }

            }





    }

    private void updateSecurityContext(AuthResponseDto responseDto) {
        authController.updateSecurityContext(responseDto);

    }


    private void completeUserProfile(AuthResponseDto authResponseDto) {
        User user = new User(authResponseDto.getUserId());
        logger.info("Update the User prefile :");
        logger.info("Enter your name :");
        user.setUsername(scanner.nextLine());
        logger.info("Enter your email :");
        user.setEmail(scanner.nextLine());
        logger.info("Enter the Dob");
        user.setDob(LocalDate.parse(scanner.nextLine()));

        // Update the database
        authController.updateUser(user);
        authResponseDto.setUsername(user.getUsername());
        authResponseDto.setUserId(user.getId());
    }

    public void reSendOtp(){
        // Resend the otp
        try {
            CustomResponse<String> result =   authController.reSendOtp(sessionId);
            if(result.isSuccess()){
                sessionId =  result.getData();
            }else {
                // Due to server error
                throw new ServerError("Unaccepted server Error");
            }
        }catch (UserNotFound userNotFound){
            logger.info("User not found .Retry after some time :: ");
            System.exit(0);
        }catch (ServerError serverError){
            logger.info("Server error try after sometime : ");
            System.exit(0);
        }



    }
    public void login() {

        AuthUi authUi = new AuthUi();

        authUi.sendOtp();

        if(authUi.sessionId == null){
            logger.info("Session id not available. Resending OTP...");
            authUi.sendOtp();
        }


    }
}
