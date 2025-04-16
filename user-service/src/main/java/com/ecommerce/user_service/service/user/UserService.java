package com.ecommerce.user_service.service.user;

import com.ecommerce.user_service.domain.User;
import com.ecommerce.user_service.dto.user.UserRegisterDto;

public interface UserService {
    User createUser(UserRegisterDto userRegisterDto);
    User getLoggingUser();
    User getUserById(Long id_user);
}
