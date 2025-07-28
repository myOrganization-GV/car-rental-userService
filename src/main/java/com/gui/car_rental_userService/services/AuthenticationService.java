package com.gui.car_rental_userService.services;


import com.gui.car_rental_userService.entities.Role;
import com.gui.car_rental_userService.entities.User;
import com.gui.car_rental_userService.enums.Provider;
import com.gui.car_rental_userService.models.LoginResponseDto;
import com.gui.car_rental_userService.repositories.RoleRepository;
import com.gui.car_rental_userService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public User registerUser(String firstName, String lastName, String email, Provider provider, String password){
        if(userRepository.findByEmail(email).isPresent()){
            throw new IllegalStateException("User already exists with this email");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> userRoleOptional = roleRepository.findByAuthority("USER");
        if (userRoleOptional.isEmpty()) {
            throw new IllegalStateException("Role 'USER' not found in the system. Cannot register user.");
        }
        Role userRole = userRoleOptional.get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);
        User user = new User(null,firstName,lastName,email, null, provider, authorities, encodedPassword);
        return userRepository.save(user);
    }

    public LoginResponseDto loginUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        String token = tokenService.generateJwt(authentication);

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new LoginResponseDto(userOptional.get(), token);
        } else {
            throw new IllegalStateException("User not found after successful authentication.");
        }

    }



}
