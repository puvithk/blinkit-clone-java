package auth.controller;

import auth.dto.AuthResponseDto;
import auth.dto.OtpRequestDto;
import auth.dto.OtpVerificationRequestDto;
import auth.service.AuthService;
import common.response.CustomResponse;

public class AuthController {

    // Declare and initialize the Objects
    // Declare the Auth service object
    private final AuthService authService = new AuthService();

    public CustomResponse<String> sendOtp(OtpRequestDto requestDto){
        // Send the phone number into the auth service layer
        return authService.sendOpt(requestDto);
    }

    public CustomResponse<AuthResponseDto> verifyOtp(OtpVerificationRequestDto requestDto) {
        // send the session id and opt to the auth server layer
        return authService.verifyOtp(requestDto);
    }
}
