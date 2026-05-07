package auth.service;

import auth.dto.AuthResponseDto;
import auth.dto.OtpRequestDto;
import auth.dto.OtpVerificationRequestDto;
import auth.exceptions.InvalidPhoneNumber;
import common.response.CustomResponse;

public class AuthService {
    public CustomResponse<String> sendOpt(OtpRequestDto loginRequest) {
        return null;
    }


    public CustomResponse<AuthResponseDto> verifyOtp(OtpVerificationRequestDto requestDto) {
    }

    public CustomResponse<String> reSendOtp(String sessionId) {
    }
}
