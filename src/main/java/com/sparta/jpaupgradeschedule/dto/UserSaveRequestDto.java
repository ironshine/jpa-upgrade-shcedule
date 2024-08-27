package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveRequestDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime postTime;
    private LocalDateTime updateTime;

    public void setPassword(String password) {
        this.password = password;
    }
}
