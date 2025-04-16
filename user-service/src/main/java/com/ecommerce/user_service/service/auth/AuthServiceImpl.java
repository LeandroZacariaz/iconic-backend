package com.ecommerce.user_service.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecommerce.user_service.domain.User;
import com.ecommerce.user_service.dto.auth.AuthResponseDto;
import com.ecommerce.user_service.dto.user.UserLoginDto;
import com.ecommerce.user_service.dto.user.UserRegisterDto;
import com.ecommerce.user_service.exceptions.EmailAlreadyExistsException;
import com.ecommerce.user_service.exceptions.InvalidCredentialsException;
import com.ecommerce.user_service.exceptions.ResourceNotFoundException;
import com.ecommerce.user_service.repository.UserRepository;
import com.ecommerce.user_service.service.jwt.JwtService;
import com.ecommerce.user_service.service.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.email())) {
            throw new EmailAlreadyExistsException("Usuario con email " + userRegisterDto.email() + " ya existe.");
        }else{
            User user = userService.createUser(userRegisterDto);
            return AuthResponseDto.builder().token(jwtService.getToken(user)).build();
        }
    }

    @Override
    public AuthResponseDto login(UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password()));
        } catch (BadCredentialsException var4) {
            throw new InvalidCredentialsException("Email o contraseña inválidos.");
        }
        UserDetails user = userRepository.findByEmail(userLoginDto.email()).orElseThrow(() -> {
            return new ResourceNotFoundException("Usuario con email: " + userLoginDto.email() + " no encontrado.");
         });
        String token = jwtService.getToken(user);
        return AuthResponseDto.builder().token(token).build();
    }

    @Override
    public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
