package com.sparta.jpaupgradeschedule.dto;

import com.sparta.jpaupgradeschedule.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime postTime;
    private final LocalDateTime updateTime;

    public UserSaveResponseDto(User saveUser) {
        this.id = saveUser.getId();
        this.username = saveUser.getUsername();
        this.email = saveUser.getEmail();
        this.postTime = saveUser.getPostTime();
        this.updateTime = saveUser.getUpdateTime();
    }
}
