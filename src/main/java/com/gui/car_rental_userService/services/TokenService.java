package com.gui.car_rental_userService.services;

import com.gui.car_rental_userService.entities.User;
import com.gui.car_rental_userService.models.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;


    public String generateJwt(Authentication auth){

        Instant now = Instant.now();
        SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
        User user = securityUser.getUser();
        List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("Car Rental")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("firstName",user.getFirstName())
                .claim("roles",roles)
                .claim("email", auth.getName())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}
