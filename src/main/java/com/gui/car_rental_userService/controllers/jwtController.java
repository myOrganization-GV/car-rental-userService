package com.gui.car_rental_userService.controllers;

import com.gui.car_rental_userService.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
public class jwtController {

    private final RSAKeyProperties rsaKeyProperties;


    public jwtController(RSAKeyProperties rsaKeyProperties){
        this.rsaKeyProperties = rsaKeyProperties;
    }
    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getJwks() {
        RSAPublicKey publicKey = rsaKeyProperties.getPublicKey();
        RSAKey jwk = new RSAKey.Builder(publicKey)
                .build();
        JWKSet jwkSet = new JWKSet(jwk);
        return jwkSet.toJSONObject();
    }
}
