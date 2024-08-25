package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentUpdateRequestDto {
    private String username;
    private String content;
    private LocalDateTime updateTime;
}
