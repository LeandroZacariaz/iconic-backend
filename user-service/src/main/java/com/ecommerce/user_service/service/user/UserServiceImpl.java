package com.ecommerce.user_service.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user_service.domain.User;
import com.ecommerce.user_service.domain.enums.RoleEnumUser;
import com.ecommerce.user_service.dto.user.UserRegisterDto;
import com.ecommerce.user_service.mappers.user.UserMapper;
import com.ecommerce.user_service.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @Override
    public User createUser(UserRegisterDto userRegisterDto) {
        User userCreated=userMapper.userRegisterDtoToUser(userRegisterDto);
        userCreated.setPassword(passwordEncoder.encode(userRegisterDto.password()));
        userCreated.setRole(RoleEnumUser.USER);
        return userRepository.save(userCreated);
    }

    @Override
    public User getLoggingUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("No se ha encontrado ningún usuario autenticado.");
            }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario con email: " + email + " no encontrado."));
    }

    @Override
    public User getUserById(Long id_user) {
        return userRepository.findById(id_user)
                  .orElseThrow(() -> new UsernameNotFoundException("Usuario con ID: " + id_user + " no encontrado."));
    }

    

}
