package com.sparta.jpaupgradeschedule.dto;

import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {
    private String username;
    private String title;
    private String content;
}
