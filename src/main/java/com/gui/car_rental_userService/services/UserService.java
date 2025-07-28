package com.gui.car_rental_userService.services;

import com.gui.car_rental_userService.models.SecurityUser;
import com.gui.car_rental_userService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        return new SecurityUser(userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user is not valid")));
    }

}