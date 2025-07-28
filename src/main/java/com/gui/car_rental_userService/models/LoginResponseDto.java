package com.gui.car_rental_userService.models;


import com.gui.car_rental_userService.entities.User;

public class LoginResponseDto {

    private User user;
    private String jwt;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(User user, String jwt) {
        super();
        this.user = user;
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
