package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime postTime;
    private final LocalDateTime updateTime;

    public ScheduleSaveResponseDto(Long id, Long userId, String title, String content, LocalDateTime postTime, LocalDateTime updateTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.updateTime = updateTime;
    }
}