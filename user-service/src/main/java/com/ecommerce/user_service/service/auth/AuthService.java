package com.ecommerce.user_service.service.auth;

import com.ecommerce.user_service.dto.auth.AuthResponseDto;
import com.ecommerce.user_service.dto.user.UserLoginDto;
import com.ecommerce.user_service.dto.user.UserRegisterDto;

public interface AuthService {
    AuthResponseDto register(UserRegisterDto userRegisterDto);
    AuthResponseDto login(UserLoginDto userLoginDto);

    String getCurrentUserEmail();
}
