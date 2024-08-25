package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentUpdateResponseDto {
    private final Long id;
    private final String username;
    private final String content;

    public CommentUpdateResponseDto(Long id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }
}
