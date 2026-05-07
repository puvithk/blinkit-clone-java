package auth.service;

import auth.dto.AuthResponseDto;
import auth.dto.OtpRequestDto;
import auth.dto.OtpVerificationRequestDto;
import auth.exceptions.InvalidPhoneNumber;
import common.response.CustomResponse;

public class AuthService {
    public CustomResponse<String> sendOpt(OtpRequestDto loginRequest){
        // Check weather the number is correct
        if(!loginRequest.getPhone().matches("^\\+91[6-9]{9}$")){
            // Mainly based on India hence 10 digits
            throw new InvalidPhoneNumber("Not a valid Phone number");

        }
        // Check weather the number is in the database

        // If in database do nothing

        // If not create user and update

        // Create a session id and a otp and update in the database
        // Return the success message with the session id as data
        String sessionId = "Good" ;
        return new CustomResponse<>(true , "Opt verification needed" , sessionId);
    }


    public CustomResponse<AuthResponseDto> verifyOtp(OtpVerificationRequestDto requestDto) {
    }
}
