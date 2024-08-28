package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String email;
    private String password;
    private Boolean role;
}
