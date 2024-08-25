package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentSaveResponseDto {
    private final Long id;
    private final String username;
    private final String content;
    private final LocalDateTime postTime;
    private final LocalDateTime updateTime;

    public CommentSaveResponseDto(Long id,String username, String content, LocalDateTime postTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.postTime = postTime;
        this.updateTime = updateTime;
    }
}
