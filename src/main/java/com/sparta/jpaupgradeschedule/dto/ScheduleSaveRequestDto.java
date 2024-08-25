package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {
    private Long userId;
    private String title;
    private String content;
}
