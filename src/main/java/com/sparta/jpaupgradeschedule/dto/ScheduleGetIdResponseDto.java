package com.sparta.jpaupgradeschedule.dto;

import com.sparta.jpaupgradeschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class ScheduleGetIdResponseDto {
    private final Long id;
    private final List<UserResponseDto> userId;
    private final String title;
    private final String content;
    private final LocalDateTime postTime;
    private final LocalDateTime updateTime;

    public ScheduleGetIdResponseDto(Schedule idSchedule, List<UserResponseDto> userList) {
        this.id = idSchedule.getId();
        this.userId = userList;
        this.title = idSchedule.getTitle();
        this.content = idSchedule.getContent();
        this.postTime = idSchedule.getPostTime();
        this.updateTime = idSchedule.getUpdateTime();
    }
}
